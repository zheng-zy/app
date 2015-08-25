package com.secret.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.secret.Config;

public class UploadContacts {

	public void uploadConatcts(String phone_md5, String token, String contacts, final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {
		new NetConnection(Config.SERVER_URL, HttpMethod.GET, new NetConnection.SuccessCallBack() {

			@Override
			public void onSuccess(String result) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					switch (jsonObject.getInt(Config.KEY_STATUS)) {
					case Config.RESULT_STATUS_SUCCESS:
						if (successCallBack != null) {
							successCallBack.onSuccess();
						}
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
		}, Config.KEY_ACTION, Config.ACTION_UPLOAD_CONTACTS, Config.KEY_PHONE_MD5, phone_md5, Config.KEY_TOKEN, token,
				Config.KEY_CONTACTS, contacts);
	}

	public static interface SuccessCallBack {
		void onSuccess();
	}

	public static interface FailCallBack {
		void onFail(int errorCode);
	}

}
