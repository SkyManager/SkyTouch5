package com.skyled.skytouch5.thirdpart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ChanalView extends View {
	Context context;
	Paint circle;
	Paint plane;
	Paint clear;
	Paint chanal_color_circle;
	Paint textValuePaint;
	Paint textNamePaint;
	Typeface eur;
	String name;
	String value;
	
	int ChanalColor;
	int ChanalBack;
	int NameTextColor;
	
	boolean chanalstatus=false;
	
    public ChanalView(Context context) {
        super(context);
        this.context=context;
        init();
    }   
    public ChanalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    
    
    public void init() {
    	
    	ChanalColor=Color.BLUE;
    	ChanalBack=Color.parseColor("#40ffffff"); //cc
    	NameTextColor=Color.BLACK;
    	
    	name="Канал 1";
    	value="10";
    	
    	Typeface eur= Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
    	textNamePaint= new Paint(Paint.ANTI_ALIAS_FLAG);
    	textNamePaint.setColor(NameTextColor);
    	textNamePaint.setTextSize(22);
    	textNamePaint.setTypeface(eur);
    	
    	textValuePaint= new Paint(Paint.ANTI_ALIAS_FLAG);
    	textValuePaint.setColor(Color.BLACK);
    	textValuePaint.setTextSize(22);
    	textValuePaint.setTypeface(eur);
    	
    	circle= new Paint(Paint.ANTI_ALIAS_FLAG);
    	circle.setColor(Color.GRAY);
    	circle.setStyle(Paint.Style.STROKE); 
    	circle.setStrokeWidth(4);
    	
    	plane= new Paint(Paint.ANTI_ALIAS_FLAG);
    	plane.setColor(ChanalBack);
    	plane.setStyle(Paint.Style.FILL_AND_STROKE); 
    	
    	clear=new Paint(Paint.ANTI_ALIAS_FLAG);
    	clear.setStyle(Style.FILL_AND_STROKE);
    	clear.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
    	
    	chanal_color_circle=new Paint(Paint.ANTI_ALIAS_FLAG);
    	chanal_color_circle.setStyle(Style.FILL_AND_STROKE);
    	chanal_color_circle.setColor(ChanalColor);
 
  
    } 
    
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth();
        int radius=(width/4)/5;
        textNamePaint.setTextSize(radius);
        textValuePaint.setTextSize(radius/1.5f);
        plane.setColor(ChanalBack);
        canvas.drawRoundRect(new RectF(0, 0, 10, width/4.8f), 5, 5, plane);
        canvas.drawRect(new RectF(5, 0, 10, width/4.8f), clear);
        canvas.drawRect(new RectF((width/3)*2, 0, width, width/4), plane);
        canvas.drawCircle((width/3)*2+((width-(width/3)*2)/2), (width/4)/2.5f, radius-1, circle);
        canvas.drawRect(new RectF(5, 0, (width/3)*2-width/190, width/4), plane); 
        canvas.drawCircle((width/3)+((width-(width/3)*2)/2), (width/4)/2.5f, radius+2, clear);
        canvas.drawCircle((width/3)+((width-(width/3)*2)/2), (width/4)/2.5f, radius-2, chanal_color_circle);
        canvas.drawText(name, (width/15), (width/4)/2.5f+radius/3, textNamePaint);
        canvas.drawText(value, (width/3)*2+((width-(width/3)*2)/2)-(value.length()*radius/3.2f), (width/4)/2.5f+radius/4, textValuePaint);
    } 
    
    public void setChanalName(String name)
    {
    	this.name=name;
    	invalidate();
    }
    public void setChanalValue(int value)
    {
    	this.value=value+"";
    	invalidate();
    }
    
    public int getChanalValue()
    {
    	return Integer.parseInt(this.value);
    }
    
    public void setChanalIndicator(int color)
    {
    	this.ChanalColor=color;
    	chanal_color_circle.setColor(ChanalColor);
    	invalidate();
    }
    public void enableChanal()
    {
    	this.ChanalBack=Color.parseColor("#CCffffff");
    	this.NameTextColor=Color.WHITE;
    	chanalstatus=true;
    	invalidate();
    }
    public void disableChanal()
    {
    	this.ChanalBack=Color.parseColor("#40ffffff");
    	this.NameTextColor=Color.WHITE;
    	chanalstatus=false;
    	invalidate();
    }
    public boolean getChanalStatus()
    {
    	return this.chanalstatus;
    }

 }
