package com.skyled.skytouch5.thirdpart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
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

public class ChanalsRedactor extends View {
	Context context;
	ActiveChanalEventListener mListener;
	SwitchRedactorEventListener swListener;
	CloseRedactorEventListener closeListener;
	
	Paint plane;
	Paint clear;
	Paint devider;
	Paint chanal_color_circle;
	Paint textTimePaint;
	Paint textNamePaint;
	Typeface eur;
	String name,time, value, ch_name;
	Paint circle;
	Paint clock;
	Paint textValuePaint;
	Paint gear_elements;
	
	int chanal_number;
	int ChanalColor;  
	int ChanalBack;
	int activateAfter=1000;
	
	int NameTextColor;
	private boolean on_off=false;
	float togglePosStartX, togglePosStartY, togglePosStopX,togglePosStopY, planeStart;
	boolean activate=false;
	boolean init=true;
	boolean mainmode=true;
	int indicators[];
	String values[];
	int redactorHeight;

	int activeChanalinTemplate;
	
	boolean[] active_chanal;
	
    public ChanalsRedactor(Context context) {
        super(context);
        this.context=context;
        init();
    }   
    public ChanalsRedactor(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    
    
    public void init() {

    	ChanalColor=Color.BLUE;
    	ChanalBack=Color.parseColor("#40ffffff"); //cc
    	NameTextColor=Color.BLACK;
    	name="Пресет 1";
    	ch_name="";
    	time="00:00 - 00:00";
    	value="10";
    	chanal_number=15;
    	active_chanal=new boolean[chanal_number];
    	values=new String[chanal_number];
    	indicators=new int[chanal_number];
    	for(int i=0;i<chanal_number;i++)
    	{
    		active_chanal[i] =false;
    		values[i]="0";
    		indicators[i]=Color.CYAN;
    	}
    	
    	
    	Typeface eur= Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
    	textNamePaint= new Paint(Paint.ANTI_ALIAS_FLAG);
    	textNamePaint.setColor(NameTextColor);
    	textNamePaint.setTextSize(22);
    	textNamePaint.setTypeface(eur);
    	
    	textTimePaint= new Paint(Paint.ANTI_ALIAS_FLAG);
    	textTimePaint.setColor(Color.BLACK);
    	textTimePaint.setTextSize(22);
    	textTimePaint.setTypeface(eur);
    	
    	textValuePaint= new Paint(Paint.ANTI_ALIAS_FLAG);
    	textValuePaint.setColor(Color.BLACK);
    	textValuePaint.setTextSize(22);
    	textValuePaint.setTypeface(eur);
    	

    	devider= new Paint(Paint.ANTI_ALIAS_FLAG);
    	devider.setColor(Color.GRAY);
    	devider.setStrokeWidth(2);
    	devider.setStyle(Paint.Style.STROKE); 
    	
    	plane= new Paint(Paint.ANTI_ALIAS_FLAG);
    	plane.setColor(ChanalBack);
    	plane.setStyle(Paint.Style.FILL_AND_STROKE); 
    	
    	circle= new Paint(Paint.ANTI_ALIAS_FLAG);
    	circle.setColor(Color.GRAY);
    	circle.setStyle(Paint.Style.STROKE); 
    	circle.setStrokeWidth(4);
    	
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
    	
    	gear_elements= new Paint(Paint.ANTI_ALIAS_FLAG);
    	gear_elements.setColor(Color.BLACK);
    	gear_elements.setStyle(Paint.Style.STROKE);   
    	gear_elements.setStrokeWidth(8);
    	gear_elements.setPathEffect(new DashPathEffect(new float[] { 8, 11}, 0));
    } 
    
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth();
        int radius=(width/4)/5;
        this.redactorHeight=getWidth();
        textNamePaint.setTextSize(radius/1.5f);
        textTimePaint.setTextSize(radius);
        textValuePaint.setTextSize(radius/1.5f);
        
       if(on_off)
        	plane.setColor(Color.parseColor("#CCffffff"));
        else
        	plane.setColor(Color.parseColor("#40ffffff"));
        	
        canvas.drawRoundRect(new RectF(0, 0, 10, width/4.8f), 5, 5, plane);
        canvas.drawRect(new RectF(5, 0, 10, width/4.8f), clear);
        
        canvas.drawRect(new RectF((width/3)*2, 0, width, width/4.8f), plane);
        canvas.drawRect(new RectF(5, 0, (width/3)*2-width/190, width/4.8f), plane); 
        
        canvas.drawLine(width/10, width/15, width/10+width/2, width/15, devider);
        canvas.drawLine(width/10, width/7, width/10+width/2, width/7, devider);
        
        canvas.drawCircle(width/18, width/9.5f, radius/1.5f, clock);
        clock.setStrokeWidth(2);
        canvas.drawLine(width/18, width/9.5f, width/18+radius/1.5f-radius/4, width/9.5f-radius/7, clock);
        clock.setStrokeWidth(1);
        canvas.drawLine(width/18, width/9.5f, width/18, width/9.5f-radius/1.9f, clock); 
        canvas.drawText(name, width/8, width/17.6f, textNamePaint);
        canvas.drawText(time, width/8, width/7.8f, textTimePaint);
        
//        for(int i=0; i<5; i++){
//        	canvas.drawCircle(width/6.5f+(radius*2)*i, width/5.6f, radius/2, clear);
//        	chanal_color_circle.setColor(indicators[i]);
//        	canvas.drawCircle(width/6.5f+(radius*2)*i, width/5.6f, radius/2.7f, chanal_color_circle);
//        }
        	clock.setStrokeWidth(6);
        	canvas.drawCircle((width/3)*2+((width-(width/3)*2)/2), (width/4)/2.5f, radius/1.8f, clock);
        	canvas.drawCircle((width/3)*2+((width-(width/3)*2)/2), (width/4)/2.5f, radius/1.3f, gear_elements);
        	clock.setStrokeWidth(3);   
        	
        	float startY=width/4.8f;
        	float stopY=width/2.6f;
        	float startX=(width/3)*2;
        	float stopX=width;
        	float chanalHeight=stopY-startY;
        	textNamePaint.setTextSize(chanalHeight/4);
        	
        	if(activate){
        	for(int i=0;i<chanal_number;i++)
        	{
        		if(!active_chanal[i])
        			plane.setColor(Color.parseColor("#40ffffff"));else plane.setColor(Color.parseColor("#CCffffff"));
        		
        	  canvas.drawRect(new RectF(startX, startY+chanalHeight*i+3, stopX, stopY+chanalHeight*i), plane);
        	  // left part plane 
        	  canvas.drawRoundRect(new RectF(width/10 ,startY+chanalHeight*i+3, width/1.5f, stopY+chanalHeight*i), 5, 5, plane);
        	  canvas.drawRect(new RectF(width/1.51f, startY+chanalHeight*i+3, width/1.5f, stopY+chanalHeight*i), clear);
        	  //right part plane
        	  ch_name="Канал "+(i+1);
        	  canvas.drawText(ch_name, width/10*1.5f, startY+chanalHeight*i+3+(chanalHeight/1.7f), textNamePaint);
        	  canvas.drawCircle( width/10*5.5f, startY+chanalHeight*i+3+(chanalHeight/2f), radius/1.2f, clear);
        	  chanal_color_circle.setColor(indicators[i]);
        	  canvas.drawCircle( width/10*5.5f, startY+chanalHeight*i+3+(chanalHeight/2f), radius/1.4f, chanal_color_circle);
        	  canvas.drawCircle((width/3)*2+((width-(width/3)*2)/2), startY+chanalHeight*i+3+(chanalHeight/2f), radius/1.3f, circle);
        	  canvas.drawText(values[i], (width/3)*2+((width-(width/3)*2)/2)-(values[i].length()*radius/3.2f), startY+chanalHeight*i+3+(chanalHeight/1.8f), textValuePaint);
        	}}new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                	activateChanals();
                }
              }, activateAfter);
    } 
    
    
	   private void activateChanals()
	   {
		   this.activate=true;
		   invalidate();
	   }
	   public void deactivateChanals()
	   {
		   this.activate=false;
		   invalidate();
	   }

    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:	
			if(event.getX()>=getWidth()/1.3f && event.getX()<getWidth()/1.097f 
					&& event.getY()>=getWidth()/16 && event.getY()<getWidth()/7)
			{
				if(swListener!=null) swListener.SwitchRedactor();
			}
			
			if(event.getX()>=5 && event.getX()<(getWidth()/3)*2-getWidth()/190
					&& event.getY()>=getWidth()/16 && event.getY()<getWidth()/7)
			{
				if(closeListener!=null) closeListener.CloseRedactor();
				for(int i=0;i<chanal_number;i++)
		    	{
		    		active_chanal[i] =false;
		    		activeChanalinTemplate=0;
		    	}
			}
			for(int i=0;i<chanal_number;i++)
        	{
			if(event.getX()>=getWidth()/10 && event.getX()<getWidth() && 
					event.getY()>=getWidth()/4.8f+(getWidth()/2.6f-getWidth()/4.8f)*i+3 && event.getY()<getWidth()/2.6f+(getWidth()/2.6f-getWidth()/4.8f)*i)
				{
					for(int j=0;j<chanal_number;j++)
					{
						active_chanal[j]=false;
					}
					active_chanal[i]=true;
					activeChanalinTemplate=i+1;
				if(mListener!=null) mListener.onEvent();
					invalidate();
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
    public void setPresetTime(int value)
    {
    	this.time=textTimePaint+"";
    	invalidate();
    }
   public void setIndicators(int [] indicators)
   {
	   this.indicators=indicators;
	   invalidate();
   }

   public void setChanalIndicatorColor(int  color)
   {
	   this.indicators[activeChanalinTemplate-1]=color;
	   invalidate();
   }
   
   public void setOn_Off_Preset_indicator(boolean on_off)
   {
	   this.on_off=on_off;
	   invalidate();
   }
   public void setChanalIndicatorValue(int  value)
   {
	   this.values[activeChanalinTemplate-1]=value+"";
	   invalidate();
   }
   
   public int getActiveChanal()
	   {
		   return activeChanalinTemplate;
	   }
   
   public int getRedactorHeight(int width)
   {
	   int firstPart=Math.round(width/4.8f); 
	   float startY=width/4.8f;
   	   float stopY=width/2.6f;
	   int secondPart=Math.round(stopY-startY)*chanal_number;
	   return firstPart+secondPart;
   }
   
   public interface ActiveChanalEventListener{
   	public void onEvent();
   	}
   
   public interface SwitchRedactorEventListener
   {
   	public void SwitchRedactor();
   }
   
   public interface CloseRedactorEventListener
   {
   	public void CloseRedactor();
   }
   
   public void setActiveChanalEventListener(ActiveChanalEventListener eventListener) {
   	mListener=eventListener;
   }
   public void setSwitchRedactorEventListener(SwitchRedactorEventListener switchRedactor) {
   	swListener=switchRedactor;
   }
   public void setCloseRedactorEventListener(CloseRedactorEventListener closeRedactor) {
	 closeListener=closeRedactor;
   }
   

 }