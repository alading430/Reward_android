package com.dzg.reward.mine.v1;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.dzg.lib.thread.NewRunnable;
import com.dzg.lib.thread.ThreadPool;
import com.dzg.reward.BaseActivity;
import com.dzg.reward.R;
import com.dzg.reward.model.local.LoginInfo;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-11-8
 */

@TargetApi(9)
public class MineLoginActivity extends BaseActivity implements OnClickListener {
	private TextView btn_back = null;
	private TextView tv_title_name = null;
	
	private EditText user_name = null;
	private EditText password = null;
	private EditText check = null;
	private TextView login_btn = null;
	private TextView regist_btn = null;
	private String checkCodeUrl = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_mine_login);
		loadView();
	}
	
	private void loadView(){
		
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		
		tv_title_name = (TextView)findViewById(R.id.tv_title_name);
		tv_title_name.setText("登录");
		tv_title_name.setVisibility(View.VISIBLE);
		
		user_name = (EditText)findViewById(R.id.user_name);
		password = (EditText)findViewById(R.id.password);
		login_btn = (TextView)findViewById(R.id.login_btn);
		login_btn.setOnClickListener(this);
		regist_btn = (TextView)findViewById(R.id.regist_btn);
		regist_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		case R.id.login_btn:
			String name = user_name.getText().toString();
			String pass = password.getText().toString();
			// 请求登陆
			if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass))
			{
				showToast("请输入用户名和密码");
			}
			else
			{
				showLoading("","正在登录，请稍候", true);
				login(name,pass);
			}
			break;
		case R.id.regist_btn:
			break;
		}
	}
	
	/**
	 * 将InputStream转换成某种字符编码的String
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
     public static String InputStreamTOString(InputStream in,String encoding) throws Exception{
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int count = -1;
		while((count = in.read(data,0,1024)) != -1)
			outStream.write(data, 0, count);
		
		data = null;
		return new String(outStream.toByteArray(),encoding);
	}
	
	@Override
	protected void onActivityResult(int arg0, int resultCode, Intent data) {
		super.onActivityResult(arg0, resultCode, data);
		if(resultCode == Activity.RESULT_OK){
			setResult(resultCode, data);
			finish();
		}
	}
	
	/**
	 * 请求网络登录
	 * @param name 用户账户
	 * @param pass 用户密码
	 */
	private void login(final String name,final String pass)
	{
		ThreadPool.runInBackground(new NewRunnable(MineLoginActivity.this){
			@Override
			public void runInTryCatch()
			{
				LoginInfo.getInstances().setUserId(1L);
				LoginInfo.getInstances().setSid("2");
				LoginInfo.getInstances().setToken("3");
				LoginInfo.getInstances().setUserName("username");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hideLoading();
				setResult(Activity.RESULT_OK, getIntent());
				finish();
//				ComTaobaoClientSysGetAppTokenRequest tokenReq = new ComTaobaoClientSysGetAppTokenRequest();
//				tokenReq.setKey(name);
//				// 获取token
//				ComTaobaoClientSysGetAppTokenResponse tokenResp = (ComTaobaoClientSysGetAppTokenResponse)MtopHelper.getInstance().SyncRequestApi(tokenReq,ComTaobaoClientSysGetAppTokenResponse.class);
//				if(tokenResp!=null && tokenResp.getData()!=null)
//				{
//					AliLog.i("login","tokenResp=" + tokenResp);
//					String pubKey = tokenResp.getData().getPubKey();
//					String token = tokenResp.getData().getToken();
//					String checkCode = check.getText().toString();
//
//					ComTaobaoClientSysLoginRequest loginReq = new ComTaobaoClientSysLoginRequest();
//					loginReq.setToken(token);
//					loginReq.setUsername(name);
//					if (!TextUtils.isEmpty(checkCode))
//					{
//						loginReq.setCheckCode(checkCode);
//					}
//					try
//					{
//						RSAUtil.pubKey = RSAUtil.generateRSAPublicKey(pubKey);
//						loginReq.setPassword(new String(RSAUtil.encrypt(pass)));
//					}catch (Exception e1){}
//
//					// 登陆
//					ComTaobaoClientSysLoginResponse loginResp = (ComTaobaoClientSysLoginResponse)MtopHelper.getInstance().SyncRequestApi(loginReq,ComTaobaoClientSysLoginResponse.class);
//					if (loginResp!=null && loginResp.getData()!=null)
//					{
//						AliLog.i("login","loginResp=" + loginResp);
//						String res = loginResp.getRet()[0].split("::")[0];
//						
//						if (res.equals("USER_NOT_FOUND"))
//						{
//							showToast("该账号不存在.请先去淘宝注册~");
//						}
//						// 密码错误
//						else if (res.equals("ERROR_PASSWORD_NOT_MATCH"))
//						{
//							showToast("登录失败，请检查账号、密码.");
//						}
//						// 需要传验证码
//						else if (res.equals("ERROR_NEED_CHECK_CODE"))
//						{
//							showToast("出错太多次啦，需要输入验证码。");
//							findViewById(R.id.check_code_row).setVisibility(View.VISIBLE);
//							checkCodeUrl = loginResp.getData().getCheckCodeUrl();
//							ImageView img = (ImageView)findViewById(R.id.check_img);
//							refreashCheckCodeImg(checkCodeUrl,img);
//							img.setOnClickListener(LoginActivity.this);
//						}
//						else if (res.equals("1003"))
//						{
//							showToast("验证码错误.请重新输入");
//						}
//						else if (res.equals("1004"))
//						{
//							showToast("验证码超时.请重新输入");
//						}
//						else if (res.equals("SUCCESS"))// 登陆成功
//						{
//							String sid = loginResp.getData().getSid();
//							long userId = Long.valueOf(loginResp.getData().getUserId());
//							LoginInfo.getInstances().setUserId(userId);
//							LoginInfo.getInstances().setSid(sid);
//							LoginInfo.getInstances().setToken(token);
//							LoginInfo.getInstances().setUserName(name);
//							// 获取用户头像
//							GetUserInfoByUserIdRequest reqUserInfo = new GetUserInfoByUserIdRequest();
//							reqUserInfo.setUserId(userId);
//							GetUserInfoByUserIdResponse userInfoResp = (GetUserInfoByUserIdResponse)MtopHelper.getInstance().SyncRequestApi(reqUserInfo,GetUserInfoByUserIdResponse.class);
//							if(userInfoResp!=null && userInfoResp.getData()!=null)
//							{
//								LoginInfo.getInstances().setUserHeadUrl(userInfoResp.getData().getHeadUrl());
//							}
//							
//							//上传登陆用户信息
//							UTWrapper.updateLoginUserName(name);
//							bindAgooUser(sid);
//							if(isWIFI) // 只在wifi下后台同步
//							{
//								syncBackgroundData(userId);
//							}
//							setResult(Activity.RESULT_OK, getIntent());
//							finish();
//						}
//						else
//						{
//							showToast("出现未知错误，请联系管理员");
//						}
//					}
//					else
//					{
//						showToast("请重试，用户名或密码错误");
//					}
					// 隐藏加载框
//					hideLoading();
//				}else{
//					showToast("网络错误，请重试。");
//					// 隐藏加载框
//					hideLoading();
//				}
			}
		});
	}
}
