package com.dzg.reward;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.dzg.reward.R;
import com.dzg.reward.TabBar.OnCurrentTabChangedListener;
import com.dzg.reward.buy.v1.BuyFragment;
import com.dzg.reward.main.v1.MainFragment;
import com.dzg.reward.mine.v1.MineFragment;
import com.dzg.reward.receive.v1.ReceiveFragment;
import com.dzg.reward.reward.v1.RewardFragment;
import com.dzg.reward.sell.v1.SellFragment;
import com.ta.TAActivity;
import com.ta.annotation.TAInjectView;
import com.ta.util.TALogger;

/**
 * @author yufeng.dzg
 * @version:2014-11-20
 */

public class MainActivity extends TAActivity {

	@TAInjectView(id = R.id.view_pager)
	ViewPager view_pager;
	@TAInjectView(id = R.id.tab_bar)
	TabBar tab_bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		loadView();
	}

	private void loadView() {
		TALogger.d(this.getClass().getSimpleName(), "loadView");
		Fragment[] fragmentArray = new Fragment[] { new MainFragment(), new RewardFragment(),
				new ReceiveFragment(), new MineFragment(), new SellFragment(),
				new BuyFragment()};
		BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentArray);
		view_pager.setAdapter(adapter);
		view_pager.setOnPageChangeListener(pageChangeListener);
		tab_bar.setOnCurrentTabChangedListener(tabChangedListener);
	}
	
	public void setCurrentTab(int index){
		tab_bar.setCurrentTab(index);
	}
	
	OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int index) {
			tab_bar.setCurrentTab(index);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	};
	
	OnCurrentTabChangedListener tabChangedListener = new OnCurrentTabChangedListener() {
		
		@Override
		public void onCurrentTabChanged(int index) {
			view_pager.setCurrentItem(index);
		}
	};
	
	public void onBackPressed() {
		finish();
	};
	
	
	
	
}
