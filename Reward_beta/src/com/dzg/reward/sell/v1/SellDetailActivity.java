package com.dzg.reward.sell.v1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.dzg.reward.BaseActivity;
import com.dzg.reward.R;
import com.dzg.reward.reward.v1.RewardWaitActivity;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-10-10
 */

public class SellDetailActivity extends BaseActivity implements OnClickListener {

	private TextView btn_back;
	private TextView send_btn;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = this;
		setContentView(R.layout.v1_sell_detail);
		loadView();
	}
	
	private void loadView(){
		//---------------header--------------------
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		TextView title = (TextView)findViewById(R.id.tv_title_name);
		title.setText("出售位置-发布现卖信息");
		title.setVisibility(View.VISIBLE);
		//---------------body---------------------
		send_btn = (TextView)findViewById(R.id.send_btn);
		send_btn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		case R.id.send_btn:
			intent = new Intent(context, RewardWaitActivity.class);
			context.startActivity(intent);
			break;
		}
	}
	
}
