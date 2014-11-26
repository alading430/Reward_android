package com.dzg.reward.custom;

import com.dzg.reward.MainActivity;
import com.dzg.reward.R;
import com.dzg.reward.abs.BaseView;
import com.ta.annotation.TAInject;
import com.ta.annotation.TAInjectView;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @author yufeng.dzg
 * @version:2014-11-23
 */

public class V1Header extends BaseView {
	
	@TAInjectView(id = R.id.tv_title_name)
	public TextView tv_title_name;
	@TAInjectView(id = R.id.btn_back, click = "aaa")
	public TextView btn_back;
	
	public V1Header(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.header,this);
	}
	
	public V1Header(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.header,this);
	}

	public V1Header(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater.from(context).inflate(R.layout.header,this);
	}
	
	public void loadView(final MainActivity activity){
		init();
		btn_back.setVisibility(View.VISIBLE);
		tv_title_name.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activity.setCurrentTab(0);
			}
		});
	}
	
	public void loadView(final OnClickListener clickListener){
		init();
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clickListener.onClick(v);
			}
		});
	}

	
	public void setTitle(String title){
		tv_title_name.setText(title);
		tv_title_name.setVisibility(View.VISIBLE);
	}
	
	public void aaa(View v){
		System.out.println("aaaaaaaaa");
	}
	
}
