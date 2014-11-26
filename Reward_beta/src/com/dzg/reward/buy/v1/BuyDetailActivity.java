package com.dzg.reward.buy.v1;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dzg.reward.BaseActivity;
import com.dzg.reward.R;
import com.dzg.reward.model.local.ReceiveInfo;
//import com.dzg.reward.util.ImageHelper;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-11-6
 */

public class BuyDetailActivity extends BaseActivity implements OnClickListener {
	
	private TextView btn_back = null;
	
	private ListView listView;
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_buy_detail);
		context = this;
		loadView();
		loadData();
	}
	
	private void loadView(){
		
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		TextView title = (TextView)findViewById(R.id.tv_title_name);
		title.setText("我要现买");
		title.setVisibility(View.VISIBLE);
		
		listView = (ListView)findViewById(R.id.listview);
		
	}
	
	private void loadData(){
		
		MyAdapter adapter = new MyAdapter(this);
		// TODO test
		for (int i = 0; i < 20; i++) {
			adapter.data.add(i);
		}
		listView.setAdapter(adapter);
	}
	
	class MyAdapter extends BaseAdapter{
		
		private LayoutInflater inflater;
		
		public List<Integer> data = new ArrayList<Integer>();
		
		public MyAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = inflater.inflate(R.layout.v1_buy_listitem, null);
			}
			/********************悬赏记录********************/
//			ViewHolder holder = (ViewHolder)convertView.getTag(R.id.reward_info);
//			
//			if(holder == null){
//				holder = new ViewHolder();
//				holder.mIcon = (ImageView) convertView.findViewById(R.id.icon);
//				holder.mTitle = (TextView) convertView.findViewById(R.id.title);
//				holder.mCount = (TextView) convertView.findViewById(R.id.count);
//				holder.mSelledCount = (TextView) convertView.findViewById(R.id.selled_count);
//				holder.mAddress = (TextView) convertView.findViewById(R.id.address);
//				convertView.setTag(R.id.reward_info, holder);
//			}
			convertView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, BuyDetail2Activity.class);
					context.startActivity(intent);
				}
			});
			return convertView;
		}
	}
	
	static class ViewHolder{
		
		private ImageView mIcon;
		private TextView mTitle;
		private TextView mCount;
		private TextView mSelledCount;
		private TextView mAddress;
		public void update(ReceiveInfo Receive){
			mTitle.setText(Receive.getName());
			mCount.setText(""+Receive.getCount());
			mSelledCount.setText(""+Receive.getSelledCount());
			mAddress.setText(Receive.getAddress());
			loadImageForView(Receive.getIconUrl()==null?"":Receive.getIconUrl(),mIcon);
		}
	}
	
	public static void loadImageForView(String url,final ImageView imageView){
//		ImageHelper.setImageResource(imageView, url, false, R.drawable.defaulthead);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		}
	}
	
}
