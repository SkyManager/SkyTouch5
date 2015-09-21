package com.skyled.skytouch5;

import android.content.ContentValues;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import com.skyled.skytouch5.thirdpart.ChanalView;
import com.skyled.skytouch5.thirdpart.SeekArc;
import com.skyled.skytouch5.thirdpart.SeekArc.OnSeekArcChangeListener;

public class SaturationController {

protected MainActivity context;
	
	public SaturationController(MainActivity _context){
        context = _context;
    }
	public void activateSaturationController() {
		
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() { 
            	context.saturation.setArcRotation(210); 
            	context.saturation.setSweepAngle(300); 
            	context.saturation.setTouchInSide(true);
            	context.saturation.setProgress(100);
            	context.saturation.setOnSeekArcChangeListener(new OnSeekArcChangeListener() {
        			
        			@Override
        			public void onStopTrackingTouch(SeekArc seekArc) {	
        			
        				context.colorPick.setColor(context.colorPick.getColor());
        			}		
        			@Override
        			public void onStartTrackingTouch(SeekArc seekArc) {
        			}
        			
        			@Override
        			public void onProgressChanged(SeekArc seekArc, int progress,
        					boolean fromUser){ 
        				float[] hsv = new float[3];
        				Color.colorToHSV(context.colorPick.getColor(), hsv);
        				hsv[1]=(float)progress*0.01f;
        				int newcolor=Color.HSVToColor(hsv);
        				 if(context.active_chanal!=0)
							{
								ChanalView	chan = ((ChanalView) getViewByPosition(context.active_chanal-1, context.chanalList).findViewById(R.id.chanalView_forList));
								chan.setChanalIndicator(newcolor);
								context.cachanals.listArray.get(context.active_chanal-1).setColor(newcolor);
								context.cachanals.listArray.get(context.active_chanal-1).setSaturation(progress);
								
								String where = "_id="+ (context.active_chanal-1) ;
		        				ContentValues cv = new ContentValues();
		    					cv.put(DataB.CH_COLOR, newcolor+"");
		    					cv.put(DataB.CH_SATUR, progress+"");
		    					context.sqdb.update(DataB.T_CHANALS, cv, where, null);
		    					context.setColor( newcolor);
							}
	        				 if(context.active_chanalInPreset!=0)
								{
									context.template.setChanalIndicatorColor(newcolor);
								}
        				
//        				if(chanalMode){
//        				String where = "_id=" + activePreset ;
//        			 	ContentValues cv = new ContentValues();
//        		        cv.put(activeChanalColor, colorPick.getColor()+"");
//        		        sqdb.update(DataB.T_PRESETS, cv, where, null);
//        				
//        			    int   	nr = (newcolor >> 16) & 0xFF;
//        				int		ng = (newcolor >> 8) & 0xFF;
//        				int 	nb = newcolor & 0xFF;
//        				
//        				 np1.setValue(nr);
//        				 np2.setValue(ng); 
//        				 np3.setValue(nb);
//        				 setColor(activeChanalColor, newcolor);
//        				 }else
//        				 {
//        				
//        				 }
//        				
//        				 
        			} 
        		});
           	 
            }
       });
		
	}
	public View getViewByPosition(int pos, ListView listView) {
		final int firstListItemPosition = listView.getFirstVisiblePosition();
		final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

		if (pos < firstListItemPosition || pos > lastListItemPosition ) {
		    return listView.getAdapter().getView(pos, null, listView);
		} else {
		    final int childIndex = pos - firstListItemPosition;
		    return listView.getChildAt(childIndex);
		}
		}

}
