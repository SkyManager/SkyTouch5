package com.skyled.skytouch5.thirdpart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class Shedule extends View {
	Paint grid;
	Paint gridAdditional;
	Paint textPaint;
	Paint textDayPaint;
	Paint	textDayPaintHoliday;
	Paint timeLines;
	Paint shadow;
	Path gridPath;
	Path shadowPath;
	
	private float cellWidth;
	private float cellHeight;
	private float cellSpace;
	private float gridStartX;
	private float gridStartY;
	private int hours;
	private float TimeLength;
    String hoursStr;
    Typeface eur;
    Context context;
    String time="‚…";
    String[] days={"ν", "‚ς", "‘π", "—ς", "ς", "‘α", "‚ρ"};
    String[] presetList={"περες 1", "περες 2", "περες 3", "περες 4", "περες 5"};
    int timeTextSize=8;
  

    float [][]  shedule = {new float[10], new float[10], new float[10], new float[10], new float[10],new float[10],new float[10]};
    int[] presetColors = {Color.parseColor("#ff1a1a"),Color.parseColor("#1ace1a"),Color.parseColor("#ff1aff"),Color.parseColor("#1a1aff"),Color.parseColor("#e98523")};
	    public Shedule(Context context) {
	        super(context);
	        this.context=context;
	        isInEditMode();
	        init();
	    }   
	    public Shedule(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        this.context=context;
	        isInEditMode();
	        init();
	    }
	    public void init() {
	    	cellWidth=16;
	    	cellHeight=34;
	    	cellSpace=2;
	    	gridStartX =40;
	    	gridStartY=50; 
	    	hours=24;
	    	TimeLength=((cellWidth+cellSpace)*hours)-cellSpace;
	
	    	grid=new Paint(Paint.ANTI_ALIAS_FLAG);
	    	grid.setStyle(Paint.Style.STROKE);
	    	grid.setColor(Color.WHITE);
	    	grid.setAlpha(204);
	    	grid.setStrokeWidth(cellHeight);
	    	grid.setPathEffect(new DashPathEffect(new float[] { cellWidth, cellSpace}, 0));
	    	Typeface eur= Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
	    	
	    	
	    	gridAdditional=new Paint(Paint.ANTI_ALIAS_FLAG);
	    	gridAdditional.setStyle(Paint.Style.STROKE);
	    	gridAdditional.setColor(Color.WHITE);
	    	gridAdditional.setAlpha(51);
	    	gridAdditional.setStrokeWidth(cellHeight/2);
	    	gridAdditional.setPathEffect(new DashPathEffect(new float[] { cellWidth, cellSpace}, 0));

	    	textPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
	    	textPaint.setColor(Color.WHITE);
	    	textPaint.setTextSize(timeTextSize);
	    	textPaint.setTypeface(eur);
	    	
	    	textDayPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
	    	textDayPaint.setColor(Color.WHITE);
	    	textDayPaint.setTextSize(cellHeight/1.4f);
	    	textDayPaint.setTypeface(eur);  
	    	
	    	textDayPaintHoliday=new Paint(Paint.ANTI_ALIAS_FLAG);
	    	textDayPaintHoliday.setColor(Color.RED);
	    	textDayPaintHoliday.setTextSize(cellHeight/1.4f);
	    	textDayPaintHoliday.setTypeface(eur);
	    	
	    	textPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
	    	textPaint.setColor(Color.WHITE);
	    	textPaint.setTextSize(timeTextSize);
	    	textPaint.setTypeface(eur);
	    	
	    	timeLines=new Paint(Paint.ANTI_ALIAS_FLAG);
	    	timeLines.setColor(Color.BLUE);
	    	timeLines.setStrokeWidth(cellSpace/2); 
	    	
	    	shadow=new Paint(Paint.ANTI_ALIAS_FLAG);
	    	shadow.setStyle(Paint.Style.STROKE);
	    	shadow.setColor(Color.WHITE);
	    	shadow.setAlpha(51);
	    	shadow.setStrokeWidth(cellHeight);
	    	shadow.setStrokeWidth(cellHeight/1.5f);

	    	gridPath=new Path();
	    	shadowPath=new Path();
	    }       
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);  
            
	        for (int i=0; i<days.length; i++)
	        {
	        	gridPath.moveTo(gridStartX, gridStartY+(cellHeight+cellSpace)*i);
		        gridPath.lineTo(gridStartX+((cellWidth+cellSpace)*hours), gridStartY+(cellHeight+cellSpace)*i);
	        }
	        canvas.drawPath(gridPath, grid);
	        
	        gridPath.reset();  
	        gridPath.moveTo(gridStartX, gridStartY+cellSpace-cellHeight);
	        gridPath.lineTo(gridStartX+((cellWidth+cellSpace)*hours), gridStartY-cellHeight+cellSpace);
	        canvas.drawPath(gridPath, gridAdditional);
	        gridPath.reset(); 
	        
	        shadowPath.reset(); 
	        textPaint.setTextSize(timeTextSize*2);
	        for(int i=0; i<5; i++)
	        {   //indicator shadow
	        	shadowPath.moveTo(gridStartX, gridStartY+(cellHeight/1.5f+cellSpace)*(10+i));
		        shadowPath.lineTo(gridStartX+((cellWidth+cellSpace)*hours/6)-cellSpace, gridStartY+(cellSpace+cellHeight/1.5f)*(10+i));
		        //indicator line for presets 
		        timeLines.setColor(presetColors[i]);
		        textPaint.setColor(presetColors[i]);
		        canvas.drawLine(gridStartX+10, gridStartY+(cellHeight/1.5f+cellSpace)*(10+i),//start line
		        		gridStartX+((cellWidth+cellSpace)*hours/6)-cellSpace-10, gridStartY+(cellSpace+cellHeight/1.5f)*(10+i), timeLines);
		        // text shadow
		        shadowPath.moveTo(gridStartX+((cellWidth+cellSpace)*hours/6)+1, gridStartY+(cellHeight/1.5f+cellSpace)*(10+i));
		        shadowPath.lineTo(gridStartX+((cellWidth+cellSpace)*hours)-cellSpace, gridStartY+(cellSpace+cellHeight/1.5f)*(10+i));
		        // preset name text
		        canvas.drawText(presetList[i], gridStartX+((cellWidth+cellSpace)*hours/6)+timeTextSize, gridStartY+(cellHeight/1.5f+cellSpace)*(10+i)+timeTextSize/2, textPaint);
	        }
	        canvas.drawPath(shadowPath, shadow);
	        textPaint.setColor(Color.WHITE);
	        textPaint.setTextSize(timeTextSize);
	        
	        canvas.drawText(time, gridStartX-(time.length()*timeTextSize), gridStartY+3-cellHeight+cellSpace, textPaint);
	        for (int i=0; i<hours/2;i++){
	        	 canvas.drawText(String.format("%02d",i*2), gridStartX+(cellWidth+cellSpace)*i*2, gridStartY+3-cellHeight+cellSpace, textPaint);	
	        }
	        	// Draw  Days String
	        	for(int i=0; i<days.length; i++)
	        	{
	        	if(i<=4){
	        	 canvas.drawText(days[i], gridStartX-(time.length()*timeTextSize), gridStartY+cellHeight/5f+((cellHeight+cellSpace)*i), textDayPaint);
	        	}
	        	if(i>4){
		        	 canvas.drawText(days[i], gridStartX-(time.length()*timeTextSize), gridStartY+cellHeight/5f+((cellHeight+cellSpace)*i), textDayPaintHoliday);
		        }
	        }
	        		float startAt=0;
	        		float  stopAt=0;
	        	
		        	 //   Draw Time LINES
		        	for (int i=0; i<days.length;i++){
		        	startAt=(int)shedule[i][0]*TimeLength/hours+(shedule[i][0]%1/0.6f*1)*TimeLength/hours;
	        		stopAt=(int)shedule[i][1]*TimeLength/hours+(shedule[i][1]%1/0.6f*1)*TimeLength/hours;
	        		timeLines.setColor(presetColors[0]);
		        	canvas.drawLine(gridStartX+startAt, gridStartY+(cellHeight+cellSpace)*i-((cellHeight/5)*2)+2, gridStartX+stopAt, gridStartY+(cellHeight+cellSpace)*i-((cellHeight/5)*2)+2, timeLines);
		        	// Second preset
		        	startAt=(int)shedule[i][2]*TimeLength/hours+(shedule[i][2]%1/0.6f*1)*TimeLength/hours;
	        		stopAt=(int)shedule[i][3]*TimeLength/hours+(shedule[i][3]%1/0.6f*1)*TimeLength/hours;
		        	timeLines.setColor(presetColors[1]);
		        	canvas.drawLine(gridStartX+startAt, gridStartY+(cellHeight+cellSpace)*i-((cellHeight/5))+1, gridStartX+stopAt, gridStartY+(cellHeight+cellSpace)*i-((cellHeight/5))+1, timeLines);
		        	// Third preset
		        	startAt=(int)shedule[i][4]*TimeLength/hours+(shedule[i][4]%1/0.6f*1)*TimeLength/hours;
	        		stopAt=(int)shedule[i][5]*TimeLength/hours+(shedule[i][5]%1/0.6f*1)*TimeLength/hours;
		        	timeLines.setColor(presetColors[2]);
		        	canvas.drawLine(gridStartX+startAt, gridStartY+(cellHeight+cellSpace)*i, gridStartX+stopAt, gridStartY+(cellHeight+cellSpace)*i, timeLines);
		        	// Fourth preset
		        	startAt=(int)shedule[i][6]*TimeLength/hours+(shedule[i][6]%1/0.6f*1)*TimeLength/hours;
	        		stopAt=(int)shedule[i][7]*TimeLength/hours+(shedule[i][7]%1/0.6f*1)*TimeLength/hours;
		        	timeLines.setColor(presetColors[3]);
		        	canvas.drawLine(gridStartX+startAt, gridStartY+(cellHeight+cellSpace)*i+((cellHeight/5))-1, gridStartX+stopAt, gridStartY+(cellHeight+cellSpace)*i+((cellHeight/5))-2, timeLines);
		        	// Fifth preset
		        	startAt=(int)shedule[i][8]*TimeLength/hours+(shedule[i][8]%1/0.6f*1)*TimeLength/hours;
	        		stopAt=(int)shedule[i][9]*TimeLength/hours+(shedule[i][9]%1/0.6f*1)*TimeLength/hours;
		        	timeLines.setColor(presetColors[4]);
		        	canvas.drawLine(gridStartX+startAt, gridStartY+(cellHeight+cellSpace)*i+((cellHeight/5)*2)-2, gridStartX+stopAt, gridStartY+(cellHeight+cellSpace)*i+((cellHeight/5)*2)-2, timeLines);
		        	} 

	    }
	    
				    public void setDrawShedules(float[] d1, float[]d2, float[]d3, float[]d4, float[]d5, float[]d6, float[]d7)
				    {
				    	this.shedule = new float[][]{d1,d2,d3,d4,d5,d6,d7};
				    	invalidate();
				    }
				    
				    public void setPresetList(String[] presetList)
				    {
				     	this.presetList = presetList;
				    	invalidate();
				    }
		
	}
