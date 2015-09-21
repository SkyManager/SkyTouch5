package com.skyled.skytouch5;

import android.content.ContentValues;
import android.view.View;
import android.widget.ListView;

import com.skyled.skytouch5.thirdpart.ChanalView;
import com.skyled.skytouch5.thirdpart.SeekArc;
import com.skyled.skytouch5.thirdpart.SeekArc.OnSeekArcChangeListener;

public class BrightnesController {

	
protected MainActivity context;
	
	public BrightnesController(MainActivity _context){
        context = _context;
    }
	CAChanal caChanal;
	public void activateBrightnesController() {
		
		context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	caChanal=new CAChanal(context);
            	context.brightness.setArcRotation(210); 
            	context.brightness.setSweepAngle(300);
            	context.brightness.setTouchInSide(true);
            	context.brightness.setProgress(100); 
            	context.brightness.setRoundedEdges(true);
            	context.brightness.setOnSeekArcChangeListener(new OnSeekArcChangeListener() {
        			
        			@Override
        			public void onStopTrackingTouch(SeekArc seekArc) {	
        			}		
        			@Override
        			public void onStartTrackingTouch(SeekArc seekArc) {
        			}
        			
        			@Override
        			public void onProgressChanged(SeekArc seekArc, int progress,
        					boolean fromUser) {  
        					//				if(chanalMode)
        					//				{
        					//					String where = "_id=" + activePreset ;
        					//				 	ContentValues cv = new ContentValues();
        					//			        cv.put(activeChanalBR, progress+"");
        					//			        sqdb.update(DataB.T_PRESETS, cv, where, null);
        					//			        setChanalBright(activeChanalBR, progress);
        					//				}else{
        					//					String where = "_id=" + activePreset ;
        					//				 	ContentValues cv = new ContentValues();
        					//			        cv.put(DataB.P_BR, progress+"");
        					//			        sqdb.update(DataB.T_PRESETS, cv, where, null);
        					//				} 
											if(context.active_chanal!=0)
											{
												ChanalView	chan = ((ChanalView) getViewByPosition(context.active_chanal-1, context.chanalList).findViewById(R.id.chanalView_forList));
												chan.setChanalValue(progress);
												context.cachanals.listArray.get(context.active_chanal-1).setBrightness(progress);
						        				String where = "_id="+ (context.active_chanal-1) ;
						        				ContentValues cv = new ContentValues();
						    					cv.put(DataB.CH_BRIGHT, progress+"");
						    					context.sqdb.update(DataB.T_CHANALS, cv, where, null);	
											}
											if(context.active_chanalInPreset!=0)
												{
													context.template.setChanalIndicatorValue(progress);
												}
											context.setChanalBright(progress);        				
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
