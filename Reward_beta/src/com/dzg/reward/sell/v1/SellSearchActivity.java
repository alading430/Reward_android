package com.dzg.reward.sell.v1;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.dzg.reward.R;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-10-18
 */

public class SellSearchActivity extends TAActivity implements OnClickListener {

	private TextView btn_back;
	
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
		title.setText("我要现买");
		title.setVisibility(View.VISIBLE);
		
//		TabPagerFragmentView tabFragmentGroup = (TabPagerFragmentView)findViewById(R.id.reward_tabpagegroups);
//		TabPagerItem item = null;
//		List<TabPagerItem> data = new ArrayList<TabPagerItem>();
//
//		item = new TabPagerItem(this.getBaseContext());
//		RewardBodyTabPager reward = RewardBodyTabPager.getInstance(1l);
//		item.initTabPagerItem(null,null,null,reward,false,false,true,false);
//		data.add(item);
//		tabFragmentGroup.setTabItems(data);
//		tabFragmentGroup.initTabPager();
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
