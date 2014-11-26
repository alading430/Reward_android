package com.dzg.reward;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author yufeng.dzg
 * @version:2014-11-20
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

	Fragment[] fragmentArray;
	
	public BaseFragmentPagerAdapter(FragmentManager fm, Fragment[] fragmentArray) {
		super(fm);
		if(fragmentArray == null)
			this.fragmentArray = new Fragment[]{};
		else
			this.fragmentArray = fragmentArray;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentArray[position];
	}

	@Override
	public int getCount() {
		return fragmentArray.length;
	}

}
