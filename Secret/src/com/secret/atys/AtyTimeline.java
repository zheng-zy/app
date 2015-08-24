package com.secret.atys;

import com.secret.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class AtyTimeline extends Activity{
	
	private ListView lvList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_timeline);
		lvList = (ListView) findViewById(R.id.lvList);
	}
}
