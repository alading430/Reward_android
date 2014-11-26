package com.dzg.reward.receive.v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.dzg.reward.BaseActivity;
import com.dzg.reward.R;

/**
 * @author yufeng.dzg
 * @version:2014-10-10
 */

public class ReceiveDetailActivity extends BaseActivity implements OnClickListener {

	private TextView btn_back;
	
	private TextView receive_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_receive_detail);
		loadView();
	}
	
	private void loadView(){
		//---------------header--------------------
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		TextView title = (TextView)findViewById(R.id.tv_title_name);
		title.setText("我要领赏");
		title.setVisibility(View.VISIBLE);
		//---------------body---------------------
		receive_btn = (TextView)findViewById(R.id.receive_btn);
		receive_btn.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		case R.id.receive_btn:
			Intent intent = new Intent(this, ReceiveResultActivity.class);
			this.startActivity(intent);
			break;
		}
	}
	
}
