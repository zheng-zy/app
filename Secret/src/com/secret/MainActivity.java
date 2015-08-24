package com.secret;

import com.secret.atys.AtyLogin;
import com.secret.atys.AtyTimeline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		String token = Config.getCachedToken(this);
		if (token != null){
			Intent intent = new Intent(this, AtyTimeline.class);
			intent.putExtra(Config.KEY_TOKEN, token);
			startActivity(intent);
		}else {
			startActivity(new Intent(this, AtyLogin.class));
		}
		finish();
	}

}
