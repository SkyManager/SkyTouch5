package com.skyled.skytouch5;

import java.util.ArrayList;
import java.util.regex.Pattern;

import android.app.Activity;  
import android.content.Context;  
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;  
import android.os.Handler;
//import android.util.Log;
import android.view.View;  
import android.view.ViewGroup;  
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;  
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Gallery;  
import android.widget.ImageView;  
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends Activity  {
	
	private Gallery mGallery;  
	private ImageAdapter mImageAdapter;
	private Button butSave, butCancel, butGetState;
	private TextView mTextView;
	private TextView mTextViewIp;
	private TextView mTextViewIpCam;
	//private DatePicker mDatePicker;
	Context mContext;
	private long MyDataID;
	NetTask netTask;
	ArrayList<String> states = new ArrayList<String>();
	Handler h;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        	
        // Р“Р°Р»РµСЂРµСЏ
        mGallery = (Gallery) findViewById(R.id.gallery);
        mImageAdapter = new ImageAdapter(this);  
        mGallery.setAdapter(mImageAdapter);
        
        
        // РўРµРєСЃС‚ Рё РґР°С‚Р°
        mTextView = (TextView) findViewById(R.id.DescText);
        mTextViewIp = (TextView) findViewById(R.id.IpText);
        mTextViewIpCam = (TextView) findViewById(R.id.IpCamText);
        final int port = 1000;
        
        final ExpandableListView listView = (ExpandableListView)findViewById(R.id.expandableListView1);
        listView.setGroupIndicator(null);
        
      //Создаем набор данных для адаптера
        
        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
        ArrayList<String> children1 = new ArrayList<String>();
        ArrayList<String> children2 = new ArrayList<String>();
        ArrayList<String> children3 = new ArrayList<String>();
        ArrayList<String> children4 = new ArrayList<String>();
        ArrayList<String> children5 = new ArrayList<String>();
        children1.add("1.2 Канал");
        children1.add("1.3 Канал");
        groups.add(children1);
        children2.add("2.2 Канал");
        children2.add("2.3 Канал");
        groups.add(children2);
        children3.add("3.2 Канал");
        children3.add("3.3 Канал");
        groups.add(children3); 
        children4.add("4.2 Канал");
        children4.add("4.3 Канал");
        groups.add(children4); 
        children5.add("5.2 Канал");
        children5.add("5.3 Канал");
        groups.add(children5); 
       //Создаем адаптер и передаем context и список с данными
        final ExpListAdapter adapter = new ExpListAdapter(getApplicationContext(), groups, states);
        //listView.setAdapter(adapter);
        
		

		//queryDevice("get config", md_temp.getIP(), port);
        
        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
            	listView.setAdapter(adapter);
            };
          };
        
        if (getIntent().hasExtra("MyData")) {
        	MyData md = (MyData) getIntent().getSerializableExtra("MyData");
        	//Date d = new Date (md.getDate());
        	//mDatePicker.updateDate(d.getYear() + 1900, d.getMonth(), d.getDate());
        	mGallery.setSelection(mImageAdapter.getPositionbyResId(md.getIcon()));
        	mTextView.setText(md.getTitle());
        	mTextViewIp.setText(md.getIP());
        	mTextViewIpCam.setText(md.getIPCam());
        	MyDataID = md.getID();
        	//множится асинктаск...//
			queryDevice("get config", md.getIP(), port);
			//listView.setAdapter(adapter);
    		
        }
        else {
        	MyDataID = -1;
        	mGallery.setSelection(mImageAdapter.getCount() / 2);
        	for(int i=0;i<15;i++)
				{
					states.add("0");
				}
        	listView.setAdapter(adapter);
        }
        
        // РљРЅРѕРїРєРё
        butSave = (Button) findViewById(R.id.butSave);
        butCancel = (Button) findViewById(R.id.butCancel);
        
        butSave.setOnClickListener (new OnClickListener() {
            public void onClick(View v) {
            	
            	//Date date = new Date(mDatePicker.getYear()-1900, mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
           		MyData md = new MyData (MyDataID, mTextViewIp.getText().toString(), mTextViewIpCam.getText().toString(), mTextView.getText().toString(), mImageAdapter.getResourceId(mGallery.getSelectedItemPosition()));
            	Intent intent = getIntent();
            	intent.putExtra("MyData", md);
            	setResult(RESULT_OK, intent);
            	finish();
            }
         });

        butCancel.setOnClickListener (new OnClickListener() {
            public void onClick(View v) {
            	setResult(RESULT_CANCELED, new Intent());
            	finish();
            }
         });
    }
    
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        int bg;

        private int[] mImageIds = {  
        		R.drawable.room1, R.drawable.room2, R.drawable.room3, R.drawable.room4};  

        public ImageAdapter(Context c) {
            mContext = c;
            TypedArray attr = mContext.obtainStyledAttributes(R.styleable.MyGallery);
            bg = attr.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            attr.recycle();
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }
        
        public int getResourceId(int position) {
        	
        	int id = mImageIds[position];
            return id;
        }
        
        public int getPositionbyResId(int ResId) {
        	
        	for (int i = 0; i < mImageIds.length; i++)
        		if (mImageIds[i] == ResId)
        			return i;
            return 0;
        }
        
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mImageIds[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setBackgroundResource(bg);
            imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            return imageView;
        }
    }
    public void queryDevice(String param, String ip, int port)
    {
		
     netTask= new NetTask(ip, port);
       netTask.setNetListener(new NetTask.NetTaskResult() {
          @Override
          public void onPreExecuteConcluded() {
        	  
          }
          @Override
          public void onPostExecuteConcluded(String result) {
      				Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
      				toast.show();
      				String[] separated = result.split("([A-Z])\\w+: ch\\d\\d=| +ch\\d\\d=");
      				for(int i=0;i<15;i++)
      				{
      					states.add(separated[i+1]);
      				}
      				h.sendEmptyMessage(1);
          }
      });
        netTask.execute(param);
    }
}
