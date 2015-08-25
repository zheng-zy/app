package com.secret;

import com.secret.atys.AtyLogin;
import com.secret.atys.AtyTimeline;
import com.secret.ld.LocalContacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		System.out.println(LocalContacts.getContactsJsonString(this));
		String token = Config.getCachedValue(this, Config.KEY_TOKEN);
		String phoneNumber = Config.getCachedValue(this, Config.KEY_PHONE_NUM);
		if (token != null && phoneNumber != null){
			Intent intent = new Intent(this, AtyTimeline.class);
			intent.putExtra(Config.KEY_TOKEN, token);
			intent.putExtra(Config.KEY_PHONE_NUM, phoneNumber);
			startActivity(intent);
		}else {
			startActivity(new Intent(this, AtyLogin.class));
		}
		finish();
	}

}
