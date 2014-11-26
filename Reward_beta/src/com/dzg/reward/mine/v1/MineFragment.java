package com.dzg.reward.mine.v1;

import com.dzg.reward.R;
import com.dzg.reward.abs.BaseFragment;
import com.dzg.reward.custom.V1Header;
import com.dzg.reward.util.Const;
import com.ta.annotation.TAInjectView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author yufeng.dzg
 * @version:2014-11-20
 */

@SuppressLint("NewApi")
public class MineFragment extends BaseFragment implements OnClickListener {

	@TAInjectView(id = R.id.header)
	V1Header header;
	@TAInjectView(id = R.id.user_info_btn)
	View user_info_btn;
	@TAInjectView(id = R.id.reward_btn)
	View reward_btn;
	@TAInjectView(id = R.id.receive_btn)
	View receive_btn;
	@TAInjectView(id = R.id.sell_btn)
	View sell_btn;
	@TAInjectView(id = R.id.buy_btn)
	View buy_btn;
	@TAInjectView(id = R.id.wallet_btn)
	View wallet_btn;
	@TAInjectView(id = R.id.message_btn)
	View message_btn;
	@TAInjectView(id = R.id.logout_btn)
	TextView logout_btn;
	@TAInjectView(id = R.id.change_pwd_btn)
	View change_pwd_btn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		setContentView(R.layout.v1_mine);
		loadData();
		return mainView;
	}

	private void loadData() {
		header.loadView(activity);
		header.tv_title_name.setText(R.string.main_mine);
		user_info_btn.setOnClickListener(this);
		reward_btn.setOnClickListener(this);
		receive_btn.setOnClickListener(this);
		sell_btn.setOnClickListener(this);
		buy_btn.setOnClickListener(this);
		wallet_btn.setOnClickListener(this);
		message_btn.setOnClickListener(this);
		logout_btn.setOnClickListener(this);
		change_pwd_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.user_info_btn:
			 intent = new Intent(mContext, MineUserInfoActivity.class);
			mContext.startActivity(intent);
			break;
		case R.id.reward_btn:
			 intent = new Intent(mContext, MineRewardActivity.class);
			mContext.startActivity(intent);
			break;
		case R.id.receive_btn:
			 intent = new Intent(mContext, MineReceiveActivity.class);
			mContext.startActivity(intent);
			break;
		case R.id.sell_btn:
			 intent = new Intent(mContext, MineSellActivity.class);
			mContext.startActivity(intent);
			break;
		case R.id.buy_btn:
			 intent = new Intent(mContext, MineBuyActivity.class);
			mContext.startActivity(intent);
			break;
		case R.id.wallet_btn:
			 intent = new Intent(mContext, MineWalletActivity.class);
			mContext.startActivity(intent);
			break;
		case R.id.message_btn:
			 intent = new Intent(mContext, MineMessageActivity.class);
			mContext.startActivity(intent);
			break;
		case R.id.change_pwd_btn:
			 intent = new Intent(mContext,
			 MineChangeLoginPasswordActivity.class);
			mContext.startActivity(intent);
			break;
		case R.id.logout_btn:
//			Boolean login = CheckLoginStatus.getLoginStatus(mContext);
//			if (login) {
//				exitLogin();
//			} else {
				intent = new Intent(mContext, MineLoginActivity.class);
				activity.startActivityForResult(intent, Const.REQ_CODE_LOGIN);
//			}
			break;
		default:
			break;
		}
	}

}
