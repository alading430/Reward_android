package com.dzg.reward;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.dzg.reward.R;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-11-20
 */

public class WelcomeActivity extends TAActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.welcome, null);
		setContentView(view);
		AlphaAnimation animation = new AlphaAnimation(0.5f, 1.0f);
		animation.setDuration(1000);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				startMain();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
	}

	@Override
	protected void onPreOnCreate(Bundle savedInstanceState) {
		super.onPreOnCreate(savedInstanceState);

	}

	public void startMain() {
		doActivity(R.string.main);
//		Intent intent = new Intent(this, MainActivity.class);
//		startActivity(intent);
	}

}
