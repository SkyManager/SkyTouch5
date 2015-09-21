package com.skyled.skytouch5;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.skyled.skytouch5.thirdpart.ChanalView;



public class CAChanal extends BaseAdapter {
    private Context context;

    protected MainActivity MAcontext;

    public CAChanal(MainActivity _context){
        MAcontext = _context;
    }

    public CAChanal(Context context) { 
        this.context = context;
    }

    ArrayList<DMChanal> listArray;
  
    public CAChanal() {
        listArray = new ArrayList<DMChanal>(15);
    }
    @Override
    public int getCount() {
        return listArray.size(); 
    }

    @Override
    public Object getItem(int i) {
        return listArray.get(i); 
    }

    @Override
    public long getItemId(int i) {
        return i; 
    }
    
    
    boolean [] chanalElementStates = new boolean[MAcontext.chanalsNumber] ;
    int bright[]=new int[MAcontext.chanalsNumber];
    
    
    @Override
    public View getView(final int index,View view, ViewGroup parent) {
         final ViewHolder holder ; 
        if (view == null) { 
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.chanal_template, null);
            holder = new ViewHolder(); 
            
            holder.chanalElement=(ChanalView)view.findViewById(R.id.chanalView_forList);
            view.setTag(holder);
        }else
        {
             holder = (ViewHolder)view.getTag();
        }

        final DMChanal dataModel = listArray.get(index);   
		holder.chanalElement.setChanalName(dataModel.getName());
		bright[index]=dataModel.getBrightnes();
		holder.chanalElement.setChanalValue(bright[index]);
		holder.chanalElement.setChanalIndicator(dataModel.getColor());
		
         holder.chanalElement.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {  
            	for(int i=0;i<chanalElementStates.length;i++)
            	{
            		chanalElementStates[i] = false;	
            	}
            	MAcontext.chanalList.invalidateViews();  

            	boolean enabled = !chanalElementStates[index];
            	chanalElementStates[index] = enabled;
                updateEnabledState(holder, enabled,bright[index]);
            	if(MAcontext.active_chanal-1==index)
				{
            		MAcontext.cPanel.off();
            		MAcontext.active_chanal=0;
					MAcontext.colorPick.setVisibility(View.INVISIBLE);
					MAcontext.saturation.setVisibility(View.INVISIBLE);
					MAcontext.brightness.setVisibility(View.VISIBLE);
					MAcontext.slider.setVisibility(View.INVISIBLE);
			    	enabled = !chanalElementStates[index];
	            	chanalElementStates[index] = enabled;
	                updateEnabledState(holder, enabled,bright[index]);
				}else
				{ 
					MAcontext.cPanel.on();
            		MAcontext.active_chanal=index+1;
            		MAcontext.brightness.setProgress(dataModel.getBrightnes());	
            		MAcontext.colorPick.setColor(dataModel.getColor());
					MAcontext.saturation.setGradient(dataModel.getColor());
					MAcontext.saturation.invalidate();
				//	MAcontext.saturation.setProgress(dataModel.getSaturation());
				}	
            } 
        });
         bright[index] = holder.chanalElement.getChanalValue();
  
        updateEnabledState(holder, chanalElementStates[index],bright[index] );  
        return view;
    }   
    
    private void updateEnabledState(ViewHolder holder, boolean enable, int bright) {
        if (enable)
        {
        	  holder.chanalElement.enableChanal();
        } 
        else
        {
			holder.chanalElement.disableChanal();  
        }     
        holder.chanalElement.setChanalValue(bright);
    }
    
    static class ViewHolder {
         ChanalView chanalElement;
        }

    public Object getFilter() {

        return null;
    } 


}  