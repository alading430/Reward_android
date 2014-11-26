package com.dzg.reward.mine.v1;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.dzg.reward.R;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-11-9
 */

public class MineSuggestActivity extends TAActivity implements OnClickListener {
	private TextView btn_back = null;
	private TextView tv_title_name = null;
	
	private View suggest_layout;
	private Button submit_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_mine_suggest);
		loadView();
	}
	
	private void loadView(){
		
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		
		tv_title_name = (TextView)findViewById(R.id.tv_title_name);
		tv_title_name.setText("我的建议");
		tv_title_name.setVisibility(View.VISIBLE);
		
		suggest_layout = (View)findViewById(R.id.suggest_layout);
		suggest_layout.setOnClickListener(this);
		
		submit_btn = (Button)findViewById(R.id.submit_btn);
		submit_btn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		case R.id.suggest_layout:
			break;
		case R.id.submit_btn:
			
			break;
		}
	}
	
}
