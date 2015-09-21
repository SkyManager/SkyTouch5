package com.skyled.skytouch5;

import java.util.ArrayList;

import com.skyled.skytouch5.thirdpart.FlashButton;
import com.skyled.skytouch5.thirdpart.FlashButton.FlashEnum;
import com.skyled.skytouch5.thirdpart.FlashButton.FlashListener;
import com.skyled.skytouch5.thirdpart.TextViewN;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ExpListAdapter extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<String>> mGroups;
    private Context mContext;
    private ArrayList<String> mStates;
    private FlashEnum mState;
  
    public ExpListAdapter (Context context,ArrayList<ArrayList<String>> groups, ArrayList<String> states){
        mContext = context;
        mGroups = groups;
        mStates = states;
    }
    
    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView,
                             final ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }

        if (isExpanded){
           //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }

        final LinearLayout groupLayLeft = (LinearLayout) convertView.findViewById(R.id.group_lay_left);
        final LinearLayout groupLayRight = (LinearLayout) convertView.findViewById(R.id.group_lay_right);
        TextViewN textGroup = (TextViewN) convertView.findViewById(R.id.textGroup);
        textGroup.setText(Integer.toString(groupPosition+1) + ".1" + " Канал");
        final TextViewN textMode = (TextViewN) convertView.findViewById(R.id.textMode);
        //textMode.setText("OFF");
        FlashButton flButton = (FlashButton) convertView.findViewById(R.id.flashButton1);
        
        
        /*String temp = mStates.get(groupPosition*3);
        if(temp.contains("0"))
        {
        	flButton.setState(mState.OFF);
        	textMode.setText("OFF");
        	groupLayLeft.setBackgroundResource(R.drawable.list_item_corner);
			groupLayRight.setBackgroundResource(R.drawable.list_item_corner);
        }
        if(temp.contains("1"))
        {
        	flButton.setState(mState.MONO);
        	textMode.setText("Mono");
        	((ExpandableListView) parent).expandGroup(groupPosition, true);
        	groupLayLeft.setBackgroundResource(R.drawable.list_group_item_corner);
			groupLayRight.setBackgroundResource(R.drawable.list_group_item_corner);
        }
        if(temp.contains("2"))
        {
        	flButton.setState(mState.RGB);
        	textMode.setText("RGB");
        	groupLayLeft.setBackgroundResource(R.drawable.list_group_item_corner);
			groupLayRight.setBackgroundResource(R.drawable.list_group_item_corner);
        }*/
        
        flButton.setFlashListener(new FlashListener() {
			
			@Override
			public void onRgb() {
				// TODO Auto-generated method stub
				((ExpandableListView) parent).collapseGroup(groupPosition);
				textMode.setText("RGB");
				groupLayLeft.setBackgroundResource(R.drawable.list_group_item_corner);
				groupLayRight.setBackgroundResource(R.drawable.list_group_item_corner);
			}
			
			@Override
			public void onOff() {
				// TODO Auto-generated method stub
				((ExpandableListView) parent).collapseGroup(groupPosition);
				textMode.setText("OFF");
				groupLayLeft.setBackgroundResource(R.drawable.list_item_corner);
				groupLayRight.setBackgroundResource(R.drawable.list_item_corner);
			}
			
			@Override
			public void onMono() {
				// TODO Auto-generated method stub
	            ((ExpandableListView) parent).expandGroup(groupPosition, true);
	            textMode.setText("Mono");
	            groupLayLeft.setBackgroundResource(R.drawable.list_group_item_corner);
	            groupLayRight.setBackgroundResource(R.drawable.list_group_item_corner);
			}
		});
        
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }

        final LinearLayout childLayLeft = (LinearLayout) convertView.findViewById(R.id.child_lay_left);
        final LinearLayout childLayRight = (LinearLayout) convertView.findViewById(R.id.child_lay_right);
        TextView textChild = (TextView) convertView.findViewById(R.id.textChildN);
        textChild.setText(mGroups.get(groupPosition).get(childPosition));

        ToggleButton btnOnoffChanel = (ToggleButton)convertView.findViewById(R.id.toggleOnOffChanel);
        btnOnoffChanel.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					{
					childLayLeft.setBackgroundResource(R.drawable.list_group_item_corner);
					childLayRight.setBackgroundResource(R.drawable.list_group_item_corner);
					}
				else
					{
					childLayLeft.setBackgroundResource(R.drawable.list_item_corner);
					childLayRight.setBackgroundResource(R.drawable.list_item_corner);
					}
			}
		});

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
