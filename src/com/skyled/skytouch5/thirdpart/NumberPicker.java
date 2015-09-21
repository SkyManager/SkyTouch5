package com.skyled.skytouch5.thirdpart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

public class NumberPicker extends android.widget.NumberPicker {
		private Context context;
		private Typeface tfs;
		public NumberPicker(Context context, AttributeSet attrs) {
	     super(context, attrs);
	     this.context = context;
	   
	   }
	   
	   @Override
	   public void addView(View child) {
	     super.addView(child);
	     updateView(child);
	   }

	   @Override
	   public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
	     super.addView(child, index, params);
	     updateView(child);
	   }

	   @Override
	   public void addView(View child, android.view.ViewGroup.LayoutParams params) {
	     super.addView(child, params);
	     updateView(child);
	   
	   }

	   private void updateView(View view) {
		//   tfs = Typeface.createFromAsset(context.getAssets(),"fonts/font.ttf");
	     if(view instanceof EditText){    	 
	    //   ((EditText) view).setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/font.ttf"));
	       ((EditText) view).setTextSize(25);
	       ((EditText) view).setTextColor(Color.RED);
	     }
	   }

	 }