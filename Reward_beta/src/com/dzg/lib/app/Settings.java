package com.dzg.lib.app;


public class Settings
{
	/**
	 * 是否是debug(例如打开log)
	 */
	public static boolean IS_SHOW_DEBUG = true;
	
	public static final String OS_VERSION_PREFIX = "android:";
	public static final String MODEL_PRE = "_model_";
	
	public static final int POWER_MODEL_AUTO = -1;// 智能模式
	public static final int POWER_MODEL_LOW = 1; // 极简模式
	public static final int POWER_MODEL_MIDDLE = 2;// 流畅模式
	public static final int POWER_MODEL_HIGHT = 3; // 高清模式

}
