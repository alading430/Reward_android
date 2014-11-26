/*
 * Copyright 1999-2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.dzg.reward.model.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dzg.reward.RewardApplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public abstract class BasePreferences
{
	public static CharSequence DEFAULT_DELIMITER = ":";
	protected abstract String getPreferenceName();
	protected boolean isEditable = false;
	
	private SharedPreferences getSharedPreferences()
	{
		SharedPreferences sp = RewardApplication.getInstance().getSharedPreferences(getPreferenceName(),Context.MODE_PRIVATE);
		return sp;
	}
	
	public void setEditable(boolean isEditable)
	{
		this.isEditable = isEditable;
	}

	/**
	 * Remove the specified key-value from shared preferences
	 * 
	 * @param key
	 * @return
	 */
	public boolean remove(String key)
	{
		SharedPreferences sp = getSharedPreferences();
		Editor editor = sp.edit();
		editor.remove(key);
		return editor.commit();
	}

	/**
	 * Add the string list support, this value is append to the end of the
	 * specified key-value Because of the setStringSet is supported by API Level
	 * 11
	 * 
	 * @see #setStringSet(String, Set)
	 * @param key
	 * @param value
	 * @param delimiter
	 */
	public void addStringList(String key,String value,CharSequence delimiter)
	{
		if (TextUtils.isEmpty(value))
			return;

		List<String> list = getStringList(key,delimiter);
		if (list == null)
			list = new ArrayList<String>();

		if (list.contains(value))
			return;

		List<String> newList = new ArrayList<String>();
		newList.addAll(list);
		newList.add(value);

		String compositeString = TextUtils.join(delimiter,newList);
		setString(key,compositeString);
	}

	/**
	 * Using the default delimiter
	 * 
	 * @param key
	 * @param value
	 */
	public void addStringList(String key,String value)
	{
		addStringList(key,value,DEFAULT_DELIMITER);
	}

	/**
	 * Add the string list support, split the string to list Because of the
	 * getStringSet is supported by API Level 11
	 * 
	 * @see #getStringSet(String, Set)
	 * @param key
	 * @return
	 */
	public List<String> getStringList(String key,CharSequence delimiter)
	{
		String value = getString(key);
		if (TextUtils.isEmpty(value))
			return null;

		String[] values = value.split(delimiter.toString());
		return Arrays.asList(values);
	}

	/**
	 * Using the default delimiter
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getStringList(String key)
	{
		return getStringList(key,DEFAULT_DELIMITER);
	}

	public void setLong(String key,long value)
	{
		SharedPreferences sp = getSharedPreferences();
		Editor editor = sp.edit();
		editor.putLong(key,value);

		editor.commit();
	}

	public long getLong(String key,long defValue)
	{
		SharedPreferences sp = getSharedPreferences();
		long value = sp.getLong(key,defValue);
		return value;
	}

	public long getLong(String key)
	{
		return getLong(key,0L);
	}

	public void setStringSet(String key,Set<String> values)
	{
		SharedPreferences sp = getSharedPreferences();
		Editor editor = sp.edit();
		editor.putStringSet(key,values);
		editor.commit();
	}

	public Set<String> getStringSet(String key,Set<String> defValues)
	{
		SharedPreferences sp = getSharedPreferences();
		Set<String> values = sp.getStringSet(key,defValues);
		return values;
	}

	public Set<String> getStringSet(String key)
	{
		return getStringSet(key,new HashSet<String>());
	}

	public void setFloat(String key,float value)
	{
		SharedPreferences sp = getSharedPreferences();
		Editor editor = sp.edit();
		editor.putFloat(key,value);
		editor.commit();
	}

	public float getFloat(String key,float defValue)
	{
		SharedPreferences sp = getSharedPreferences();
		float value = sp.getFloat(key,defValue);
		return value;
	}

	public float getFloat(String key)
	{
		return getFloat(key,0.0f);
	}

	public void setBoolean(String key,boolean value)
	{
		SharedPreferences sp = getSharedPreferences();
		Editor editor = sp.edit();
		editor.putBoolean(key,value);
		editor.commit();
	}

	public boolean getBoolean(String key,boolean defValue)
	{
		try
		{
			SharedPreferences sp = getSharedPreferences();
			boolean value = sp.getBoolean(key,defValue);
			return value;
		}
		catch (Exception e)
		{
		}
		return defValue;
	}

	public Boolean getBoolean(String key)
	{
		return getBoolean(key,false);
	}

	public void setInt(String key,int value)
	{
		SharedPreferences sp = getSharedPreferences();
		Editor editor = sp.edit();
		editor.putInt(key,value);
		editor.commit();
	}

	public int getInt(String key,int defValue)
	{
		SharedPreferences sp = getSharedPreferences();
		int value = sp.getInt(key,defValue);
		return value;
	}

	public int getInt(String key)
	{
		return getInt(key,0);
	}

	public void setString(String key,String value)
	{
		SharedPreferences sp = getSharedPreferences();
		Editor editor = sp.edit();
		editor.putString(key,value);
		editor.commit();
	}

	public String getString(String key,String defValue)
	{
		SharedPreferences sp = getSharedPreferences();
		String value = sp.getString(key,defValue);
		return value;
	}

	public String getString(String key)
	{
		return getString(key,"");
	}
}
