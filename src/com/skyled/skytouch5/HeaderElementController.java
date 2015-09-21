package com.skyled.skytouch5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;


public class HeaderElementController {

protected MainActivity context;
	
	public HeaderElementController(MainActivity _context){
        context = _context;
    }		
	
	public void activateHeaderElementController() {
	
		
		context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	context.digitalClock.setVisibility(View.VISIBLE);
            	context.warper.setOnClickListener(new OnClickListener() {
        			
        			@Override
        			public void onClick(View v) { 

        			}
        		});
            	
            	context.templateContainer.setVisibility(View.INVISIBLE); 
        	
            	context.ON_OFF=(ImageView)context.findViewById(R.id.ON_OFF);
            	context. ON_OFF.setOnClickListener(new OnClickListener() {
        			@Override
        			public void onClick(View v) {	
        				new AlertDialog.Builder(context)
        			    .setTitle("Пинг мейна")
        			    .setMessage( context.ping("192.168.1.190"))
//        			    .setMessage( context.getSystemTime())
        			    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
        			        public void onClick(DialogInterface dialog, int which) { 
        			          
        			        }
        			     })
        			    .setIcon(android.R.drawable.ic_dialog_alert)
        			     .show();
        				
        			}
        		});
            	
            	context.effectBTN.getBackground().setAlpha(45);
            	context.effectBTN.setTextColor(Color.argb(45, 255, 255, 255));

        		
        		
            	context.mainBTN.setOnClickListener(new OnClickListener() {
        			
        			@Override
        			public void onClick(View arg0) {
        				if(context.showlist)
        				{
        					context.mainBTN.setBackgroundColor(Color.RED);	
        					context.pressetBTN.setBackgroundColor(Color.parseColor("#4d4d4d"));
        					context.presetList.setVisibility(View.INVISIBLE);
        					context.template.setVisibility(View.INVISIBLE);
        					context.chanalList.setVisibility(View.VISIBLE);
        					context.showlist=false;	
        				}

        			}
        		});
            	context.pressetBTN.setOnClickListener(new OnClickListener() {
        				
        				@Override
        				public void onClick(View arg0) {
        					
        						context.pressetBTN.setBackgroundColor(Color.RED);	
        						context.mainBTN.setBackgroundColor(Color.parseColor("#4d4d4d"));
        						context.presetList.setVisibility(View.VISIBLE);
        						context.showlist=true;
        						context.chanalList.setVisibility(View.INVISIBLE);
        						context.sqlman.getPreset();
        						if(context.cPanel.isActive()){context.cPanel.off();
        						context.colorPick.setVisibility(View.INVISIBLE);
        						context.saturation.setVisibility(View.INVISIBLE);
        						context.brightness.setVisibility(View.VISIBLE);
        						context.brightness.demo();
        						context.headStatus.setText("");}
        					
        				}
        			});
        		
            	
        		context.MainBack.setOnClickListener(new OnClickListener() {
        			
        			@Override
        			public void onClick(View v){ 
        				context.rightPanel.setVisibility(View.VISIBLE);
        				context.template.setVisibility(View.INVISIBLE);
        				//context.chanals_main.setVisibility(View.INVISIBLE);
        				context.chanalList.setVisibility(View.INVISIBLE);
        				context.templateContainer.setVisibility(View.INVISIBLE); 
        				context.header.setVisibility(View.INVISIBLE);
        		        if(context.showlist)
        		        {
        		        	context.presetList.setVisibility(View.INVISIBLE);
        		        }
        		        context.digitalClock.setVisibility(View.VISIBLE);
        			}
        		}); 
            	
            	            	
            }
       });
		
		
	}

}
