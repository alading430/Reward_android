package com.dzg.reward.model.local;

import java.util.Date;

import android.text.TextUtils;


public class LoginInfo extends BasePreferences
{
	// 表ID
	private Long ID = -1L;
	// 用户ID
	private Long userId = -1L;
	// 用户账户
	private String userName = null;
	// session id
	private String sid = null;
	// token
	private String token = null;
	private Date gmtCreated = null;
	private Date gmtModified = null;
	private String userHeadUrl = null;
	private static final LoginInfo ins = new LoginInfo();
	
	public static LoginInfo getInstances()
	{
		ins.setEditable(true);
		return ins;
	}

	public Long getID()
	{
		return ID;
	}

	public void setID(Long iD)
	{
		ID = iD;
	}

	public Long getUserId()
	{
		if(userId<0L)
		{
			userId = getLong("userId",-1L);
		}
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
		if(isEditable)
		{
			setLong("userId",userId);
		}
	}

	public String getUserName()
	{
		if(TextUtils.isEmpty(userName))
		{
			userName = getString("userName");
		}
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
		if(isEditable)
		{
			setString("userName",userName);
		}
	}

	public String getSid()
	{
		if(TextUtils.isEmpty(sid))
		{
			sid = getString("sid");
		}
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
		if(isEditable)
		{
			setString("sid",sid);
		}
	}

	public String getToken()
	{
		if(TextUtils.isEmpty(token))
		{
			token = getString("token");
		}
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
		if(isEditable)
		{
			setString("token",token);
		}
	}

	public Date getGmtCreated()
	{
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated)
	{
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtModified()
	{
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified)
	{
		this.gmtModified = gmtModified;
	}

	@Override
	protected String getPreferenceName()
	{
		return "IPIN_LOGININFO";
	}

	public String getUserHeadUrl()
	{
		if(TextUtils.isEmpty(userHeadUrl))
		{
			userHeadUrl = getString("userHeadUrl");
		}
		return userHeadUrl;
	}

	public void setUserHeadUrl(String userHeadUrl)
	{
		this.userHeadUrl = userHeadUrl;
		if(isEditable)
		{
			setString("userHeadUrl",userHeadUrl);
		}
	}

}
