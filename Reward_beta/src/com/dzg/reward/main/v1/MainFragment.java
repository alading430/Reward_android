package com.dzg.reward.main.v1;

import java.util.ArrayList;

import com.dzg.custom.gallery.GalleryNavigator;
import com.dzg.custom.gallery.ImageAdapter;
import com.dzg.custom.gallery.OneFlingGallery;
import com.dzg.reward.R;
import com.dzg.reward.abs.BaseFragment;
import com.dzg.reward.line.v1.LineActivity;
import com.dzg.reward.mine.v1.MineLoginActivity;
import com.dzg.reward.mine.v1.MineSuggestActivity;
import com.ta.annotation.TAInjectView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
/**
 * @author yufeng.dzg
 * @version:2014-11-20
 */

@SuppressLint({ "NewApi", "HandlerLeak" })
public class MainFragment extends BaseFragment implements OnClickListener {
	
	@TAInjectView(id = R.id.main_reward)
	private ImageView main_reward;
	@TAInjectView(id = R.id.main_receive)
	private ImageView main_receive;
	@TAInjectView(id = R.id.main_buy)
	private ImageView main_buy;
	@TAInjectView(id = R.id.main_sell)
	private ImageView main_sell;
	@TAInjectView(id = R.id.main_line)
	private ImageView main_line;
	@TAInjectView(id = R.id.main_mine)
	private ImageView main_mine;
	@TAInjectView(id = R.id.main_suggest)
	private ImageView main_suggest;
	@TAInjectView(id = R.id.main_service)
	private ImageView main_service;
	@TAInjectView(id = R.id.login_btn)
	private TextView login_btn;
	@TAInjectView(id = R.id.gallery)
	private OneFlingGallery gallery;
	
	private ImageAdapter mAdapter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		setContentView(R.layout.v1_main_fragment);
		
		main_reward.setOnClickListener(this);
		main_receive.setOnClickListener(this);
		main_buy.setOnClickListener(this);
		main_sell.setOnClickListener(this);
		main_line.setOnClickListener(this);
		main_mine.setOnClickListener(this);
		main_suggest.setOnClickListener(this);
		main_service.setOnClickListener(this);
		login_btn.setOnClickListener(this);
		
		 
		ArrayList<Integer> mResourceList = createResourceList("image_%02d");
		mAdapter = new ImageAdapter(mContext, mResourceList);
		final GalleryNavigator navi = (GalleryNavigator)findViewById(R.id.navi);
		navi.setSize(mResourceList.size());
//
		gallery.setAdapter(mAdapter);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int id,
					long arg3) {
				navi.setPosition(id);
				navi.invalidate();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		startAutoScroll();
		return mainView;
	}
	
	
	
	public ArrayList<Integer> createResourceList(String format) {
		ArrayList<Integer> resourceList = new ArrayList<Integer>();
		int index = 1;
		int resId;
		final Resources resources = mContext.getResources();
		final String packageName = mContext.getPackageName();

		while (true) {
			String name = String.format(format, index);
			resId = resources.getIdentifier(name, "drawable", packageName);
			if (resId != 0) {
				resourceList.add(resId);
			} else {
				break;
			}
			index++;
		}
		return resourceList;
	}
	
	AutoScroll autoScroll;
	
	private void startAutoScroll(){
		autoScroll = new AutoScroll();
		new Thread(autoScroll).start();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		System.out.println("onStop");
		autoScroll.flag = false;
	}
	
	class AutoScroll implements Runnable {
		public boolean flag = true;
		Object object = new Object();
		@Override
		public void run() {
			synchronized (object) {
				while(flag){
					for (int i = 0; i < 5; i++) {
						handler.sendEmptyMessage(i);
						try {
							object.wait(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(!flag)
							break;
					}
				}
			}
		}
	};
	
	Handler handler = new Handler(){
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			gallery.setSelection(msg.what);
		}
	};
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId()){
		case R.id.main_reward:
			activity.setCurrentTab(1);
			break;
		case R.id.main_receive:
			activity.setCurrentTab(2);
			break;
		case R.id.main_sell:
			activity.setCurrentTab(4);
			break;
		case R.id.main_buy:
			activity.setCurrentTab(5);
			break;
		case R.id.main_line:
			intent = new Intent(activity, LineActivity.class);
			activity.startActivity(intent);
			break;
		case R.id.main_mine:
			activity.setCurrentTab(3);
			break;
		case R.id.main_suggest:
			intent = new Intent(activity, MineSuggestActivity.class);
			activity.startActivity(intent);
			break;
		case R.id.main_service:
			Uri call = Uri.parse("tel:"+"123456");
			intent = new Intent(Intent.ACTION_CALL, call);
			activity.startActivity(intent);
			break;
		case R.id.login_btn:
			intent = new Intent(activity, MineLoginActivity.class);
			activity.startActivity(intent);
			break;
		}
	}
	
}
