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
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PresetView extends View {
	Context context;
	
	ActivePresetListener activatePresetListener;
	
	
	Paint circle;
	Paint plane;
	Paint planeActive;
	Paint planePassive;
	Paint clear;
	Paint devider;
	Paint chanal_color_circle;
	Paint textTimePaint;
	Paint textNamePaint;
	Typeface eur;
	String name;
	String time;
	Paint toggle;
	
	Paint clock;
	
	int ChanalColor;
	int ChanalBack;
	int NameTextColor;
	private boolean on_off=false;
	float togglePosStartX, togglePosStartY, togglePosStopX,togglePosStopY, planeStart, planeStop,planeStartY,planeStopY;
	
	boolean init=true;
	boolean mainmode=true;
	int indicators[]={Color.BLUE,Color.BLUE ,Color.BLUE,Color.BLUE , Color.BLUE};
	
	
    public PresetView(Context context) {
        super(context);
        this.context=context;
        init();
    }   
    public PresetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    
    
    public void init() {
    	
    	
    	ChanalColor=Color.BLUE;
    	ChanalBack=Color.parseColor("#40ffffff"); //cc
    	NameTextColor=Color.BLACK;
    	name="ðåñåò 1";
    	time="00:00 - 00:00";
    	
    	
    	Typeface eur= Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
    	textNamePaint= new Paint(Paint.ANTI_ALIAS_FLAG);
    	textNamePaint.setColor(NameTextColor);
    	textNamePaint.setTextSize(22);
    	textNamePaint.setTypeface(eur);
    	
    	textTimePaint= new Paint(Paint.ANTI_ALIAS_FLAG);
    	textTimePaint.setColor(Color.BLACK);
    	textTimePaint.setTextSize(22);
    	textTimePaint.setTypeface(eur);
    	
    	circle= new Paint(Paint.ANTI_ALIAS_FLAG);
    	circle.setColor(Color.GRAY);
    	circle.setStyle(Paint.Style.STROKE); 
    	circle.setStrokeWidth(4);
    	
    	toggle=new Paint(Paint.ANTI_ALIAS_FLAG);
    	toggle.setColor(Color.parseColor("#40000000"));
    	toggle.setStyle(Paint.Style.FILL_AND_STROKE); 
    	toggle.setStrokeWidth(4);
    	
    	devider= new Paint(Paint.ANTI_ALIAS_FLAG);
    	devider.setColor(Color.GRAY);
    	devider.setStrokeWidth(2);
    	devider.setStyle(Paint.Style.STROKE); 
    	
    	plane= new Paint(Paint.ANTI_ALIAS_FLAG);
    	plane.setColor(ChanalBack);
    	plane.setStyle(Paint.Style.FILL_AND_STROKE); 
    	
    	planeActive= new Paint(Paint.ANTI_ALIAS_FLAG);
    	planeActive.setColor(Color.RED);
    	planeActive.setStyle(Paint.Style.FILL_AND_STROKE); 
    	
    	planePassive= new Paint(Paint.ANTI_ALIAS_FLAG);
    	planePassive.setColor(Color.parseColor("#40000000"));
    	planePassive.setStyle(Paint.Style.FILL_AND_STROKE); 
    	
    	clear=new Paint(Paint.ANTI_ALIAS_FLAG);
    	clear.setStyle(Style.FILL_AND_STROKE);
    	clear.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
    	
    	chanal_color_circle=new Paint(Paint.ANTI_ALIAS_FLAG);
    	chanal_color_circle.setStyle(Style.FILL_AND_STROKE);
    	chanal_color_circle.setColor(ChanalColor);
 
    	clock= new Paint(Paint.ANTI_ALIAS_FLAG);
    	clock.setColor(Color.BLACK);
    	clock.setStyle(Paint.Style.STROKE); 
    	clock.setStrokeWidth(3);
    } 
    
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth();
        int radius=(width/4)/5;
        textNamePaint.setTextSize(radius/1.5f);
        textTimePaint.setTextSize(radius);
        
        if(on_off)
        	plane.setColor(Color.parseColor("#CCffffff"));
        else
        	plane.setColor(Color.parseColor("#40ffffff"));
        	
        canvas.drawRoundRect(new RectF(0, 0, 10, width/4.8f), 5, 5, plane);
        canvas.drawRect(new RectF(5, 0, 10, width/4.8f), clear);
        
        canvas.drawRect(new RectF((width/3)*2, 0, width, width/4), plane);
        canvas.drawRect(new RectF(5, 0, (width/3)*2-width/190, width/4), plane); 
        
        canvas.drawLine(width/10, width/15, width/10+width/2, width/15, devider);
        canvas.drawLine(width/10, width/7, width/10+width/2, width/7, devider);
        
        canvas.drawCircle(width/18, width/9.5f, radius/1.5f, clock);
        clock.setStrokeWidth(2);
        canvas.drawLine(width/18, width/9.5f, width/18+radius/1.5f-radius/4, width/9.5f-radius/7, clock);
        clock.setStrokeWidth(1);
        canvas.drawLine(width/18, width/9.5f, width/18, width/9.5f-radius/1.9f, clock); 
        canvas.drawText(name, width/8, width/17.6f, textNamePaint);
        canvas.drawText(time, width/8, width/7.8f, textTimePaint);
        
//        	canvas.drawCircle(width/6.5f+(radius*1.8f)*i, width/5.6f, radius/2, clear);
//        	chanal_color_circle.setColor(indicators[i]);
//        	canvas.drawCircle(width/6.5f+(radius*1.8f)*i, width/5.6f, radius/2.7f, chanal_color_circle);

        // Initial parameter for toggle
        if(init){
		        togglePosStartX=width/1.23f;
		        togglePosStopX=width/1.15f;
		        togglePosStopY=width/9.8f;
		        togglePosStartY=width/9.8f;
		        planeStart=width/1.2f;
		        planeStop=width/1.29f;
		        planeStartY=width/11f;
		        planeStopY=width/8.9f;
		        init=false;
        }
    
        if(mainmode){
        canvas.drawRoundRect(new RectF(width/1.29f, width/16,
					width/1.097f, width/7), 25, 25, planePassive);
        if(!on_off){
        toggle.setColor(Color.parseColor("#40ffffff"));
        canvas.drawRoundRect(new RectF(width/1.29f, width/16, planeStop, width/7), 25, 25, planePassive);
        canvas.drawCircle(togglePosStartX, width/9.8f, width/29f, toggle);
        canvas.drawCircle(togglePosStartX, width/9.8f, width/32f, plane); 
        canvas.drawCircle(width/1.14f, width/9.8f, width/70f, devider);
        	}else{
             toggle.setColor(Color.WHITE); 
             planeActive.setColor(Color.RED);
           	// canvas.drawRoundRect(new RectF(planeStart, width/16, planeStop, width/7), 25, 25, planeActive);
             canvas.drawRoundRect(new RectF(planeStart, planeStartY, planeStop, planeStopY), 25, 25, planeActive);
           	 canvas.drawCircle(togglePosStopX, togglePosStopY, width/32f, toggle);   //on
           	 canvas.drawLine(width/1.24f, width/11, width/1.24f, width/9, toggle);
        	} 
        }
    } 
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:	
			if(event.getX()>=getWidth()/1.3f && event.getX()<getWidth()/1.097f 
					&& event.getY()>=getWidth()/16 && event.getY()<getWidth()/7)
			{
				if(this.on_off==true){
					if(mainmode)
					deactivation();
					if(activatePresetListener!=null) activatePresetListener.onActivate();
				}else{
					if(mainmode)
					activation();
					if(activatePresetListener!=null) activatePresetListener.onActivate();
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		}

		return true;
	}
    
    public void setPresetName(String name)
    {
    	this.name=name;
    	invalidate();
    }
    public void setPresetTime(String time)
    {
    	this.time=time+"";
    	invalidate();
    }
    public void setChanalIndicator(int color)
    {
    	this.ChanalColor=color;
    	chanal_color_circle.setColor(ChanalColor);
    	invalidate();
    }
    public void enablePreset()
    {
    	this.on_off=true;
    	this.init=false;
    	this.invalidate();
    }
    
    public void disablePreset()
    {
    	this.on_off=false;
    	this.init=true;
    	this.invalidate();
    }
    
   private  void sw_ON (int iterator)
   { 
	   this.togglePosStopX=togglePosStartX+iterator;
	   this.planeStop+=iterator/8.7f;
	   this.planeStart-=iterator/21f;
	   
	   this.planeStopY+=iterator/22;
	   this.planeStartY-=iterator/22;
	   
	   invalidate();
   }
   private  void sw_OFF (int iterator)
   {
	   this.togglePosStartX=togglePosStopX-iterator;
	   invalidate();
   }
   private void activation()
   {
	   for(int i=0; i<=38; i++)
		{	
		final int iterator=i;
		new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
       	   sw_ON(iterator);
          }
        }, (i)*5);
		}
	   	this.on_off=true;
	   	this.planeActive.setColor(Color.RED);;
   }
   private void deactivation()
   {
	    this.planeStop=getWidth()/1.29f;
		for(int i=0; i<=38; i++)
		{	
		final int iterator=i;
		new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
        	   sw_OFF(iterator);
           }
         }, (i)*5);
		}
		this.on_off=false; 
		
   }
   public void setIndicators(int [] indicators)
   {
	   this.indicators=indicators;
	   invalidate();
   }
   public boolean getPresetStatus()
   {
	   return this.on_off;
   }
   
   public interface ActivePresetListener{
	   	public void onActivate();
	   	}
   public void setActivatePresetListener(ActivePresetListener eventListener) {
	   activatePresetListener=eventListener;
	   }

 }