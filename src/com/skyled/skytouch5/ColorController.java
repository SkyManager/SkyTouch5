package com.skyled.skytouch5;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

import android.content.ContentValues;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.skyled.skytouch5.thirdpart.ChanalView;
import com.skyled.skytouch5.thirdpart.ColorPicker.OnColorChangedListener;

public class ColorController {

protected MainActivity context;
	
	public ColorController(MainActivity _context){
        context = _context;
    }	
	
	public void activateColorController() {
		
		context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	
            	context.colorPick.setOnColorChangedListener(new OnColorChangedListener() {
        			@Override
        			public void onColorChanged(int color) {
        				 if(context.active_chanal!=0)
      
        				
        				 context.saturation.setGradient(context.colorPick.getColor());
        				// context.setColor(context.colorPick.getColor());
//        				String where = "_id=" + activePreset ;
//        				ContentValues cv = new ContentValues(); 
//        			    cv.put(activeChanalColor, colorPick.getColor()+"");
//        			    sqdb.update(DataB.T_PRESETS, cv, where, null);
//        			     int   	pic1r = (colorPick.getColor() >> 16) & 0xFF;
//        				 int	pic1g = (colorPick.getColor() >> 8) & 0xFF;
//        				 int 	pic1b = colorPick.getColor() & 0xFF;
        				 
//        				 np1.setValue(pic1r);
//        				 np2.setValue(pic1g);
//        				 np3.setValue(pic1b);		
						 
  
        				 if(context.active_chanal!=0)
							{
								ChanalView	chan = ((ChanalView) getViewByPosition(context.active_chanal-1, context.chanalList).findViewById(R.id.chanalView_forList));
								chan.setChanalIndicator(context.colorPick.getColor());
								context.cachanals.listArray.get(context.active_chanal-1).setColor(context.colorPick.getColor());
								String where = "_id="+ (context.active_chanal-1) ;
		        				ContentValues cv = new ContentValues();
		    					cv.put(DataB.CH_COLOR, context.colorPick.getColor()+"");
		    					context.sqdb.update(DataB.T_CHANALS, cv, where, null);
		    					context.setColor(context.colorPick.getColor());
							} 
        					if(context.active_chanalInPreset!=0)
							{
        						context.saturation.setGradient(context.colorPick.getColor());
								context.template.setChanalIndicatorColor(context.colorPick.getColor());
							}
        					new Handler().postDelayed(new Runnable() {
        		                @Override
        		                public void run() {
        		                	 context.saturation.setGradient(context.colorPick.getColor());
        		                }
        		              }, 200);
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
