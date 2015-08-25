package com.secret.ld;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.secret.Config;
import com.secret.MD5Tool;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;

public class LocalContacts {
	private static final String[] PHONES_PROJECTION = new String[] { Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID,
			Phone.CONTACT_ID };
	/**
	 * 获取本地联系人
	 * @param context
	 * @return
	 */
	public static String getContactsJsonString(Context context) {
		Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
		String phoneNumber;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject;
		while (cursor.moveToNext()) {
			phoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
			if (phoneNumber.charAt(0) == '+' && phoneNumber.charAt(1) == '8' && phoneNumber.charAt(2) == '6') {
				phoneNumber = phoneNumber.substring(3);
			}
			jsonObject = new JSONObject();
			try {
				jsonObject.put(Config.KEY_PHONE_NUM, MD5Tool.md5(phoneNumber));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObject);
			System.out.println(phoneNumber);
		}
		return jsonArray.toString();
	}
}
