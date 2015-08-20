package com.costom.view.v4;

import com.costom.view.R;
import com.costom.view.v3.BaseView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class NumText extends BaseView{

	private Paint paint = new Paint();
	private int lineNum = 0;
	private int mx = 0;
	private boolean xScroll = false;
	
	public NumText(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumText);
		lineNum = ta.getInt(R.styleable.NumText_lineNum, 1);
		xScroll = ta.getBoolean(R.styleable.NumText_xScroll, false);
		ta.recycle();
	}

	public NumText(Context context) {
		super(context);
	}

	@Override
	protected void drawSub(Canvas canvas) {
		
		for(int i = 0; i < lineNum; i ++){
			int textSize = 30 + i;
			paint.setTextSize(textSize);
			canvas.drawText("����ѧԺ", mx, textSize + textSize * i, paint);
		}
	}

	@Override
	protected void logic() {
		
		if(xScroll){
			mx += 3;
			if(mx > getWidth()){
				mx = (int) -paint.measureText("����ѧԺ");
			}
		}
	}

}
