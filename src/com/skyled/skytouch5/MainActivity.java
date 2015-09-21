package com.skyled.skytouch5;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;

import com.skyled.skytouch5.thirdpart.ChanalsRedactor;
import com.skyled.skytouch5.thirdpart.ColorPicker;
import com.skyled.skytouch5.thirdpart.CustomDigitalClock;
import com.skyled.skytouch5.thirdpart.RightPanel;
import com.skyled.skytouch5.thirdpart.SeekArc;
import com.skyled.skytouch5.thirdpart.TextViewN;
import com.skyled.skytouch5.thirdpart.controlPanel;
import com.skyled.skytouch5.thirdpart.controlPanel.switchBrightListener;
import com.skyled.skytouch5.thirdpart.controlPanel.switchColorListener;
import com.skyled.skytouch5.thirdpart.controlPanel.switchSaturationListener;

public class MainActivity extends Activity {

	// context 
	private static Context mContext;
	
	//SQL references
	  static SQLiteDatabase sqdb ;
	  static SQLman sqlman;
	  
	//Custom Adapter Refernces
    static CAPresets capresets;
    static CAChanal cachanals;

    //Custom elements
    static	SeekArc brightness, saturation;
    static ColorPicker colorPick;
    net.simonvt.numberpicker.NumberPicker np1, np2, np3;
	static ChanalsRedactor template;
	static controlPanel cPanel;
	static RightPanel rightPanel;
    CustomDigitalClock digitalClock;
    
    //Default widget
	static ListView presetList, chanalList;
	static SlidingDrawer slider;
	static TextViewN  headStatus,  effectBTN, pressetBTN, mainBTN, MainBack;
	static ImageView  ON_OFF;
	RelativeLayout  warper;
	Button  handle;
	LinearLayout header;
	protected static ScrollView templateContainer;

	  
	//Animation 	
	static LayoutAnimationController  animFadein, chanalIn;   
	  
	// Variables
	static boolean animation_fadeIn=true, showlist=true;
	static int active_chanal=0,active_chanalInPreset=0,active_preset=0,bufID,tempWidth, chanalsNumber;
	Thread net;        

	//Network 
	private static Socket socket;      
	private static final int SERVERPORT = 1000;
//	private static final String SERVER_IP = "192.168.1.190"; 
	private static final String SERVER_IP = "192.168.1.195";
	
	 
	
	//Controllers
	RightPanelCintroller rightPanelCintroller;
	SaturationController saturationController;
	ColorController colorController;
	BrightnesController brightnesController;
	NumberPickerController  numberPickerController;
	TemplateController  templateController;
	HeaderElementController headerElementController;
	
	
	Typeface eur;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
	//new Thread(new ClientThread()).start();

		net=new Thread(new ClientThread());
		net.start();
		

		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		 getActionBar().hide();
		 
		 DisplayMetrics metrics = getResources().getDisplayMetrics();
		 int densityDpi = (int)(metrics.density * 160f);
		 if (densityDpi==160){  }else{setContentView(R.layout.activity_main_213); }
		 getWindow().setSoftInputMode(
		 WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		 getWindow().getDecorView()
         .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			 animFadein = AnimationUtils.loadLayoutAnimation(this, R.anim.lay_fadeout);
			 chanalIn = AnimationUtils.loadLayoutAnimation(this, R.anim.chanal_lay_fade); 
			 mContext=this;    
			     
		DataB sqh = new DataB(this);
	    sqdb = sqh.getWritableDatabase();   
	    
	    chanalsNumber=15;

	    presetList=(ListView)findViewById(R.id.presetList);
	    presetList.setDivider(null);
	    presetList.setDividerHeight(0);
	    presetList.setVisibility(View.INVISIBLE);
	    presetList.setLayoutAnimation(chanalIn);
	    
	    chanalList=(ListView)findViewById(R.id.chanalstList);
	    chanalList.setDivider(null);
	    chanalList.setDividerHeight(0);
	    chanalList.setVisibility(View.INVISIBLE);
		
		cPanel=(controlPanel)findViewById(R.id.cPanel);
		templateContainer=(ScrollView)findViewById(R.id.templateContainer);
		
		np1=(net.simonvt.numberpicker.NumberPicker)findViewById(R.id.np1);
		np2=(net.simonvt.numberpicker.NumberPicker)findViewById(R.id.np2);
		np3=(net.simonvt.numberpicker.NumberPicker)findViewById(R.id.np3);
	
        headStatus=(TextViewN)findViewById(R.id.headStatus);
       
        header=(LinearLayout)findViewById(R.id.linearLayout1);
        header.setVisibility(View.INVISIBLE);
    
        digitalClock = (CustomDigitalClock) findViewById(R.id.Digitalclock);
        eur= Typeface.createFromAsset(this.getAssets(), "fonts/font.ttf");
        digitalClock.setTypeface(eur);
	    capresets = new CAPresets();
	    cachanals=new CAChanal();
	    handle=(Button)findViewById(R.id.handle);
	    handle.setTypeface(eur);
	    
	    slider=(SlidingDrawer)findViewById(R.id.slidingDrawer1);
	    slider.setVisibility(View.INVISIBLE);

	    
	    warper=(RelativeLayout)findViewById(R.id.wraper);
	    rightPanel=(RightPanel)findViewById(R.id.rightPanel1);
	
	    MainBack=(TextViewN)findViewById(R.id.MainBack);
	    brightness = (SeekArc) findViewById(R.id.seekArc1);
	    saturation = (SeekArc) findViewById(R.id.seekArc2);
	    colorPick=(ColorPicker)findViewById(R.id.colorPicker1);
	    colorPick.setVisibility(View.INVISIBLE);
		saturation.setVisibility(View.INVISIBLE);
		saturation.setGradient(colorPick.getColor());
		
		
		template=(ChanalsRedactor)findViewById(R.id.preset_lay_temp);
		template.setVisibility(View.GONE);

		mainBTN=(TextViewN)findViewById(R.id.addBtn);
		pressetBTN=(TextViewN)findViewById(R.id.editBtn);
		effectBTN=(TextViewN)findViewById(R.id.offBtn);
		pressetBTN.setBackgroundColor(Color.RED);
		


	    
	     rightPanelCintroller = new RightPanelCintroller(this);
	     saturationController = new SaturationController(this);
	     colorController= new ColorController(this);
	     brightnesController=new BrightnesController(this);
	     numberPickerController= new NumberPickerController(this);
	     templateController=new TemplateController(this);
	     headerElementController=new HeaderElementController(this);
	     sqlman=new SQLman(this);
	     
	     
		 rightPanelCintroller.activateRightPanel();
		 saturationController.activateSaturationController();
		 colorController.activateColorController();
		 brightnesController.activateBrightnesController(); 
         numberPickerController.activateNumberPickerController();
         templateController.activateTemplateController(); 
         headerElementController.activateHeaderElementController();
         
	      
		cPanel.setBrihtnesActivationListener(new switchBrightListener() {
			
			@Override
			public void onBrightnes() {
				if(cPanel.isActive()) 
				{
					colorPick.setVisibility(View.INVISIBLE);
					saturation.setVisibility(View.INVISIBLE);
					brightness.setVisibility(View.VISIBLE);
					brightness.demo();
					//slider.setVisibility(View.INVISIBLE);
					headStatus.setText("�������");
				}
			}
		});  
		
		cPanel.setColorActivationListener(new switchColorListener() {
			
			@Override
			public void onColor() {
				colorPick.demo();
				colorPick.setVisibility(View.VISIBLE);
				saturation.setVisibility(View.INVISIBLE);
				brightness.setVisibility(View.INVISIBLE);
				//slider.setVisibility(View.VISIBLE);
				headStatus.setText("����");
				
			}
		});
		
		cPanel.setSaturationActivationListener(new switchSaturationListener() {
			
			@Override
			public void onSaturation() {
				colorPick.setVisibility(View.INVISIBLE);
				saturation.setVisibility(View.VISIBLE);
				saturation.demo();
				brightness.setVisibility(View.INVISIBLE);
				headStatus.setText("��������");
			}
		});
		
		sqlman.getPreset();
		sqlman.getChanals();
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		if(net.isAlive())
		{
			Log.e("Thread alive", "ok");
		}else
		{
			Log.e("Thread alive", "droped");
			net=new Thread(new ClientThread());
			net.start();
			if(net.isAlive())Log.e("Thread alive", "ok");
		}
	}
	
	public static void timeRedactor(String id)
	{
		 Intent intent = new Intent(mContext, PresetRedactor.class);
		 intent.putExtra("presetID", id);
		 mContext.startActivity(intent);
	}
	

	
//  		@@@         
	public static void rChanelsRedactor(String id, String name,String status, String prbrightness, String timing, int[] chanals, String [] chbright, int initLocation)
	{
		templateContainer.setVisibility(View.VISIBLE);
		// get Template redactor width
	//	bufID=Integer.parseInt(id);
		
//		template.setPresetName("TEST");
//		boolean presetStatus; if(status.equals("on"))presetStatus=true;else presetStatus=false;
//		template.setOn_Off_Preset_indicator(presetStatus);
//
//		mainBTN.getBackground().setAlpha(128);
//		mainBTN.setTextColor(Color.argb(50, 255, 255, 255));
//		effectBTN.getBackground().setAlpha(128);
//		effectBTN.setTextColor(Color.argb(50, 255, 255, 255));
//
//		
//
//		presetList.getChildAt(bufID-1).setVisibility(View.GONE);
     	template.setVisibility(View.VISIBLE);
    	presetList.setVisibility(View.INVISIBLE);
//		presetList.setLayoutAnimation(animFadein); 
//		presetList.setLayoutAnimationListener(new AnimationListener() {
//			@Override
//			public void onAnimationStart(Animation animation) {	
//			}
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//			}  
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				if(!animation_fadeIn){
//					presetList.setVisibility(View.VISIBLE);
//					animation_fadeIn=true;
//				}else  
//				{
//					presetList.setVisibility(View.INVISIBLE);
//					animation_fadeIn=false;
//				}
//			}
//		});    

		brightness.setProgress(Integer.parseInt(prbrightness));	
		showlist=false;
		

		template.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

		    @Override
		    public void onGlobalLayout() {
		    	tempWidth=template.getWidth();
		    	Log.e("tempWidth",tempWidth+"");
		    }
		});

		template.getLayoutParams().height=template.getRedactorHeight(640);
	}

///  ______________________________________________________________________________________________________________
//	public static void presetChanges(String id, String name, String status, String bright, int[] colors, String[] chbrightness)
//	{
//
//		String where = "_id=" + id + " AND P_filename='"+ name+"'";
//	 	ContentValues cv = new ContentValues();
//        cv.put(DataB.P_STATUS, "off");
//        sqdb.update(DataB.T_PRESETS, cv, null, null);
//        cv.put(DataB.P_STATUS, status);
//        sqdb.update(DataB.T_PRESETS, cv, where, null);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            	sqlman.getPreset();  
//            }
//          }, 400);
//	}
	
	
	
	public static void setColor(int color)
	{		  
		 int   	vr = (color >> 16) & 0xFF;
		 int	vg = (color >> 8) & 0xFF;
		 int 	vb = color & 0xFF;
		 
        try {	
     String str = "pwm "+String.format("%02d", active_chanal*10)+String.format("%03d", vr)+String.format("%03d", vg)+String.format("%03d", vb);
    
     Log.e("SSS", str);
     PrintWriter out = new PrintWriter(new BufferedWriter(
   new OutputStreamWriter(socket.getOutputStream())),
         true);
 out.println(str);
} catch (UnknownHostException e) {
 e.printStackTrace();
} catch (IOException e) {
 e.printStackTrace();
} catch (Exception e) {
 e.printStackTrace();
}
}
	
	
	public static void setChanalBright(int brightValue)
	{
			float brv=brightValue*2.55f;
			int mode=active_chanal;
			if(active_chanal==0)
			{
				mode=6; 
			}
        try {	
     String str ="brg "+String.format("%02d", mode*10)+(int)brv;
     Log.e("brg", str);
     
        	 	PrintWriter out = new PrintWriter(new BufferedWriter(
   new OutputStreamWriter(socket.getOutputStream())),
         true);
 out.println(str);
} catch (UnknownHostException e) {
 e.printStackTrace();
} catch (IOException e) {
 e.printStackTrace();
} catch (Exception e) {
 e.printStackTrace();
}
}
	
public String getSystemTime()
	{
	String time = "";
        try {	
            String str = "07555444";
 	       PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
 	       out.println(str);
 	    ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream(1024);
 	    byte[] buffer = new byte[1024];
 	    socket.getInputStream().reset();
 	    int bytesRead;
	 	   while ((bytesRead = socket.getInputStream().read(buffer)) >0){
	 		  Log.e("start", "get Input");
	 	        	byteArrayOutputStream.write(buffer, 0, bytesRead);
	 	            time += byteArrayOutputStream.toString("UTF-8");
	 	            Log.e("out", time);
	 	     }
	 	  byteArrayOutputStream.close();
 	           
		} catch (UnknownHostException e) {
		 e.printStackTrace();
		} catch (IOException e) {
		 e.printStackTrace();
		} catch (Exception e) {
		 e.printStackTrace();
		}
        
        return time;
}
	
	
	public String ping(String url) {
	    String str = "";
	    try {
	        Process process = Runtime.getRuntime().exec(
	                "/system/bin/ping -c 6 " + url);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                process.getInputStream()));
	        int i;
	        char[] buffer = new char[4096];
	        StringBuffer output = new StringBuffer();
	        while ((i = reader.read(buffer)) > 0)
	            output.append(buffer, 0, i);
	        reader.close();

	        // body.append(output.toString()+"\n");
	        str = output.toString();
	        // Log.d(TAG, str);
	    } catch (IOException e) {
	        // body.append("Error\n");
	        e.printStackTrace();
	    }
	    return str;
	}
	
	
	static  class ClientThread implements Runnable {
	    
        @Override

        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);

            } catch (UnknownHostException e1) {

                e1.printStackTrace();

            } catch (IOException e1) {

                e1.printStackTrace();
            }
        }
    }
	
//	public class MyClientTask extends AsyncTask<Void, Void, Void> {
//		  
//		  String dstAddress;
//		  int dstPort;
//		  String response = "";
//		  
//		  MyClientTask(String addr, int port){
//		   dstAddress = addr;
//		   dstPort = port;
//		  }
//
//		  @Override
//		  protected Void doInBackground(Void... arg0) {
//		   
//		   Socket socket = null;
//		   
//		   try {
//		    socket = new Socket(dstAddress, dstPort);
//		    
//		    ByteArrayOutputStream byteArrayOutputStream = 
//		                  new ByteArrayOutputStream(1024);
//		    byte[] buffer = new byte[1024];
//		    
//		    int bytesRead;
//		    InputStream inputStream = socket.getInputStream();
//		             while ((bytesRead = inputStream.read(buffer)) != -1){
//		                 byteArrayOutputStream.write(buffer, 0, bytesRead);
//		                 response += byteArrayOutputStream.toString("UTF-8");
//		             }
//
//		   } catch (UnknownHostException e) {
//		    e.printStackTrace();
//		    response = "UnknownHostException: " + e.toString();
//		   } catch (IOException e) {
//		    e.printStackTrace();
//		    response = "IOException: " + e.toString();
//		   }finally{
//		    if(socket != null){
//		     try {
//		      socket.close();
//		     } catch (IOException e) {
//		 
//		      e.printStackTrace();
//		     }
//		    }
//		   }
//		   return null;
//		  }
//
//		  @Override
//		  protected void onPostExecute(Void result) {
//		   Log.e("response", response);
//		   super.onPostExecute(result);
//		  }
//		  
//		 }


}
