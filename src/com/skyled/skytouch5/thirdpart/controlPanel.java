package com.skyled.skytouch5.thirdpart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class controlPanel extends View {
	Context context;
	
	
	switchBrightListener brightListener;
	switchColorListener colorListener;
	switchSaturationListener saturationListener;
	
	Paint brightness;
	Paint brightness_rays;
	
	Paint color;
	Paint saturation;
	
	boolean [] modes= {true, false, false};
	boolean aditionalModules=false;

	private static final int[] COLORS = new int[] { 0xFFFF0000, 0xFFFF00FF,
		0xFF0000FF, 0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000 };
	
    public controlPanel(Context context) {
        super(context);
        this.context=context;
     
        init();
    }   
    public controlPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    
    public void init() {
    	//Typeface eur= Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");

    	brightness= new Paint(Paint.ANTI_ALIAS_FLAG);
    	brightness.setColor(Color.WHITE);
    	brightness.setStyle(Paint.Style.STROKE);
    	brightness.setStrokeWidth(3);
    	
    	brightness_rays= new Paint(Paint.ANTI_ALIAS_FLAG);
    	brightness_rays.setColor(Color.WHITE);
    	brightness_rays.setStyle(Paint.Style.STROKE);
    	brightness_rays.setStrokeWidth(13);
    	brightness_rays.setPathEffect(new DashPathEffect(new float[] { 3, 22}, 0));
    	
    	color= new Paint(Paint.ANTI_ALIAS_FLAG);
    	color.setColor(Color.WHITE);
    	color.setStyle(Paint.Style.FILL);
    	color.setStrokeWidth(3);

    	saturation= new Paint(Paint.ANTI_ALIAS_FLAG);
    	saturation.setStyle(Paint.Style.FILL);
    	saturation.setColor(Color.parseColor("#B3ffffff"));
    	saturation.setStrokeWidth(3);
    	
    } 
    
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float height=getHeight();
        canvas.drawCircle(getWidth()/5, height/2, getWidth()/27, brightness);
        canvas.drawCircle(getWidth()/5, height/2, getWidth()/15, brightness_rays);
        
        if(aditionalModules){
        Shader s1 = new SweepGradient(getWidth()/2, height/2, COLORS, null);
        color.setShader(s1);
        canvas.drawCircle(getWidth()/2, height/2, getWidth()/15, color);
        Shader s2 = new SweepGradient(getWidth()/1.2f, height/2, COLORS, null);
        color.setShader(s2);
        canvas.drawCircle(getWidth()/1.2f, height/2, getWidth()/15, color);
        canvas.drawCircle(getWidth()/1.2f, height/2, getWidth()/24, saturation);
        }
    } 
    
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:	 
			if(event.getX()>=getWidth()/5-getWidth()/15/2 && event.getX()<getWidth()/5+getWidth()/15/2)
			{
				if(brightListener!=null) brightListener.onBrightnes();
			}
			if(event.getX()>=getWidth()/2-getWidth()/15/2 && event.getX()<getWidth()/2+getWidth()/15/2)
			{
				if(aditionalModules)
				if(colorListener!=null) colorListener.onColor();
			}
			if(event.getX()>=getWidth()/1.2f-getWidth()/15/2 && event.getX()<getWidth()/1.2f+getWidth()/15/2)
			{
				if(aditionalModules)
				if(saturationListener!=null) saturationListener.onSaturation();
			}
		
			
			break;
		case MotionEvent.ACTION_UP:
			break;
		}

		return true;
	}
	
	public void on()
	{
	  this.aditionalModules=true;
	  invalidate();
	}
	public void off()
	{
	  this.aditionalModules=false;
	  invalidate();
	}
	public boolean isActive()
	{
		return this.aditionalModules;
	}
	
	
	   public interface switchBrightListener{
		    public void onBrightnes();
		}
	   public interface switchColorListener{
		    public void onColor();
		}
	   public interface switchSaturationListener{
		    public void onSaturation();
		}
		   
		 
		   public void setBrihtnesActivationListener(switchBrightListener switchBrightness) {
			   brightListener=switchBrightness;
		   }
		   public void setColorActivationListener(switchColorListener switchColor) {
			   colorListener=switchColor;
		   }
		   public void setSaturationActivationListener(switchSaturationListener switchSaturation) {
			   saturationListener=switchSaturation;
		   }
		   
		   
 }
