package com.secret;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class Config {
	public static final String SERVER_URL = "http://demo.eoeschool.com/api/v1/nimings/io";

	public static final String KEY_TOKEN = "token";
	public static final String KEY_ACTION = "action";
	public static final String KEY_PHONE_NUM = "phone";
	public static final String KEY_STATUS = "status";
	
	public static final int RESULT_STATUS_SUCCESS = 1;
	public static final int RESULT_STATUS_FAIL = 0;
	public static final int RESULT_STATUS_INVALID_TOKEN = 2;

	public static final String APP_ID = "com.secret";
	public static final String CHARSET = "utf-8";

	public static final String ACTION_GET_CODE = "send_pass";

	/**
	 * 获取缓存在sharepreferences中的token
	 * 
	 * @param context
	 * @return
	 */
	public static String getCachedToken(Context context) {
		return context.getSharedPreferences(APP_ID, context.MODE_PRIVATE).getString(KEY_TOKEN, null);
	}

	/**
	 * 存储token
	 * 
	 * @param context
	 * @param token
	 */
	public static void cacheToken(Context context, String token) {
		Editor editor = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_TOKEN, token);
		editor.commit();

	}
}
