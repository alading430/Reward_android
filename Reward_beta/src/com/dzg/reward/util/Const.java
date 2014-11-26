package com.dzg.reward.util;

import java.io.File;

import android.os.Environment;

//服务端或本地磁盘地址静态变量
public final class Const {
	public final static String AVATAR_URL_POSTFIX = "&width=80&height=80&type=sns";
	public final static File sd = Environment.getExternalStorageDirectory();
	public final static String apkSavepath = sd.getPath() + "/reward/";
	public final static Integer REQ_CODE_MODIFY = 0;
	public final static Integer REQ_CODE_ADD = 1;
	public final static Integer REQ_CODE_USERINFO_ADD = 2;
	public final static Integer REQ_CODE_ADDRESS_ADD = 3;
	public final static Integer REQ_CODE_ADDRESS_MODIFY = 9;
	public final static Integer REQ_CODE_GET_CURRENT_LOCATION = 4;
	public final static Integer REQ_CODE_LOGIN = 5;
	public final static Integer REQ_CODE_MODIFY_ORDER = 6; // 团长修改拼单
	public final static Integer REQ_CODE_MODIFY_ORDER_JOIN = 7; // 修改加入拼单
	public final static Integer REQ_CODE_JOIN_ORDER = 8; // 加入拼单
	public final static Integer REQ_CODE_SCAN_OFFER = 9; // 二维码扫描
	public final static Integer RESULT_CODE_LOGIN_SUCCESS = 0;
	public final static Integer RESULT_CODE_LOGIN_CANCEL = 1;
	public final static Integer RESULT_CODE_SUCCESS = 0;
	public final static Integer RESULT_CODE_CANCEL = 1;

	// 本地数据库相关常量
	public final static String DB_NAME = "reward.db";
	public final static Integer DB_VERSION = 1;
	public final static String ADDRESS_TABLE_NAME = "reward_receiver_address";
	public final static String USERINFO_TABLE_NAME = "reward_receiver_info";
	public final static String CATEGORY_TABLE_NAME = "reward_category";
	public final static String LOGIN_TABLE_NAME = "reward_login";
	public final static String MESSAGE_TABLE_NAME = "reward_agoo_message";
	public final static String WHERE_CLAUSE_ALLLINE = "1";
	public final static Integer IS_DELETED = 1;
	public final static Integer IS_DEFAULT = 1;
	public final static Integer IS_NOT_DEFAULT = 0;
	public final static Integer IS_SELECTED = 1;
	public final static Integer IS_NOT_SELECTED = 0;

	// SHAREDPREFERENCES
	public final static String SHAREDPREFERENCES_NAME_LOCATION = "current_location";

	// 消息相关
	public static int MSG_GOT_ADDRESS = 1;
	public static int MSG_GOT_CATEGORY = 10;
	public static int MSG_GOT_LOGINED = 20;
	public static int MSG_WAIT_CREATE_ORDER = 30;
	public static int MSG_GO_HOME = 1000;
	public static int MSG_GO_LOGIN = 1001;
	public static int MSG_REFRESH_CHAT = 2000;
	public static int WAIT_TIME_FOR_MSG = 1000;
	public static int ALL_MSG = 0; // 拉取全部消息
	public static int MSG_COUNT = 10; // 每次拉取的消息条数
	public static int MSG_TYPE_SYSTEM_MSG = 0; // 群发的系统消息类型
	public static int MSG_TYPE_TUANZHANG_MSG = 1; // 群发的团长消息类型
	public static int MSG_TYPE_USER_MSG = 2; // 单聊消息类型

	// 首页相关
	public static String CURRENT_LOCATION = "当前";
	public static String ALL_PINDAN_LIST = "全部";
	// 首页抽屉相关
	public static int DRAWER_WIDTH = 240;

	// 修改我的地址相关
	public static int NEED_CHANGE_ORDER = 0; // 需要将缺省地址排在第一个
	public static int NO_NEED_CHANGE_ORDER = 1; // 不需要将缺省地址排在第一个
	public static int NICK_MAX_LENGTH = 4; // 地址昵称的最大长度，4个中文字
	public static int NAME_MAX_LENGTH = 4; // 名字的最大长度，4个中文字
	public static int PHONE_MAX_LENGTH = 11; // 名字的最大长度，11位的电话

	// 退出按键
	public static int WAIT_TIME = 2000;

	// 拼单状态相关
	public static int ORDER_STATUS_FAILURE = -1; // 成团失败
	public static int ORDER_STATUS_ONGOING = 0; // 成团中
	public static int ORDER_STATUS_SUCCESS = 1; // 成团成功

	// 系统报错文案
	public static String SYSTEM_ERROR_MESSAGE = "亲，系统好像有点问题耶。消消气，休息一下，我去打开发GG的屁股哈。";
	public static String OFFER_PICTURE_DEFAULT_URL = "/resources/img/no_pic.png?x=150x150"; // 商品缺省图片

	// 推送相关
	public static int PUSH_ON = 0; // 进行推送
	public static int PUSH_OFF = 1; // 不进行推送

	// 时间相关
	public static int LOGIN_TIME_LIMIT = 10000; // 登录超时限制

	// 公司认证相关
	public static int NO_AUTH = -1; // 未认证
	public static int AUTH_PROCESS = 0; // 认证审核中
	public static int AUTH_SUCCESS = 1; // 审核通过

}