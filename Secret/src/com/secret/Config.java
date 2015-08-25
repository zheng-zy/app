package com.secret;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class Config {
	// public static final String SERVER_URL = "http://demo.eoeschool.com/api/v1/nimings/io";
	public static final String SERVER_URL = "http://192.168.1.101:8080/SecretServer/api.jsp";

	public static final String KEY_TOKEN = "token";
	public static final String KEY_ACTION = "action";
	public static final String KEY_PHONE_NUM = "phone";
	public static final String KEY_STATUS = "status";
	public static final String KEY_PHONE_MD5 = "phone_md5";
	public static final String KEY_CODE = "code";
	public static final String KEY_CONTACTS = "contacts";
	public static final String KEY_PAGE = "page";
	public static final String KEY_PREPAGE = "perpage";
	public static final String KEY_TIMELINE = "timeline";
	public static final String KEY_MSG_ID = "msgId";
	public static final String KEY_MSG = "msg";
	public static final String KEY_ITEMS = "items";

	public static final int RESULT_STATUS_SUCCESS = 1;
	public static final int RESULT_STATUS_FAIL = 0;
	public static final int RESULT_STATUS_INVALID_TOKEN = 2;

	public static final String APP_ID = "com.secret";
	public static final String CHARSET = "utf-8";

	public static final String ACTION_GET_CODE = "send_pass";
	public static final String ACTION_LOGIN = "login";
	public static final String ACTION_UPLOAD_CONTACTS = "upload_contacts";
	public static final String ACTION_TIMELINE = "timeline";




	/**
	 * 鑾峰彇缂撳瓨鍦╯harepreferences涓殑閿�瀵�
	 * @param context
	 * @param key		閿�锛欿EY_TOKEN  KEY_PHONE_NUM
	 * @return
	 */
	 
	public static String getCachedValue(Context context, String key) {
		return context.getSharedPreferences(APP_ID, context.MODE_PRIVATE).getString(key, null);
	}

	/**
	 * 瀛樺偍sharepreferences涓殑閿�瀵�
	 * @param context
	 * @param key		閿�锛欿EY_TOKEN  KEY_PHONE_NUM
	 * @param value
	 */
	 
	public static void cacheValue(Context context, String key, String value) {
		Editor editor = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		editor.putString(key, value);
		editor.commit();

	}
}
