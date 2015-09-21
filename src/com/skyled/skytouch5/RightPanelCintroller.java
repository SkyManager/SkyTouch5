package com.skyled.skytouch5;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

public class RightPanelCintroller {

	protected MainActivity context;
	
	public RightPanelCintroller(MainActivity _context){
        context = _context;
    }
	
	 public void activateRightPanel(){
         context.runOnUiThread(new Runnable() {
             @Override
             public void run() {
            	 context.rightPanel.setOnClickListener(new OnClickListener() {
            			@Override
            			public void onClick(View v) {
            				context.header.setVisibility(View.VISIBLE);
            		   
            				//context.chanals_main.setVisibility(View.VISIBLE);
            				context.mainBTN.setBackgroundColor(Color.RED);	
            				context.pressetBTN.setBackgroundColor(Color.parseColor("#4d4d4d"));  
            		  
            		        if(context.digitalClock.getVisibility() == View.VISIBLE) 
            		        {  
            		        	new Handler().postDelayed(new Runnable() {
            		                @Override
            		                public void run() {
            		                	context.brightness.demo();
            		                	context.brightness.startMode();
            		                }
            		              }, 500);
            	        	}
            		        context.chanalList.setVisibility(View.VISIBLE);
            		        context.digitalClock.setVisibility(View.INVISIBLE);
            		        context.rightPanel.setVisibility(View.INVISIBLE);
            			}
            		});
            	 
            	 
             }
        });
     }
}
