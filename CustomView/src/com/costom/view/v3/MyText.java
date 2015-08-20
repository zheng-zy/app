package com.costom.view.v3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class MyText extends BaseView{

	private Paint paint = new Paint();
	private float rx = 0;
	
	public MyText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyText(Context context) {
		super(context);
	}

	@Override
	protected void drawSub(Canvas canvas) {
		paint.setTextSize(30);
		canvas.drawText("MyText", rx, 30, paint);
	}

	@Override
	protected void logic() {
		
		rx += 3;
		if(rx > getWidth()){
			rx = - paint.measureText("MyText");
		}
		
	}

}
