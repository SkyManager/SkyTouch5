package com.skyled.skytouch5.thirdpart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class buttonSwitcher extends View{

	Context context;
	Paint lines;
	Path path;
	RectF rectf;
	
	
	public buttonSwitcher(Context context) {
		super(context);
		this.context=context;
	}

	public void init()
	{
		lines=new Paint(Paint.ANTI_ALIAS_FLAG);
		lines.setColor(Color.GREEN);
		lines.setStrokeWidth(10);
		rectf = new RectF(700,100,800,150);
		
		 path = new Path();
	}
	
	
	protected void onDraw(Canvas canvas)
	{
		super.draw(canvas);
		canvas.drawArc(rectf, 10, 320, true, lines);
		
	}
}
