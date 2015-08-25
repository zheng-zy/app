package com.secret.atys;

import java.util.List;

import org.json.JSONArray;

import com.secret.Config;
import com.secret.MD5Tool;
import com.secret.R;
import com.secret.adp.AtyTimelineMessageListAdaper;
import com.secret.adp.Message;
import com.secret.ld.LocalContacts;
import com.secret.net.TimeLine;
import com.secret.net.UploadContacts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

public class AtyTimeline extends Activity {

	private ListView lvList;
	private String phoneNumber;
	private String token;
	private AtyTimelineMessageListAdaper adaper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_timeline);

		adaper = new AtyTimelineMessageListAdaper(this);

		lvList = (ListView) findViewById(R.id.lvList);

		token = getIntent().getStringExtra(Config.KEY_TOKEN);
		phoneNumber = getIntent().getStringExtra(Config.KEY_PHONE_NUM);
		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting),
				getResources().getString(R.string.connecting_to_server));
		new UploadContacts().uploadConatcts(MD5Tool.md5(phoneNumber), token, LocalContacts.getContactsJsonString(this),
				new UploadContacts.SuccessCallBack() {

					@Override
					public void onSuccess() {
						loadMessage();
						pd.dismiss();
					}

				}, new UploadContacts.FailCallBack() {

					@Override
					public void onFail(int errorCode) {
						pd.dismiss();
						if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN) {
							startActivity(new Intent(AtyTimeline.this, AtyLogin.class));
							finish();
						} else {

						}
					}
				});
	}

	private void loadMessage() {

		System.out.println("loadMessage");

		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting),
				getResources().getString(R.string.connecting_to_server));
		new TimeLine(MD5Tool.md5(phoneNumber), token, 1, 20, new TimeLine.SuccessCallBack() {

			@Override
			public void onSuccess(int page, int perpage, List<Message> messages) {
				pd.dismiss();
				System.out.println("messages:"+messages.size());
				adaper.addAll(messages);
				lvList.setAdapter(adaper);
				System.out.println("getCount:" + adaper.getCount());
//				Message message = (Message) adaper.getItem(1);
//				System.out.println("message:" + message.getMsg());
			}
		}, new TimeLine.FailCallBack() {

			@Override
			public void onFail(int errorCode) {
				pd.dismiss();
				Toast toast = Toast.makeText(getApplicationContext(), R.string.fail_to_load_timeline_data,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	}
}
