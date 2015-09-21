/**
 * 
 */
package com.skyled.skytouch5;

import java.util.Calendar;

import net.simonvt.numberpicker.NumberPicker;
import net.simonvt.numberpicker.NumberPicker.OnValueChangeListener;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.skyled.skytouch5.thirdpart.Shedule;
import com.skyled.skytouch5.thirdpart.TextViewN;
import com.skyled.skytouch5.thirdpart.weekBtns;


public class PresetRedactor extends Activity{
     Shedule  shedule;
     weekBtns week;
     SQLiteDatabase sqdb ;
     String [] monday, tuesday, wednesday, thurthday, friady, saturday, sunday;
     RelativeLayout block_wraper;
     float[] fMond, fTues, fWedn, fThur, fFrid, fSatu,fSund;
     TextViewN back_btn,changeBTN, time_on, time_off, time_back, sensors, status;
     TimerDialogClass td;
     SensorDialogClass sd;
     System_statusDialogClass statd;
     boolean t_on,t_back;
     int dialog_type=1;
     String[] presetListNEW= new String[5];
     int change_preset_id=1;
     EditText edit_name;
     float[][] shedTiming= new float [7][10];
     ToggleButton switchOnRedactor;
	@Override  
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		 getActionBar().hide();
		 DisplayMetrics metrics = getResources().getDisplayMetrics();
		 int densityDpi = (int)(metrics.density * 160f);
		 Display display = getWindowManager().getDefaultDisplay(); 
		 int width = display.getWidth();
		 int height = display.getHeight();
		 if (densityDpi==160){}else{setContentView(R.layout.preset_redactor_213);}
		 getWindow().setSoftInputMode(
		 WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		 getWindow().getDecorView()
         .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE); 
		 
		 Intent intent = getIntent();
		 change_preset_id=Integer.parseInt(intent.getStringExtra("presetID"));
		 
		 Calendar calendar = Calendar.getInstance();
		 int day = calendar.get(Calendar.DAY_OF_WEEK)-1; 

		Log.e("curent preset id", change_preset_id+""); 
		DataB sqh = new DataB(this);
		sqdb = sqh.getWritableDatabase();  
		block_wraper=(RelativeLayout)findViewById(R.id.block_wraper);
		shedule=(Shedule)findViewById(R.id.shedule1);
		week=(weekBtns)findViewById(R.id.weekBtns1);
		back_btn=(TextViewN)findViewById(R.id.MainBack);
		changeBTN=(TextViewN)findViewById(R.id.changeBTN);
		time_on=(TextViewN)findViewById(R.id.timeOn);
		time_off=(TextViewN)findViewById(R.id.textView3);
		time_back=(TextViewN)findViewById(R.id.textViewN8);
		sensors=(TextViewN)findViewById(R.id.textViewN6);
		status=(TextViewN)findViewById(R.id.textViewN10);
		edit_name=(EditText)findViewById(R.id.editText1);
		edit_name.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/font.ttf"));
		switchOnRedactor=(ToggleButton)findViewById(R.id.switchRedactor);
		time_off.setText("24:00");
		week.setCurentDay(day);
		
		// Block wraper clicking 
		block_wraper.setOnClickListener(new OnClickListener() {@Override public void onClick(View v) {}});
		//___________________________________
		
		switchOnRedactor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
				{
					block_wraper.setVisibility(View.GONE);	
				}else
				{
					block_wraper.setVisibility(View.VISIBLE);	
				}
			}
		});
		
////////////************************************DRAWING TIMLINES FROM DATA BASE ***************************************************		
		String query = "SELECT "+ DataB.S_DAY1 +", "+ DataB.S_DAY2  +", "+DataB.S_DAY3 +										//
				", "+DataB.S_DAY4 +", "+DataB.S_DAY5 +", "+DataB.S_DAY6 +", "+DataB.S_DAY7 +									//
	  			" FROM " + DataB.T_SHEDULE;																						//
    	Cursor cursor = sqdb.rawQuery(query, null);																				//
    	if(cursor.getCount()==0)																								//
    	{																														//
    		setTextTowsDb();																									//
    	}else{
    		fMond=new float[10];
    		fTues=new float[10];
    		fWedn=new float[10];
    		fThur=new float[10];
    		fFrid=new float[10];
    		fSatu=new float[10];
    		fSund=new float[10];
			while (cursor.moveToNext()) {
				monday= cursor.getString(cursor.getColumnIndex(DataB.S_DAY1)).split(",");
				tuesday = cursor.getString(cursor.getColumnIndex(DataB.S_DAY2)).split(",");											//
				wednesday = cursor.getString(cursor.getColumnIndex(DataB.S_DAY3)).split(",");										//
				thurthday = cursor.getString(cursor.getColumnIndex(DataB.S_DAY4)).split(",");										//
				friady = cursor.getString(cursor.getColumnIndex(DataB.S_DAY5)).split(",");											//
				saturday = cursor.getString(cursor.getColumnIndex(DataB.S_DAY6)).split(",");
				sunday = cursor.getString(cursor.getColumnIndex(DataB.S_DAY7)).split(",");
				for(int i=0; i<10; i++)
				{
					fMond[i]=Float.parseFloat(monday[i]);
					fTues[i]=Float.parseFloat(tuesday[i]);
					fWedn[i]=Float.parseFloat(wednesday[i]);
					fThur[i]=Float.parseFloat(thurthday[i]);
					fFrid[i]=Float.parseFloat(friady[i]);
					fSatu[i]=Float.parseFloat(saturday[i]);
					fSund[i]=Float.parseFloat(sunday[i]);
				}																													//
																																	//
				shedule.setDrawShedules(fMond,fTues, fWedn, fThur, fFrid,fSatu,fSund);												//
			 }}																														//
		cursor.close();																												//
		////////////*****************************************************************************************************************	
			
		
			
		////////////************************************NAMING PRESETS IN SHEDULE***************************************************	
		  	String list_query = "SELECT " + DataB.P_ID+", "+ DataB.P_TIMING+", "+ DataB.P_BG_ACTYVITY+", "+ DataB.P_BG_ACTIVESENSORS+", "+ DataB.P_BG_SENSORTIME+", "
		  			+ DataB.P_BG_ACTIVEDAYS+", "+ DataB.P_BG_LIGHTESTATUS+", "+ DataB.P_NAME +" FROM " + DataB.T_PRESETS;	
		  	//
		  	cursor = sqdb.rawQuery(list_query, null);
		  	while (cursor.moveToNext()) {
				String id = cursor.getString(cursor.getColumnIndex(DataB.P_ID));
				String preset_name = cursor.getString(cursor.getColumnIndex(DataB.P_NAME));
				presetListNEW[cursor.getPosition()]=preset_name;
					if(cursor.getPosition()==change_preset_id-1)
					{
						if(cursor.getString(cursor.getColumnIndex(DataB.P_BG_ACTYVITY)).equals("on"))
						{switchOnRedactor.setChecked(true);}else{switchOnRedactor.setChecked(false);}

				        boolean[] active_days=new boolean[7];
				    	Log.e("ee", cursor.getString(cursor.getColumnIndex(DataB.P_BG_ACTIVEDAYS)));  
				    	String adays[] = cursor.getString(cursor.getColumnIndex(DataB.P_BG_ACTIVEDAYS)).split(",");
						for(int i=0; i<7; i++)
						{
							if(adays[i].equals("1")){active_days[i]=true;}else{active_days[i]=false;}
						}
						week.setActivetDays(active_days);
						sensors.setText(cursor.getString(cursor.getColumnIndex(DataB.P_BG_ACTIVESENSORS)));
						time_back.setText(cursor.getString(cursor.getColumnIndex(DataB.P_BG_SENSORTIME)));
						status.setText(cursor.getString(cursor.getColumnIndex(DataB.P_BG_LIGHTESTATUS)));
						///fdsdfsdf
						String []	start_stop=cursor.getString(cursor.getColumnIndex(DataB.P_TIMING)).split(" - ");
						time_on.setText(start_stop[0]);
						time_off.setText(start_stop[1]);
					}
		  	}
		  	cursor.close();
		  	edit_name.setText(presetListNEW[change_preset_id-1]);
			shedule.setPresetList(presetListNEW);																				   //
		////////////*****************************************************************************************************************			
			
			edit_name.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
			
				}
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				}
				@Override
				public void afterTextChanged(Editable s) {
					presetListNEW[change_preset_id-1]=edit_name.getText().toString();
					shedule.setPresetList(presetListNEW);
				}
			});		
			
	back_btn.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
         Intent intent=new Intent(PresetRedactor.this ,MainActivity.class);
		 startActivity(intent);	
		}
	});
	changeBTN.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
		saveParameters();
			
         Intent intent=new Intent(PresetRedactor.this ,MainActivity.class);
		 startActivity(intent);	
		}
	}); 
                                                                                                                                                                                                                                                        
	 td=new TimerDialogClass(PresetRedactor.this);
	 sd=new SensorDialogClass(PresetRedactor.this);
	 statd=new System_statusDialogClass(PresetRedactor.this);
	 td.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#CCffffff")));
	 sd.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#CCffffff")));
	 statd.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#CCffffff")));
	 time_on.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			td.show();	
			t_on=true;
			t_back=false;
		}
	});
	   time_off.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				dialog_type=1;
				td.show();	
				t_on=false;
				t_back=false;
			}
		});
	   time_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog_type=1;
				td.show();	
				t_back=true;
			}
		});
	   sensors.setOnClickListener(new OnClickListener(
			   ) {
		@Override
		public void onClick(View v) {
			sd.show();	
		}
	   });
	   status.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			statd.show();
		}
	   });
	   
	   week.setOnTouchListener(new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_UP:	
				timeDrawer();
				break;
			}
			return false;
		}
	});
	   
	   timeDrawer();
	   
	   

	}
	
	
	
	////////////************************************Save all parameters ***************************************************	
	private void saveParameters()
	{
		ContentValues cvShed = new ContentValues();
		String fmondS="";String ftesdS="";String fwednS="";String fthusS="";String ffridS="";String fsatuS="";String fsandS="";
		for(int i=0; i<fMond.length;i++)
		{
			fmondS+=fMond[i]+",";
			ftesdS+=fTues[i]+",";	
			fwednS+=fWedn[i]+",";	
			fthusS+=fThur[i]+",";	
			ffridS+=fFrid[i]+",";	
			fsatuS+=fSatu[i]+",";
			fsandS+=fSund[i]+",";	
		}
		cvShed.put(DataB.S_DAY1,  fmondS);
		cvShed.put(DataB.S_DAY2,  ftesdS);
		cvShed.put(DataB.S_DAY3,  fwednS);
		cvShed.put(DataB.S_DAY4,  fthusS);
		cvShed.put(DataB.S_DAY5,  ffridS);
		cvShed.put(DataB.S_DAY6,  fsatuS);
		cvShed.put(DataB.S_DAY7,  fsandS);
		sqdb.update(DataB.T_SHEDULE, cvShed, "_id=1", null);
		
		String where = "_id=" + change_preset_id;
	 	ContentValues cv = new ContentValues();
	 
	 	// NAME SAVING
        cv.put(DataB.P_NAME, edit_name.getText().toString());
        cv.put(DataB.P_TIMING, time_on.getText()+" - "+time_off.getText());
        // BG_ACTIVITY REDACTOR SAVING
        if(switchOnRedactor.isChecked()) cv.put(DataB.P_BG_ACTYVITY, "on"); else cv.put(DataB.P_BG_ACTYVITY, "off");
        // BG_ACTIVDYS REDACTOR SAVING
        boolean[] active_days=week.getActiveDays();
        String adays = "";
		for(int i=0; i<7; i++)
		{
			if (active_days[i])
			{adays+="1,";
			
			}
			else
			{adays+="0,"; } 
		}
		cv.put(DataB.P_BG_ACTIVEDAYS, adays);
		cv.put(DataB.P_BG_ACTIVESENSORS, sensors.getText()+"");
		// P_BG_SENSORTIME REDACTOR SAVING
		cv.put(DataB.P_BG_SENSORTIME, time_back.getText()+"");
		cv.put(DataB.P_BG_LIGHTESTATUS, status.getText()+"");
        sqdb.update(DataB.T_PRESETS, cv, where, null);
   
	}
	
	


	public void setTextTowsDb()
	{
	    ContentValues cv = new ContentValues();
		cv.put(DataB.S_DAY1,  "0,0,0,0,0,0,0,0,0,0");
		cv.put(DataB.S_DAY2,  "0,0,0,0,0,0,0,0,0,0");
		cv.put(DataB.S_DAY3,  "0,0,0,0,0,0,0,0,0,0");
		cv.put(DataB.S_DAY4,  "0,0,0,0,0,0,0,0,0,0");
		cv.put(DataB.S_DAY5,  "0,0,0,0,0,0,0,0,0,0");
		cv.put(DataB.S_DAY6,  "0,0,0,0,0,0,0,0,0,0");
		cv.put(DataB.S_DAY7,  "0,0,0,0,0,0,0,0,0,0");
		sqdb.insert(DataB.T_SHEDULE, null, cv);    
	}
	
	private void timeDrawer()
	{	
		boolean[] active_days=week.getActiveDays();
		for(int i=0; i<7; i++)
		{
			if(active_days[i]){
				switch (i) {
				case 0:
					fMond[change_preset_id*2-2]= Float.parseFloat(((String) time_on.getText()).replace(':','.'));     //start
					fMond[change_preset_id*2-1]=Float.parseFloat(((String) time_off.getText()).replace(':','.'));     //stop
				break;
				case 1:
					fTues[change_preset_id*2-2]= Float.parseFloat(((String) time_on.getText()).replace(':','.'));     //start
					fTues[change_preset_id*2-1]=Float.parseFloat(((String) time_off.getText()).replace(':','.'));     //stop
					break;
				case 2:
					fWedn[change_preset_id*2-2]= Float.parseFloat(((String) time_on.getText()).replace(':','.'));     //start
					fWedn[change_preset_id*2-1]=Float.parseFloat(((String) time_off.getText()).replace(':','.'));     //stop
					break;
				case 3:
					fThur[change_preset_id*2-2]= Float.parseFloat(((String) time_on.getText()).replace(':','.'));     //start
					fThur[change_preset_id*2-1]=Float.parseFloat(((String) time_off.getText()).replace(':','.'));     //stop
					break;
				case 4:
					fFrid[change_preset_id*2-2]= Float.parseFloat(((String) time_on.getText()).replace(':','.'));     //start
					fFrid[change_preset_id*2-1]=Float.parseFloat(((String) time_off.getText()).replace(':','.'));     //stop
					break;
				case 5:
					fSatu[change_preset_id*2-2]= Float.parseFloat(((String) time_on.getText()).replace(':','.'));     //start
					fSatu[change_preset_id*2-1]=Float.parseFloat(((String) time_off.getText()).replace(':','.'));     //stop
					break;
				case 6:
					fSund[change_preset_id*2-2]= Float.parseFloat(((String) time_on.getText()).replace(':','.'));     //start
					fSund[change_preset_id*2-1]=Float.parseFloat(((String) time_off.getText()).replace(':','.'));     //stop
					break;

				default:
					break;
				}
			}else{
				switch (i) {
				case 0:
					fMond[change_preset_id*2-2]= 0;     //start
					fMond[change_preset_id*2-1]=0;     //stop
				break;
				case 1:
					fTues[change_preset_id*2-2]= 0;     //start
					fTues[change_preset_id*2-1]=0;     //stop
					break;
				case 2:
					fWedn[change_preset_id*2-2]=0;     //start
					fWedn[change_preset_id*2-1]=0;     //stop
					break;
				case 3:
					fThur[change_preset_id*2-2]=0;     //start
					fThur[change_preset_id*2-1]=0;     //stop
					break;
				case 4:
					fFrid[change_preset_id*2-2]= 0;     //start
					fFrid[change_preset_id*2-1]=0;     //stop
					break;
				case 5:
					fSatu[change_preset_id*2-2]=0;     //start
					fSatu[change_preset_id*2-1]=0;     //stop
					break;
				case 6:
					fSund[change_preset_id*2-2]=0;     //start
					fSund[change_preset_id*2-1]=0;     //stop
					break;

				default:
					break;
				}
			}
		
		}	
		shedule.setDrawShedules(fMond,fTues, fWedn, fThur, fFrid,fSatu,fSund);	
	}
				
	

	
public class TimerDialogClass extends Dialog implements
    android.view.View.OnClickListener {

  public Activity c;
  public Dialog d;
  public Button yes, no;
  NumberPicker hours, minutes;

  public TimerDialogClass(Activity a) {
    super(a);
    this.c = a; 
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    

		    setContentView(R.layout.time_dialog);
		    hours=(NumberPicker)findViewById(R.id.np_time2);
		    minutes=(NumberPicker)findViewById(R.id.np_time1);
		    hours.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		    minutes.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		    
		    hours.setMaxValue(23);
		    hours.setMinValue(0);

		    minutes.setMaxValue(59); 
		    minutes.setMinValue(0);
		    minutes.setOnValueChangedListener(new OnValueChangeListener() {
				@Override
				public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
					if(t_back)
					{
					time_back.setText(String.format("%02d", hours.getValue())+":"+String.format("%02d", minutes.getValue()));
					}else{
						
					if(t_on){
					time_on.setText(String.format("%02d", hours.getValue())+":"+String.format("%02d", minutes.getValue()));
					
					}else
						{
						 time_off.setText(String.format("%02d", hours.getValue())+":"+String.format("%02d", minutes.getValue()));
						}
					}}
			});
		    hours.setOnValueChangedListener(new OnValueChangeListener() {
				
				@Override
				public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
					if(t_back)
					{
						time_back.setText(String.format("%02d", hours.getValue())+":"+String.format("%02d", minutes.getValue()));
					}else{
				
					if(t_on){
					time_on.setText(String.format("%02d", hours.getValue())+":"+String.format("%02d", minutes.getValue()));
					}else
					{
					time_off.setText(String.format("%02d", hours.getValue())+":"+String.format("%02d", minutes.getValue()));
					}}
				}
			});

    
  }
  @Override
  public void onClick(View v) {switch (v.getId()) {}dismiss();}
}


///////__________________________________________________________________________________________________________________________________
				public class SensorDialogClass extends Dialog {
					public Activity c;
					public Dialog d;
				    RadioButton sensor_0,sensor_1,sensor_2,sensor_3;
					public SensorDialogClass(Activity a) {
					super(a);
					this.c = a; 
				}
						@Override
						protected void onCreate(Bundle savedInstanceState) {
						super.onCreate(savedInstanceState);
							requestWindowFeature(Window.FEATURE_NO_TITLE);
							setContentView(R.layout.sensor_dialog);
							sensor_0=(RadioButton)findViewById(R.id.sensor_0);
							sensor_1=(RadioButton)findViewById(R.id.sensor_1);
							sensor_2=(RadioButton)findViewById(R.id.sensor_2);
							sensor_3=(RadioButton)findViewById(R.id.sensor_3);
							
							sensor_0.setTypeface(Typeface.createFromAsset(c.getAssets(), "fonts/font.ttf"));
							sensor_1.setTypeface(Typeface.createFromAsset(c.getAssets(), "fonts/font.ttf"));
							sensor_2.setTypeface(Typeface.createFromAsset(c.getAssets(), "fonts/font.ttf"));
							sensor_3.setTypeface(Typeface.createFromAsset(c.getAssets(), "fonts/font.ttf"));
							
							sensor_0.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								@Override
								public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
									if(isChecked)
									sensors.setText("Не задано");
								}
							});
							sensor_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
														
									@Override
										public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
										if(isChecked)
										sensors.setText("Все датчики");
								}
								 });
							sensor_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								
								@Override
								public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
									if(isChecked)
									sensors.setText("Только датчик №1");
								}
							});
							sensor_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								
								@Override
								public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
									if(isChecked)
									sensors.setText("Только датчик №2");
								}
							});

						}
			
				}
///////__________________________________________________________________________________________________________________________________				


				
				
///////__________________________________________________________________________________________________________________________________				
				public class System_statusDialogClass extends Dialog implements
				android.view.View.OnClickListener {
					public Activity c;
					RadioButton status_0, status_1, status_2;
					public System_statusDialogClass(Activity a) {
					super(a);
					this.c = a; 
				}
						@Override
						protected void onCreate(Bundle savedInstanceState) {
						super.onCreate(savedInstanceState);
							requestWindowFeature(Window.FEATURE_NO_TITLE);
							setContentView(R.layout.s_status_dialog);
							status_0=(RadioButton)findViewById(R.id.status_0);
							status_1=(RadioButton)findViewById(R.id.status_1);
							status_2=(RadioButton)findViewById(R.id.status_2);
							status_0.setTypeface(Typeface.createFromAsset(c.getAssets(), "fonts/font.ttf"));
							status_1.setTypeface(Typeface.createFromAsset(c.getAssets(), "fonts/font.ttf"));
							status_2.setTypeface(Typeface.createFromAsset(c.getAssets(), "fonts/font.ttf"));
							
							status_0.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								
								@Override
								public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
									if(isChecked)
									status.setText("Не задано");
									
								}
							});
								status_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
										
										@Override
										public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
											if(isChecked)
											status.setText("Освещение включено");
										}
									});
								status_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								
								@Override
								public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
									if(isChecked)
									status.setText("Освещение выключено");
								}
							});
						}
				@Override
				public void onClick(View v) {
					switch (v.getId()) {}dismiss();}
				}
///////__________________________________________________________________________________________________________________________________
}
