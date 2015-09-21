package com.skyled.skytouch5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SQLman {
	protected MainActivity context;
	
	public SQLman(MainActivity _context){
       context = _context;
    }

	public void init()
	{
		context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	
            	for (int i=1;i<16;i++){
        		    ContentValues cv = new ContentValues(); 
        			cv.put(DataB.P_NAME, "Пресет "+i);
        			cv.put(DataB.P_TIMING, "00:00 - 00:00");
        			cv.put(DataB.P_STATUS, "off");
        			cv.put(DataB.P_BR, "23");
        			cv.put(DataB.P_CH1, -16758529);
        			cv.put(DataB.P_CH2, -10879232);
        			cv.put(DataB.P_CH3, -20340); 
        			cv.put(DataB.P_CH4, -10879232);
        			cv.put(DataB.P_CH5, -167529);
        			cv.put(DataB.P_CHBR1, 0);
        			cv.put(DataB.P_CHBR2, 45);
        			cv.put(DataB.P_CHBR3, 89); 
        			cv.put(DataB.P_CHBR4, 25);
        			cv.put(DataB.P_CHBR5, 3);
        			cv.put(DataB.P_BG_ACTYVITY, "off");
        			cv.put(DataB.P_BG_ACTIVEDAYS, "0,0,0,0,0,0,0");
        			cv.put(DataB.P_BG_ACTIVESENSORS, "Не задано");
        			cv.put(DataB.P_BG_SENSORTIME, "00:00"); 
        			cv.put(DataB.P_BG_LIGHTESTATUS, "Не задано");
        			context.sqdb.insert(DataB.T_PRESETS, null, cv);  
        	}	
            }
       });
	}
	
	public void chanalInit()
	{
		for (int i=1;i<16;i++){
	    ContentValues cv = new ContentValues();
		cv.put(DataB.CH_NAME, "Канал "+i);
		cv.put(DataB.CH_STATUS, "0");
		cv.put(DataB.CH_PARENT, "1");
		cv.put(DataB.CH_MODE, "0");
		cv.put(DataB.CH_BRIGHT, "100");
		cv.put(DataB.CH_SATUR, "100");
		cv.put(DataB.CH_COLOR, -10879232);
		context.sqdb.insert(DataB.T_CHANALS, null, cv);  
		}
	}
	
	public void getPreset()
	{
		context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            	context.capresets.listArray.clear();
        	  	String query = "SELECT " + DataB.P_ID+", "+ DataB.P_NAME  +", "+DataB.P_STATUS +", "+DataB.P_BR +", "+DataB.P_TIMING +
        	  			", "+DataB.P_CH1 +", "+DataB.P_CH2 +", "+DataB.P_CH3 +", "+DataB.P_CH4 +", "+DataB.P_CH5 +
        	  			", "+DataB.P_CHBR1 +
        	  			", "+DataB.P_CHBR2 +
        	  			", "+DataB.P_CHBR3 +
        	  			", "+DataB.P_CHBR4 +
        	  			", "+DataB.P_CHBR5 +
        	  			" FROM " + DataB.T_PRESETS;
            	Cursor cursor = context.sqdb.rawQuery(query, null);
        		while (cursor.moveToNext()) {
        			String pid = cursor.getString(cursor.getColumnIndex(DataB.P_ID));
        			String status = cursor.getString(cursor.getColumnIndex(DataB.P_STATUS));
        			String name = cursor.getString(cursor.getColumnIndex(DataB.P_NAME));
        			String timing = cursor.getString(cursor.getColumnIndex(DataB.P_TIMING));
        			String brightness = cursor.getString(cursor.getColumnIndex(DataB.P_BR));
        			String chanal1 = cursor.getString(cursor.getColumnIndex(DataB.P_CH1));
        			String chanal2 = cursor.getString(cursor.getColumnIndex(DataB.P_CH2));
        			String chanal3 = cursor.getString(cursor.getColumnIndex(DataB.P_CH3));
        			String chanal4 = cursor.getString(cursor.getColumnIndex(DataB.P_CH4));
        			String chanal5 = cursor.getString(cursor.getColumnIndex(DataB.P_CH5));
        			String chanalbr1 = cursor.getString(cursor.getColumnIndex(DataB.P_CHBR1));
        			String chanalbr2 = cursor.getString(cursor.getColumnIndex(DataB.P_CHBR2));
        			String chanalbr3 = cursor.getString(cursor.getColumnIndex(DataB.P_CHBR3));
        			String chanalbr4 = cursor.getString(cursor.getColumnIndex(DataB.P_CHBR4));
        			String chanalbr5 = cursor.getString(cursor.getColumnIndex(DataB.P_CHBR5));
        			context.capresets.listArray.add(new DMPresets( pid, name, timing, brightness, status, chanal1, chanal2, chanal3, chanal4, chanal5, chanalbr1, chanalbr2,
        				chanalbr3, chanalbr4, chanalbr5));
        		 }
        		cursor.close();
        		context.presetList.setAdapter(context.capresets);
        		if(context.presetList.getFooterViewsCount()==0)
        		{
        		 View footerView =  ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.preset_footer, null, false);
        		 TextView tx=(TextView)footerView.findViewById(R.id.textView1);
        		 tx.setTypeface(context.eur);
        		 footerView.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
	        		    ContentValues cv = new ContentValues(); 
	        			cv.put(DataB.P_NAME, "Пресет "+context.presetList.getCount());
	        			cv.put(DataB.P_TIMING, "00:00 - 00:00");
	        			cv.put(DataB.P_STATUS, "off");
	        			cv.put(DataB.P_BR, "23");
	        			cv.put(DataB.P_CH1, -16758529);
	        			cv.put(DataB.P_CH2, -10879232);
	        			cv.put(DataB.P_CH3, -20340); 
	        			cv.put(DataB.P_CH4, -10879232);
	        			cv.put(DataB.P_CH5, -167529);
	        			cv.put(DataB.P_CHBR1, 0);
	        			cv.put(DataB.P_CHBR2, 45);
	        			cv.put(DataB.P_CHBR3, 89); 
	        			cv.put(DataB.P_CHBR4, 25);
	        			cv.put(DataB.P_CHBR5, 3);
	        			cv.put(DataB.P_BG_ACTYVITY, "off");
	        			cv.put(DataB.P_BG_ACTIVEDAYS, "0,0,0,0,0,0,0");
	        			cv.put(DataB.P_BG_ACTIVESENSORS, "Не задано");
	        			cv.put(DataB.P_BG_SENSORTIME, "00:00"); 
	        			cv.put(DataB.P_BG_LIGHTESTATUS, "Не задано");
	        			context.sqdb.insert(DataB.T_PRESETS, null, cv);  
	        			getPreset();
					}
				});
        		context.presetList.addFooterView(footerView);
        		}
            }});
	}
	
	public void getChanals()
	{
		
			context.cachanals.listArray.clear();
		  	String query = "SELECT " + DataB.CH_ID+
		  			", "+ DataB.CH_NAME  +
		  			", "+DataB.CH_STATUS +
		  			", "+DataB.CH_MODE +
		  			", "+DataB.CH_PARENT +
    	  			", "+DataB.CH_BRIGHT +
    	  			", "+DataB.CH_SATUR +
    	  			", "+DataB.CH_COLOR +
    	  			" FROM " + DataB.T_CHANALS;
        	Cursor cursor = context.sqdb.rawQuery(query, null);
           if(cursor.getCount()==0)
           {
        	  chanalInit();
        	   getChanals();
           }
       	while (cursor.moveToNext()) {
			int ch_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataB.CH_ID)));
			String name = cursor.getString(cursor.getColumnIndex(DataB.CH_NAME));
			int status = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataB.CH_STATUS)));
			int mode = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataB.CH_MODE)));
			int parent = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataB.CH_PARENT)));
			int brightness = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataB.CH_BRIGHT)));
			int color = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataB.CH_COLOR)));
			int saturation = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataB.CH_SATUR)));
			context.cachanals.listArray.add(new DMChanal(ch_id,name,status, mode, parent, brightness, color, saturation));
		 }
       	cursor.close();
       	context.chanalList.setAdapter(context.cachanals);

	}

}
