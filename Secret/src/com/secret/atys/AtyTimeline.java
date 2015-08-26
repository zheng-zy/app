package com.secret.atys;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.secret.Config;
import com.secret.MD5Tool;
import com.secret.R;
import com.secret.adp.AtyTimelineMessageListAdaper;
import com.secret.ld.LocalContacts;
import com.secret.md.Message;
import com.secret.net.TimeLine;
import com.secret.net.UploadContacts;

public class AtyTimeline extends ListActivity {

	private String phoneNumber;
	private String token;
	private AtyTimelineMessageListAdaper adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_timeline);

		adapter = new AtyTimelineMessageListAdaper(this);
		setListAdapter(adapter);

		token = getIntent().getStringExtra(Config.KEY_TOKEN);
		phoneNumber = getIntent().getStringExtra(Config.KEY_PHONE_NUM);
		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting),
				getResources().getString(R.string.connecting_to_server));
		new UploadContacts().uploadContacts(MD5Tool.md5(phoneNumber), token, LocalContacts.getContactsJsonString(this),
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
							Toast toast = Toast.makeText(getApplicationContext(), R.string.fail_to_upload_contacts,
									Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_aty_timeline, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuShowAtyPublish:
			Intent intent = new Intent(AtyTimeline.this, AtyPublish.class);
			intent.putExtra(Config.KEY_PHONE_MD5, MD5Tool.md5(phoneNumber));
			intent.putExtra(Config.KEY_TOKEN, token);
			startActivityForResult(intent, 0);
//			startActivity(intent);
			break;

		default:
			break;
		}
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case Config.ACTIVITY_RESULT_NEED_REFRESH:
			loadMessage();
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		System.out.println("onListItemClick");
		super.onListItemClick(l, v, position, id);
		Message message = (Message) adapter.getItem(position);
		Intent intent = new Intent(this, AtyMessage.class);
		intent.putExtra(Config.KEY_MSG_ID, message.getMsgId());
		intent.putExtra(Config.KEY_MSG, message.getMsg());
		intent.putExtra(Config.KEY_PHONE_MD5, message.getPhone_md5());
		intent.putExtra(Config.KEY_TOKEN, token);
		startActivity(intent);
	}

	private void loadMessage() {

		System.out.println("loadMessage");

		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting),
				getResources().getString(R.string.connecting_to_server));
		new TimeLine(MD5Tool.md5(phoneNumber), token, 1, 20, new TimeLine.SuccessCallBack() {

			@Override
			public void onSuccess(int page, int perpage, List<Message> messages) {
				pd.dismiss();
				adapter.clear();
				adapter.addAll(messages);
			}
		}, new TimeLine.FailCallBack() {

			@Override
			public void onFail(int errorCode) {
				pd.dismiss();
				if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN) {
					startActivity(new Intent(AtyTimeline.this, AtyLogin.class));
					finish();
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), R.string.fail_to_load_timeline_data,
							Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
			}
		});
	}

}
