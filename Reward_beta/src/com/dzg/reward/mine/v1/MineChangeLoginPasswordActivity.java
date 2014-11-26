package com.dzg.reward.mine.v1;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.dzg.reward.R;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-11-8
 */

public class MineChangeLoginPasswordActivity extends TAActivity implements OnClickListener {
	private TextView btn_back = null;
	private TextView tv_title_name = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_mine_buy);
		loadView();
	}
	
	private void loadView(){
		
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		
		tv_title_name = (TextView)findViewById(R.id.tv_title_name);
		tv_title_name.setText("我现买的");
		tv_title_name.setVisibility(View.VISIBLE);
		
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
