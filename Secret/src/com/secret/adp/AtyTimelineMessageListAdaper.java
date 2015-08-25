package com.secret.adp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.secret.R;

public class AtyTimelineMessageListAdaper extends BaseAdapter {

	private List<Message> messages = new ArrayList<Message>();
	private Context context = null;

	public void clear() {
		this.messages.clear();
		notifyDataSetChanged();
	}

	public void addAll(List<Message> messages) {
		this.messages.addAll(messages);
		notifyDataSetChanged();
	}

	public Context getContext() {
		return context;
	}

	public AtyTimelineMessageListAdaper(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return this.messages.size();
	}

	@Override
	public Object getItem(int position) {
		return this.messages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// listview 优化
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.aty_timeline_list, null);
			holder = new ViewHolder();
			holder.msg = (TextView) convertView.findViewById(R.id.tvMsg);
			holder.msgId = (TextView) convertView.findViewById(R.id.tvMsgId);
			holder.msg.setText(this.messages.get(position).getMsg());
			holder.msgId.setText(this.messages.get(position).getMsgId());
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			holder.msg.setText(this.messages.get(position).getMsg());
			holder.msgId.setText(this.messages.get(position).getMsgId());
		}

		return convertView;
	}

	/**
	 * listview 优化
	 * 
	 * @author Administrator
	 * 
	 */
	private static class ViewHolder {
		TextView msg;
		TextView msgId;
	}

}
