package com.skyled.skytouch5;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.skyled.skytouch5.thirdpart.PresetView;
import com.skyled.skytouch5.thirdpart.PresetView.ActivePresetListener;

public class CAPresets extends BaseAdapter {
	private Context context;
	protected MainActivity MAcontext;
	
	
    public CAPresets(Context context) { 
        this.context = context;
    }
    public CAPresets(MainActivity _context){
        MAcontext = _context;
    }
	private static final String TAG = CAPresets.class.getSimpleName();
	ArrayList<DMPresets> listArray;
	public CAPresets() {
		listArray = new ArrayList<DMPresets>(5);
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

	boolean [] presetElementStates = new boolean[30] ;
	
	@Override
	public View getView(final int index,View view, final ViewGroup parent) {
		
        final ViewHolder holder ; 
        if (view == null) { 
				LayoutInflater inflater = LayoutInflater.from(parent.getContext());
				view = inflater.inflate(R.layout.preset_template, parent, false);
            holder = new ViewHolder();   
            holder.presetElement=(PresetView)view.findViewById(R.id.presetView_forList);
            holder.wraperBtn=(RelativeLayout)view.findViewById(R.id.wraper_btn_preset);
            view.setTag(holder);
        }else
        {
             holder = (ViewHolder)view.getTag();
        }
		 final DMPresets dataModel = listArray.get(index);	
		 holder.presetElement.setPresetName(dataModel.getName());
		 holder.presetElement.setPresetTime(dataModel.getTiming());
		 

 
		 holder.presetElement.setActivatePresetListener(new ActivePresetListener() {
			@Override
			public void onActivate() {
				
				
				for(int i=0;i<presetElementStates.length;i++)
            	{
					presetElementStates[i] = false;	
            	}
            	
				boolean enabled = !presetElementStates[index];
				presetElementStates[index] = enabled;
	            updateEnabledState(holder, enabled);	
	            
	            if(MAcontext.active_preset-1==index)
	            {
	            	MAcontext.active_preset=0;
	            	enabled = !presetElementStates[index];
					presetElementStates[index] = enabled;
		            updateEnabledState(holder, enabled);	
	            }else
	            {
	            	MAcontext.active_preset=index+1;
	            }
	            MAcontext.presetList.invalidateViews();  
				
//			int[] colors={Integer.parseInt(dataModel.getChanal1()), Integer.parseInt(dataModel.getChanal2()),Integer.parseInt(dataModel.getChanal3()),Integer.parseInt(dataModel.getChanal4()),Integer.parseInt(dataModel.getChanal5()) };
//        	String[] chbright={dataModel.getChanalbr1(), dataModel.getChanalbr2(),dataModel.getChanalbr3(),dataModel.getChanalbr4(),dataModel.getChanalbr5()};
//			MainActivity.presetChanges(dataModel.getPid(), dataModel.getName(),"on", dataModel.getBrightnes(), colors, chbright );
			}
		});
 

		holder.wraperBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
        	  int[] location = new int[2];
        	  view.getLocationOnScreen(location);
        	  Log.e("Cord1 = ", location[1]+"");
        	 int[] colors={Integer.parseInt(dataModel.getChanal1()), Integer.parseInt(dataModel.getChanal2()),Integer.parseInt(dataModel.getChanal3()),Integer.parseInt(dataModel.getChanal4()),Integer.parseInt(dataModel.getChanal5()) };
        	  String[] chbright={dataModel.getChanalbr1(), dataModel.getChanalbr2(),dataModel.getChanalbr3(),dataModel.getChanalbr4(),dataModel.getChanalbr5()};
        	  MainActivity.rChanelsRedactor(dataModel.getPid(), dataModel.getName(),dataModel.getStatus(), dataModel.getBrightnes(), dataModel.getTiming(),
        	 colors, chbright, location[1] );
        	 
          }

	});
		 
		updateEnabledState(holder, presetElementStates[index]);  
		return view;
	} 
	
    private void updateEnabledState(ViewHolder holder, boolean enable) {
        if (enable)
        {
        	  holder.presetElement.enablePreset();
        } 
        else
        {
			holder.presetElement.disablePreset(); 
        }     
    }
	
	  static class ViewHolder {
		  		PresetView presetElement;
		  		RelativeLayout wraperBtn;
	        }

	public Object getFilter() {

		return null;
	} 


}