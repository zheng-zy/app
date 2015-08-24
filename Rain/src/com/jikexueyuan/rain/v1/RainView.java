package com.jikexueyuan.rain.v1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class RainView extends BaseView{

	private float startX;
	private float startY;
	private float stopX;
	private float stopY;
	private float sizeX;
	private float sizeY;
	private Paint paint;
	
	public RainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		sizeX = 10;
		sizeY = 30;
		startX = 100;
		startY = 0;
		stopX = startX + sizeX;
		stopY = startY + sizeY;
		
		paint = new Paint();
		paint.setColor(0xffffffff);
	}

	public RainView(Context context) {
		super(context);
	}

	@Override
	protected void drawSub(Canvas canvas) {
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}

	@Override
	protected void logic() {
		
		float opt = 0.5f;
		
		startX += sizeX * opt;
		stopX += sizeX * opt;
		
		startY += sizeY * opt;
		stopY += sizeY * opt;
		
		
		if(startY > getHeight()){
			startX = 100;
			startY = 0;
			stopX = startX + 10;
			stopY = startY + 30;
		}
	}

}
