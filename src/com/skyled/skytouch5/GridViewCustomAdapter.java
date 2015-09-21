package com.skyled.skytouch5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewCustomAdapter extends ArrayAdapter
{
         Context context;
       
    

     public GridViewCustomAdapter(Context context) 
     {
             super(context, 0);
             this.context=context;
             
     }
    
     public int getCount() 
        {
                     return 8;
        }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) 
     {
             View row = convertView;
             
             if (row == null) 
             {
                     LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                     row = inflater.inflate(R.layout.grid_row, parent, false);


                     TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
                     ImageView imageViewIte = (ImageView) row.findViewById(R.id.imageView);
                     
					 switch (position) {
					 case 0:textViewTitle.setText("Комната "+(position+1));
					 		imageViewIte.setImageResource(R.drawable.room1);
					 break;
					 case 1:textViewTitle.setText("Комната "+(position+1));
							imageViewIte.setImageResource(R.drawable.room2);
					 break;
					 case 2:textViewTitle.setText("Комната "+(position+1));
							imageViewIte.setImageResource(R.drawable.room3);
					 break;
					 case 3:textViewTitle.setText("Комната "+(position+1));
							imageViewIte.setImageResource(R.drawable.room4);
					 break;
					 
					 default: textViewTitle.setText("Комната X");
					 		imageViewIte.setImageResource(R.drawable.ic_launcher);
					 break;
                     }
             } 

    
      
      return row;

     }

}
