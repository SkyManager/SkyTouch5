package com.skyled.skytouch5.thirdpart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class RightPanel extends View {
	Context context;
	
	
	Paint circle;
 
    public RightPanel(Context context) {
        super(context);
        this.context=context;
        init();
    }   
    public RightPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
     
    
    public void init() {
    	circle= new Paint(Paint.ANTI_ALIAS_FLAG);
    	circle.setColor(Color.argb(45, 255, 255, 255));
    	circle.setStyle(Paint.Style.STROKE); 
    	circle.setShadowLayer(10, 0, 2, Color.argb(255, 255, 255, 255));
    	circle.setStrokeWidth(4);
    } 
    
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float Radius=getWidth()/7f;
        int xPosStart=getWidth()/2;
        float yPosStart=getHeight()/9.4f;
        for(int i=0; i<5;i++)
        canvas.drawCircle(xPosStart, yPosStart+(137*i), Radius, circle);
    } 

 }
    

