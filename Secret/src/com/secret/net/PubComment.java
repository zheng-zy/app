package com.secret.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.secret.Config;

/**
 * 
 * @author zhengzy
 * @data 2015年8月26日 下午1:54:58
 * @version V1.0
 * @describe 发表评论通信类
 */
public class PubComment {
	public PubComment(String phone_md5, String token, String content, String msgId,
			final SuccessCallBack successCallBack, final FailCallBack failCallBack) {
		new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallBack() {

			@Override
			public void onSuccess(String result) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					switch (jsonObject.getInt(Config.KEY_STATUS)) {
					case Config.RESULT_STATUS_SUCCESS:
						successCallBack.onSuccess();
						break;
					case Config.RESULT_STATUS_INVALID_TOKEN:
						if (failCallBack != null) {
							failCallBack.onFail(Config.RESULT_STATUS_INVALID_TOKEN);
						}
						break;
					default:
						if (failCallBack != null) {
							failCallBack.onFail(Config.RESULT_STATUS_FAIL);
						}
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
					if (failCallBack != null) {
						failCallBack.onFail(Config.RESULT_STATUS_FAIL);
					}
				}
			}
		}, new NetConnection.FailCallBack() {

			@Override
			public void onFail() {
				if (failCallBack != null) {
					failCallBack.onFail(Config.RESULT_STATUS_FAIL);
				}
			}
		}, Config.KEY_ACTION, Config.KEY_PUB_COMMENT, Config.KEY_TOKEN, token, Config.KEY_PHONE_MD5, phone_md5,
				Config.KEY_MSG_ID, msgId, Config.KEY_CONTENT, content);
	}

	public static interface SuccessCallBack {
		void onSuccess();
	}

	public static interface FailCallBack {
		void onFail(int errorCode);
	}
}
