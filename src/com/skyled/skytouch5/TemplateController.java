package com.skyled.skytouch5;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.skyled.skytouch5.thirdpart.ChanalsRedactor.ActiveChanalEventListener;
import com.skyled.skytouch5.thirdpart.ChanalsRedactor.CloseRedactorEventListener;
import com.skyled.skytouch5.thirdpart.ChanalsRedactor.SwitchRedactorEventListener;


public class TemplateController {
	
protected MainActivity context;
	
	public TemplateController(MainActivity _context){
        context = _context;
    }		

	public void activateTemplateController() {
		
		
		context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	
            	
            	context.template.setCloseRedactorEventListener(new CloseRedactorEventListener() {
					
					@Override
					public void CloseRedactor() {
						context.cPanel.off();
						context.colorPick.setVisibility(View.INVISIBLE);
						context.saturation.setVisibility(View.INVISIBLE);
						context.brightness.setVisibility(View.VISIBLE);
						context.showlist=true;
						context.slider.setVisibility(View.INVISIBLE);
						context.mainBTN.getBackground().setAlpha(255);
						context.mainBTN.setTextColor(Color.argb(255, 255, 255, 255));

						context.sqlman.getPreset();
        				context.presetList.setLayoutAnimation(context.chanalIn);
        				context.template.setVisibility(View.INVISIBLE);
						context.presetList.setVisibility(View.VISIBLE);
						context.templateContainer.setVisibility(View.INVISIBLE);
						context.active_chanalInPreset=0;
					}
				});
            	
            	
            	
            	context.template.setOnClickListener(new OnClickListener() { 
        			
        			@Override
        			public void onClick(View arg0) {

        			}
        		});
            	context.template.setSwitchRedactorEventListener( new SwitchRedactorEventListener() {
        			@Override
        			public void SwitchRedactor() {
        			  //	context.timeRedactor(context.bufID+"");
        				Toast.makeText(context, "В разработке", 5000).show();
        				
        			}
        	});  
            	
            	context.template.setActiveChanalEventListener(new ActiveChanalEventListener() {
					
					@Override
					public void onEvent() {
						context.active_chanalInPreset=context.template.getActiveChanal();
						context.cPanel.on();
						context.saturation.setGradient(context.colorPick.getColor());
					}
				});
            	
            }
       });
		
	}

}
