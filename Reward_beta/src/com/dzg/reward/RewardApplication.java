package com.dzg.reward;

import com.dzg.reward.reward.v1.RewardDetailActivity;
import com.ta.TAApplication;
import com.ta.util.cache.TAFileCache;
import com.ta.util.cache.TAFileCache.TACacheParams;

/**
 * @author yufeng.dzg
 * @version:2014-11-20
 */

public class RewardApplication extends TAApplication {

	private static final String SYSTEM_CACHE = "reward_cache";
	
	private static RewardApplication application;
	
	public static RewardApplication getInstance() {
		return (RewardApplication) application;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}
	
	private void init(){
		// 配置系统的缓存,可以选择性的配置
		TACacheParams cacheParams = new TACacheParams(this, SYSTEM_CACHE);
		TAFileCache fileCache = new TAFileCache(cacheParams);
		setFileCache(fileCache);
		// 注册activity
		registerActivity(R.string.welcome, WelcomeActivity.class);
		registerActivity(R.string.main, MainActivity.class);
//		registerActivity(R.string.rewarddetail, RewardDetailActivity.class);
	}
	
	
	
}
