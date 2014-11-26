package com.dzg.reward.abs;

import com.dzg.lib.thread.SingleHandler;
import com.dzg.reward.MainActivity;
import com.dzg.reward.R;
import com.dzg.reward.RewardApplication;
import com.ta.TAActivity;
import com.ta.TAApplication;
import com.ta.annotation.TAInjectView;
import com.ta.mvc.common.TAIResponseListener;
import com.ta.mvc.common.TARequest;
import com.ta.util.TALogger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author yufeng.dzg
 * @version:2014-11-21
 */

public abstract class BaseFragment extends Fragment {

	protected Context mContext;
	protected MainActivity activity;

	protected LayoutInflater inflater;
	ViewGroup container;
	Bundle savedInstanceState;
	protected View mainView;
	private ProgressDialog loading = null;
	private static final int DIALOG_ID_PROGRESS_DEFAULT = 0x174980;
	private static final String TAIDENTITYCOMMAND = "taidentitycommand";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (MainActivity) activity;
		mContext = activity;
		this.activity.getTAApplication().getInjector().injectResource(this);
		this.activity.getTAApplication().getInjector().inject(this);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;
		this.savedInstanceState = savedInstanceState;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public View setContentView(int layoutResID) {
		mainView = inflater.inflate(layoutResID, container, false);
		this.activity.getTAApplication().getInjector()
				.injectView(this, mainView);
		return mainView;
	}

	public View findViewById(int id) {
		return mainView.findViewById(id);
	}

	public void showToast(final String msg) {
		SingleHandler.getInstance(true).post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 显示加载提示框
	 * 
	 * @param title
	 *            标题
	 * @param msg
	 *            内容
	 * @param cancel
	 *            true：能取消接受back键，false：不能取消
	 */
	public void showLoading(final String title, final String msg,
			final boolean cancel) {
		SingleHandler.getInstance(true).post(new Runnable() {
			@Override
			public void run() {
				loading = ProgressDialog.show(activity, title, msg, true,
						cancel);
			}
		});
	}
	
	/**
	 * 运行activity
	 * 
	 * @param activityResID
	 */
	public final void doActivity(int activityResID) {
		doActivity(activityResID, null);
	}

	public final void doActivity(int activityResID, Bundle bundle) {
		TARequest request = new TARequest();
		request.setData(bundle);
		request.setActivityKeyResID(activityResID);
		// 启动activity
		doCommand(TAIDENTITYCOMMAND, request);
	}
	
	public final void doCommand(int resId, TARequest request) {
		String commandKey = getString(resId);
		doCommand(commandKey, request, null, true);
	}

	public final void doCommand(String commandKey, TARequest request) {
		doCommand(commandKey, request, null, true);
	}

	public final void doCommand(int resId, TARequest request,
			TAIResponseListener listener) {
		String commandKey = getString(resId);
		doCommand(commandKey, request, listener, true);
	}

	public final void doCommand(String commandKey, TARequest request,
			TAIResponseListener listener) {
		doCommand(commandKey, request, listener, true);
	}

	public final void doCommand(int resId, TARequest request,
			TAIResponseListener listener, boolean showProgress) {
		String commandKey = getString(resId);
		TALogger.i(BaseFragment.this, "go with cmdid=" + commandKey
				+ ", request: " + request);
		doCommand(commandKey, request, listener, showProgress, true);
	}

	public final void doCommand(String commandKey, TARequest request,
			TAIResponseListener listener, boolean showProgress) {
		TALogger.i(BaseFragment.this, "go with cmdid=" + commandKey
				+ ", request: " + request);
		doCommand(commandKey, request, listener, showProgress, true);
	}

	public final void doCommand(int resId, TARequest request,
			TAIResponseListener listener, boolean showProgress, boolean record) {
		String commandKey = getString(resId);
		TALogger.i(BaseFragment.this, "go with cmdid=" + commandKey
				+ ", record: " + record + ", request: " + request);
		doCommand(commandKey, request, listener, showProgress, record, false);
	}

	public final void doCommand(String commandKey, TARequest request,
			TAIResponseListener listener, boolean showProgress, boolean record) {
		TALogger.i(BaseFragment.this, "go with cmdid=" + commandKey
				+ ", record: " + record + ", request: " + request);
		doCommand(commandKey, request, listener, showProgress, record, false);
	}

	public final void doCommand(int resId, TARequest request,
			TAIResponseListener listener, boolean showProgress, boolean record,
			boolean resetStack) {
		String commandKey = getString(resId);
		doCommand(commandKey, request, listener, showProgress, record,
				resetStack);
	}

	public final void doCommand(String commandKey, TARequest request,
			TAIResponseListener listener, boolean showProgress, boolean record,
			boolean resetStack) {
		if (showProgress) {
			showProgress();
		}
		RewardApplication.getApplication().doCommand(commandKey, request, listener, record,
				resetStack);
	}
	
	/**
	 * 需要自定义进度条，请重写
	 */
	protected void showProgress() {
		activity.showDialog(DIALOG_ID_PROGRESS_DEFAULT);
	}

	/**
	 * 隐藏进度跳，需要重写的请重写
	 */
	protected void hideProgress() {
		try {
			activity.removeDialog(DIALOG_ID_PROGRESS_DEFAULT);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	
	
	
	
	
	
}
