package com.skyled.skytouch5;

import net.simonvt.numberpicker.NumberPicker;
import net.simonvt.numberpicker.NumberPicker.OnValueChangeListener;
import android.content.ContentValues;
import android.graphics.Color;

public class NumberPickerController {

protected MainActivity context;
	
	public NumberPickerController(MainActivity _context){
        context = _context;
    }	
	
	public void activateNumberPickerController() {
	
		
		context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            		
            	context.np1.setMaxValue(255);
            	context.np1.setMinValue(0);
            	context.np2.setMaxValue(255);
            	context.np2.setMinValue(0);
            	context.np3.setMaxValue(255);
            	context.np3.setMinValue(0);
                
            	context.np1.setOnValueChangedListener(new OnValueChangeListener() {
        			
        			@Override
        			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        			
        				int colorInt= Color.rgb(context.np1.getValue(), context.np2.getValue(), context.np3.getValue());

        				context.saturation.setGradient(colorInt);
        				context.saturation.invalidate();
        				context.colorPick.setColor(colorInt);
        				context.colorPick.invalidate();
//        				String where = "_id=" + context.activePreset ;
//        				ContentValues cv = new ContentValues();
//        			    cv.put(context.activeChanalColor, colorInt+"");
//        			    context.sqdb.update(DataB.T_PRESETS, cv, where, null);
        			          
        		
        	}
        		});
            	context.np2.setOnValueChangedListener(new OnValueChangeListener() {
        					
        					@Override
        					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        						int colorInt= Color.rgb(context.np1.getValue(), context.np2.getValue(), context.np3.getValue());
        						context.saturation.setGradient(colorInt);
        						context.saturation.invalidate();
//        						 String where = "_id=" + context.activePreset ;
//        						 	ContentValues cv = new ContentValues();
//        					        cv.put(context.activeChanalColor, colorInt+"");
//        					        context.sqdb.update(DataB.T_PRESETS, cv, where, null);
        			
        					        
        					}
        				});
            	context.np3.setOnValueChangedListener(new OnValueChangeListener() {
        			
        			@Override
        			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        				int colorInt= Color.rgb(context.np1.getValue(), context.np2.getValue(), context.np3.getValue());
        				context.saturation.setGradient(colorInt);
        				context.saturation.invalidate();
//        				 String where = "_id=" + context.activePreset ;
//        				 ContentValues cv = new ContentValues();
//        			     cv.put(context.activeChanalColor, colorInt+"");
//        			     context.sqdb.update(DataB.T_PRESETS, cv, where, null);
        			}
        		});	

            }
       });
		
	}

}
