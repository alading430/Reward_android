package com.dzg.reward.abs;

import com.dzg.reward.RewardApplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author yufeng.dzg
 * @version:2014-11-23
 */

public class BaseView extends LinearLayout {

	public BaseView(Context context) {
		super(context);
	}

	public BaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public BaseView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	protected void init(){
		RewardApplication.getApplication().getInjector().inject(this);
		RewardApplication.getApplication().getInjector().injectView(this);
	}
	
	@Override
	protected void onAnimationStart() {
		// TODO Auto-generated method stub
		super.onAnimationStart();
	}
	
	@Override
	protected void onCreateContextMenu(ContextMenu menu) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu);
	}
	
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		// TODO Auto-generated method stub
		super.onVisibilityChanged(changedView, visibility);
	}
}
