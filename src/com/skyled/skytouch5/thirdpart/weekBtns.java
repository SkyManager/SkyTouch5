package com.skyled.skytouch5.thirdpart;

import com.skyled.skytouch5.thirdpart.ColorPicker.OnColorChangedListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class weekBtns extends View {
	Context context;
	
	
	Paint mUnavailable;
	Paint mAilable;
	Paint ActiveCircle;
	Paint mUnavailableHoliday;
	Paint mAilableHoliday;
	private float gridStartX;
	private float gridStartY;
	private float TextSize;
	String[] days={"èÌ", "ÇÚ", "ë", "óÚ", "èÚ", "ë·", "ÇÒ"};
	boolean[] daysStatus={false, false, false, false, false, false, false};
    public weekBtns(Context context) {
        super(context);
        this.context=context;
        init();
    }   
    public weekBtns(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    
    public void init() {
    	Typeface eur= Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
    	gridStartX =0;
    	gridStartY=22;
    	TextSize=0;
    	
    	mUnavailable= new Paint(Paint.ANTI_ALIAS_FLAG);
    	mUnavailable.setColor(Color.GRAY);
    	mUnavailable.setTypeface(eur);
    	
    	mAilable= new Paint(Paint.ANTI_ALIAS_FLAG);
    	mAilable.setColor(Color.WHITE);
    	mAilable.setTypeface(eur);
    	
    	mAilableHoliday= new Paint(Paint.ANTI_ALIAS_FLAG);
    	mAilableHoliday.setColor(Color.RED);
    	mAilableHoliday.setTypeface(eur);
    	
    	mUnavailableHoliday= new Paint(Paint.ANTI_ALIAS_FLAG);
    	mUnavailableHoliday.setColor(Color.RED);
    	mUnavailableHoliday.setAlpha(51);
    	mUnavailableHoliday.setTypeface(eur);
    	
    	ActiveCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
    	ActiveCircle.setColor(Color.RED);
    	ActiveCircle.setStyle(Paint.Style.FILL_AND_STROKE); 
    } 
    
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TextSize=getWidth()/13.5f;
    	mAilable.setTextSize(TextSize);
    	mUnavailable.setTextSize(TextSize);
    	mUnavailableHoliday.setTextSize(TextSize);
    	mAilableHoliday.setTextSize(TextSize);
        // DRAW DAYS TEXT
        for (int i=0; i<days.length; i++)
        {
        	if(!daysStatus[i]){
        		if(i>4){
        			canvas.drawText(days[i], gridStartX+(days[i].length()*TextSize)*i, gridStartY, mUnavailableHoliday);
                	canvas.drawCircle(TextSize/1.2f+gridStartX+(days[i].length()*TextSize)*i, gridStartY+TextSize, TextSize/2, mUnavailable);
        		}else
        		{
        			canvas.drawText(days[i], gridStartX+(days[i].length()*TextSize)*i, gridStartY, mUnavailable);
                	canvas.drawCircle(TextSize/1.2f+gridStartX+(days[i].length()*TextSize)*i, gridStartY+TextSize, TextSize/2, mUnavailable);
        		}		
        	}else
        	{
        		if(i>4)
        		{
        			canvas.drawText(days[i], gridStartX+(days[i].length()*TextSize)*i, gridStartY, mAilableHoliday);
                	canvas.drawCircle(TextSize/1.2f+gridStartX+(days[i].length()*TextSize)*i, gridStartY+TextSize, TextSize/2, mAilable);
                	canvas.drawCircle(TextSize/1.2f+gridStartX+(days[i].length()*TextSize)*i, gridStartY+TextSize, (TextSize/2.5f), ActiveCircle);
        		}else
        		{
        			canvas.drawText(days[i], gridStartX+(days[i].length()*TextSize)*i, gridStartY, mAilable);
                	canvas.drawCircle(TextSize/1.2f+gridStartX+(days[i].length()*TextSize)*i, gridStartY+TextSize, TextSize/2, mAilable);
                	canvas.drawCircle(TextSize/1.2f+gridStartX+(days[i].length()*TextSize)*i, gridStartY+TextSize, (TextSize/2.5f), ActiveCircle);	
        		}
        		
        	}
        }
    } 
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:	
			changeDayStatus(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_UP:
			break; 
		}
		return true;
	}
	
	private void changeDayStatus(float dayX, float dayY)
	{
		if (dayY>=gridStartY && dayY<gridStartY+TextSize*2)
		{
			if (dayX>=gridStartX && dayX<gridStartX+(days[0].length()*TextSize))
			{
				if(daysStatus[0]){daysStatus[0]=false;}else{daysStatus[0]=true;}
			}
			if (dayX>=gridStartX+(days[0].length()*TextSize) && dayX<gridStartX+(days[0].length()*TextSize)*2)
			{
				if(daysStatus[1]){daysStatus[1]=false;}else{daysStatus[1]=true;}
			}
			if (dayX>=gridStartX+(days[0].length()*TextSize)*2 && dayX<gridStartX+(days[0].length()*TextSize)*3)
			{
				if(daysStatus[2]){daysStatus[2]=false;}else{daysStatus[2]=true;}
			}
			if (dayX>=gridStartX+(days[0].length()*TextSize)*3 && dayX<gridStartX+(days[0].length()*TextSize)*4)
			{
				if(daysStatus[3]){daysStatus[3]=false;}else{daysStatus[3]=true;}
			}
			if (dayX>=gridStartX+(days[0].length()*TextSize)*4 && dayX<gridStartX+(days[0].length()*TextSize)*5)
			{
				if(daysStatus[4]){daysStatus[4]=false;}else{daysStatus[4]=true;}
			}
			if (dayX>=gridStartX+(days[0].length()*TextSize)*5 && dayX<gridStartX+(days[0].length()*TextSize)*6)
			{
				if(daysStatus[5]){daysStatus[5]=false;}else{daysStatus[5]=true;}
			}
			if (dayX>=gridStartX+(days[0].length()*TextSize)*6 && dayX<gridStartX+(days[0].length()*TextSize)*7)
			{
				if(daysStatus[6]){daysStatus[6]=false;}else{daysStatus[6]=true;}
			}
			invalidate();
		}
	}
	
	public void setCurentDay(int today)
	{
		this.daysStatus[today-1]=true;
		invalidate();
	}
	public void setActivetDays(boolean[] days)
	{
		this.daysStatus=days;
		invalidate();
	}
	public boolean[] getActiveDays()
	{
		return daysStatus;
	}
	

 }
    

