package com.dzg.reward.reward.v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.dzg.reward.BaseActivity;
import com.dzg.reward.R;
import com.dzg.reward.model.local.RewardInfo;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-10-10
 */

public class RewardDetailActivity extends BaseActivity implements OnClickListener {

	private TextView btn_back;
	private RewardInfo reward;
	private View layout;
	private TextView send_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_reward_detail);
		loadView();
	}
	
	private void loadView(){
		//---------------header--------------------
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		TextView title = (TextView)findViewById(R.id.tv_title_name);
		title.setText("悬赏-预约占座");
		title.setVisibility(View.VISIBLE);
		//---------------body---------------------
		send_btn = (TextView)findViewById(R.id.send_btn);
		send_btn.setOnClickListener(this);
		layout = (View)findViewById(R.id.layout);
		layout.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId()){
		case R.id.btn_back:
			finish();
//			back();
//			finishActivity();
			break;
		case R.id.send_btn:
			intent = new Intent(this, RewardWaitActivity.class);
			startActivity(intent);
			break;
		case R.id.layout:
			
			break;
		}
	}
	
	
	public void setRewardInfo(RewardInfo reward) {
		this.reward = reward;
	}
	
}
