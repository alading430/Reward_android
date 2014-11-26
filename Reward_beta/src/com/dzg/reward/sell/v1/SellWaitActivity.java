package com.dzg.reward.sell.v1;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.dzg.reward.R;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-11-5
 */

public class SellWaitActivity extends TAActivity implements OnClickListener {

	private TextView btn_back = null;

	private TextView tv_title_name = null;
	
	private TextView cancel_btn = null;
	
	private TextView add_waitting = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_reward_wait_reward);
		loadView();
	}
	
	private void loadView(){
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		
		tv_title_name = (TextView)findViewById(R.id.tv_title_name);
		tv_title_name.setText("等待售出");
		tv_title_name.setVisibility(View.VISIBLE);
		cancel_btn = (TextView)findViewById(R.id.cancel_btn);
		cancel_btn.setOnClickListener(this);
		add_waitting = (TextView)findViewById(R.id.add_waitting);
		add_waitting.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.cancel_btn:
			
			break;
		case R.id.add_waitting:
			
			break;
		default:
			break;
		}
	}
	
}
