package com.costom.view.v1;

import com.costom.view.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View{

	private Bitmap bitmap;
	
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
	}

	public MyView(Context context) {
		super(context);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		Paint paint = new Paint();
		paint.setTextSize(30);
		paint.setColor(0xffff0000);//a,r,g,b（透明度，红色，绿色，蓝色）
		canvas.drawText("this is onDraw", 0, 30, paint);
		
		canvas.drawLine(0, 60, 100, 60, paint);
		
		paint.setStyle(Style.STROKE);
		
		//通过坐标绘制矩形
//		canvas.drawRect(0, 90, 100, 190, paint);
		//通过Rect绘制矩形
//		Rect r = new Rect(0, 90, 100, 190);
//		canvas.drawRect(r, paint);
		//通过RectF绘制矩形
		RectF rect = new RectF(0, 90, 100, 190);
//		canvas.drawRect(rect, paint);
		//绘制圆角矩形
		canvas.drawRoundRect(rect, 10, 10, paint);
		
		//绘制圆形
		canvas.drawCircle(50, 270, 50, paint);
		
		canvas.drawBitmap(bitmap, 0, 350, paint);
		
	}
	
}
