package com.dzg.reward.buy.v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.dzg.reward.R;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-11-6
 */

public class BuyDetail2Activity extends TAActivity implements OnClickListener {
	
	private TextView btn_back = null;
	
	private TextView buy_btn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_buy_detail2);
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
		
		buy_btn = (TextView)findViewById(R.id.buy_btn);
		buy_btn.setOnClickListener(this);
	}
	
	private void loadData(){
		
	}
	
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		case R.id.buy_btn:
			Intent intent = new Intent(this, BuyResultActivity.class);
			this.startActivity(intent);
			break;
		}
	}
	
}
