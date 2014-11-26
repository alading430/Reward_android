package com.dzg.reward;

import com.dzg.lib.omni.OmniBaseActivity;
import com.dzg.lib.thread.SingleHandler;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends OmniBaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
	}
	
	// TODO add =====================================================

		private ProgressDialog loading = null;

		public void showToast(final String msg) {
			SingleHandler.getInstance(true).post(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
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
					loading = ProgressDialog.show(BaseActivity.this, title, msg,
							true, cancel);
				}
			});
		}

		/**
		 * 隐藏加载提示框
		 */
		public void hideLoading() {
			if (loading != null) {
				loading.dismiss();
			}
		}
	
}
