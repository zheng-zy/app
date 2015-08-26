package com.secret.atys;

import java.util.List;

import com.secret.Config;
import com.secret.MD5Tool;
import com.secret.R;
import com.secret.adp.AtyMessageCommentListAdapter;
import com.secret.md.Comment;
import com.secret.net.GetComment;
import com.secret.net.PubComment;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AtyMessage extends ListActivity implements OnClickListener {

	private String phone_md5;
	private String msgId;
	private String msg;
	private String token;
	private AtyMessageCommentListAdapter adapter = null;

	private TextView tvMessage;
	private EditText etComment;
	private Button btSendComment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_message);
		initData();
		initView();
		initHttp();
	}

	private void initHttp() {
		getComments();
	}

	private void getComments() {
		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting),
				getResources().getString(R.string.connecting_to_server));
		new GetComment(phone_md5, token, msgId, 1, 20, new GetComment.SuccessCallBack() {

			@Override
			public void onSuccess(String msgId, int page, int perpage, List<Comment> comments) {
				pd.dismiss();
				adapter.clear();
				adapter.addAll(comments);
			}
		}, new GetComment.FailCallBack() {

			@Override
			public void onFail(int errorCode) {
				pd.dismiss();
				if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN) {
					startActivity(new Intent(AtyMessage.this, AtyLogin.class));
					finish();
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), R.string.fail_to_get_comments,
							Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
			}
		});
	}

	private void initView() {
		tvMessage = (TextView) findViewById(R.id.tvMessage);
		tvMessage.setText(msg);

		etComment = (EditText) findViewById(R.id.etComment);
		btSendComment = (Button) findViewById(R.id.btSendComment);
		btSendComment.setOnClickListener(this);

		adapter = new AtyMessageCommentListAdapter(this);
		setListAdapter(adapter);
	}

	private void initData() {
		Intent data = getIntent();
		phone_md5 = data.getStringExtra(Config.KEY_PHONE_MD5);
		msgId = data.getStringExtra(Config.KEY_MSG_ID);
		msg = data.getStringExtra(Config.KEY_MSG);
		token = data.getStringExtra(Config.KEY_TOKEN);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btSendComment:
			if (TextUtils.isEmpty(etComment.getText())) {
				Toast toast = Toast.makeText(getApplicationContext(), R.string.comment_content_cant_be_empty,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
			sendCommentHttp(etComment.getText().toString());
			break;
		default:
			break;
		}

	}

	private void sendCommentHttp(String content) {

		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting),
				getResources().getString(R.string.connecting_to_server));
		new PubComment(MD5Tool.md5(Config.getCachedValue(AtyMessage.this, Config.KEY_PHONE_NUM)), token, content,
				msgId, new PubComment.SuccessCallBack() {

					@Override
					public void onSuccess() {
						pd.dismiss();
						Toast toast = Toast.makeText(getApplicationContext(), R.string.success_to_pub_comment_content,
								Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
						etComment.setText("");
						getComments();
					}
				}, new PubComment.FailCallBack() {

					@Override
					public void onFail(int errorCode) {
						pd.dismiss();
						if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN) {
							startActivity(new Intent(AtyMessage.this, AtyLogin.class));
							finish();
						} else {
							Toast toast = Toast.makeText(getApplicationContext(), R.string.fail_to_pub_comment_content,
									Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
						}
					}
				});
	}
}
