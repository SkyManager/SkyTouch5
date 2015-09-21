package com.skyled.skytouch5;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.skyled.skytouch5.NetTask;
import com.skyled.skytouch5.thirdpart.TextViewN;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class RoomsActivity extends Activity {
	
	DBConnector mDBConnector;
	Context mContext;
	GridView mGridView;
	myGridAdapter mAdapter;
	NetTask netTask;
	TextViewN addBtn;
	TextViewN editBtn;
	TextViewN offBtn;
	
	int ADD_ACTIVITY = 0;
	int UPDATE_ACTIVITY = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        addBtn = (TextViewN)findViewById(R.id.addBtn);
        editBtn = (TextViewN)findViewById(R.id.editBtn);
        offBtn = (TextViewN)findViewById(R.id.offBtn);
        mContext = this;
        mDBConnector = new DBConnector (this);
        mGridView = (GridView)findViewById(R.id.gridView1);
        mAdapter = new myGridAdapter(mContext, mDBConnector.selectAll());
        mGridView.setAdapter(mAdapter);
        
        registerForContextMenu(mGridView);
        
        /*mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(RoomsActivity.this, "Selected Position: " + position, Toast.LENGTH_SHORT).show();
				//Intent i = new Intent(mContext, MainActivity.class);
				//RoomsActivity.this.startActivity(i);
			}
		});*/
        
        addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(mContext, AddActivity.class);
            	startActivityForResult (i, ADD_ACTIVITY);
            	updateList();
			}
		});
        
        editBtn.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("ResourceAsColor")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mAdapter.show)
				{
					editBtn.setBackgroundColor(-65536);
					mAdapter.show = false;
					mGridView.invalidateViews();
				}
				else
				{
					editBtn.setBackgroundColor(-11711155);
					mAdapter.show = true;
					mGridView.invalidateViews();
				}
				
			}
		});
        
        
    }
    /*
    @Override  
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
    	super.onCreateContextMenu(menu, v, menuInfo);  
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.context_menu, menu);
    }
    
    @Override
	public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	switch(item.getItemId()) {
    	case R.id.edit:
    		Intent i = new Intent(mContext, AddActivity.class);
    		MyData md = mDBConnector.select(info.id);
    		i.putExtra("MyData", md);
        	startActivityForResult (i, UPDATE_ACTIVITY);
        	updateList();
    		return true;
    	case R.id.delete:
    		mDBConnector.delete (info.id);
    		updateList();
    		return true;
    	default:
    		return super.onContextItemSelected(item);
    	}
    }*/
    
    public boolean deleteItem(long l)
    {
    	mDBConnector.delete (l);
		updateList();
		return true;
	}
    
    public boolean changeItem(long l)
    {
    	Intent i = new Intent(mContext, AddActivity.class);
		MyData md = mDBConnector.select(l);
		i.putExtra("MyData", md);
    	startActivityForResult (i, UPDATE_ACTIVITY);
    	updateList();
    	mGridView.invalidateViews();
		return true;
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
	        case R.id.add:
	        	Intent i = new Intent(mContext, AddActivity.class);
            	startActivityForResult (i, ADD_ACTIVITY);
            	updateList();
	            return true;
            case R.id.deleteAll:
            	mDBConnector.deleteAll();
            	updateList();
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
        if (resultCode == Activity.RESULT_OK) {
        	MyData md = (MyData) data.getExtras().getSerializable("MyData");
        	if (requestCode == UPDATE_ACTIVITY)
        		mDBConnector.update(md);
        	else
        		mDBConnector.insert(md);
        	updateList();
        }        
    }
    
    private void updateList () {
    	mAdapter.setArrayMyData(mDBConnector.selectAll());
    	mAdapter.notifyDataSetChanged();
    }
        
    class myGridAdapter extends BaseAdapter {
    	private LayoutInflater mLayoutInflater;
    	private ArrayList<MyData> arrayMyData;
    	Timer autoUpdate = new Timer();
    	LinearLayout mLayout;
    	LinearLayout mLayout2;
    	boolean show = true;
    	WebView webView;
    	Button mButtonChange;
    	Button mButtonDelete;
    	    	
    	public myGridAdapter (Context ctx, ArrayList<MyData> arr) {
    		mLayoutInflater = LayoutInflater.from(ctx);
    		setArrayMyData(arr);
    	}
    	
		public ArrayList<MyData> getArrayMyData() {
			return arrayMyData;
		}

		public void setArrayMyData(ArrayList<MyData> arrayMyData) {
			this.arrayMyData = arrayMyData;
		}
    	
    	public int getCount () {
    		return arrayMyData.size();
    	}
    		
    	public Object getItem (int position) {
    		
    		return position;
    	}
    		
    	public long getItemId (int position) {
    		MyData md = arrayMyData.get(position);
    		if (md != null) {
    			return md.getID();
    		}
    		return 0;
    	}
    	
    	public View getView(final int position, View convertView, ViewGroup parent) {
    		
    		if (convertView == null)
    			convertView = mLayoutInflater.inflate(R.layout.item, parent, false);

    		mLayout = (LinearLayout)convertView.findViewById(R.id.lay);
    		mLayout2 = (LinearLayout)convertView.findViewById(R.id.lay2);
    		mButtonChange = (Button)convertView.findViewById(R.id.buttonChange);
    		mButtonDelete = (Button)convertView.findViewById(R.id.buttonDelete);
    		
    		if(show)
    		{
    			mLayout.setVisibility(View.VISIBLE);
				mLayout2.setVisibility(View.GONE);
    		}
    		if(!show)
    		{
    			mLayout.setVisibility(View.GONE);
				mLayout2.setVisibility(View.VISIBLE);
    		}
    		
    		TextViewN vTitle = (TextViewN)convertView.findViewById(R.id.Title);
    		ToggleButton btnOnoff = (ToggleButton)convertView.findViewById(R.id.toggleOnOff);
    		ImageView vIcon = (ImageView)convertView.findViewById(R.id.Icon);
   			final MyData md = arrayMyData.get(position);
   			
   			//convertView.setTag(md.getID());/////////////////////////////////////////////////////////////////////
   			
   			webView = (WebView)convertView.findViewById(R.id.webView1);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
   			webView.setWebChromeClient(new WebChromeClient());
   			webView.setVerticalScrollBarEnabled(false);
   			webView.setHorizontalScrollBarEnabled(false);
   			if(!md.getIPCam().isEmpty())
   			{
   	   			timerSetup(webView, md.getIPCam());
   			}
   			else
   			{
   				webView.setVisibility(View.GONE);
   				vIcon.setVisibility(View.VISIBLE);
   				vIcon.setImageResource(md.getIcon());
   			}
   			
    		//---webview---
    		
   			//---webview---
			
   			
   			vTitle.setText(md.getTitle());
   			final String ip = md.getIP();
   			final int port = 1000;
   			
   		 /*webView.setOnTouchListener(new View.OnTouchListener() {
   		    @Override
   		    public boolean onTouch(View v, MotionEvent event) {
   		      return (event.getAction() == MotionEvent.ACTION_MOVE);
   		    }
   		  });*/
   			
   			webView.setOnTouchListener(new View.OnTouchListener() {
   	            public boolean onTouch(View v, MotionEvent event) {

   	                switch (event.getAction()) {
	   	                case MotionEvent.ACTION_DOWN: {
	   	                }
	   	                	break;
	   	                case MotionEvent.ACTION_MOVE: {
	   	                }
	   	                	break;
	   	                case MotionEvent.ACTION_CANCEL: {
	   	                }
	   	                	break;
	   	                case MotionEvent.ACTION_UP: {
	   	                	//Toast.makeText(RoomsActivity.this, "Выбрана комната: " + md.getTitle(), Toast.LENGTH_SHORT).show();
	   	                	//Intent i = new Intent(mContext, MainActivity.class);
	   	                	//startActivity (i);
	   	                }
	   	                	break;

   	                }

   	                return false;
   	            }
   	        });
   			
   			vIcon.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(RoomsActivity.this, "Выбрана комната: " + md.getTitle(), Toast.LENGTH_SHORT).show();
					Intent i = new Intent(mContext, MainActivity.class);
	                startActivity (i);
				}
			});
   			
   			btnOnoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if (isChecked)
						queryDevice("sys on", ip, port);
					else
						queryDevice("sys off", ip, port);
				}
			});
   			
   			mButtonDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					deleteItem(md.getID());
				}
			});
   			
   			mButtonChange.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					changeItem(md.getID());
				}
			});
   			
    		return convertView;
    	}
		private void timerSetup(final WebView webView, final String url) {
			// TODO Auto-generated method stub
			
			autoUpdate.schedule(new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					webView.loadUrl(url);
				}
			}, 0, 500);// (ms)
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
	          }
	      });
	        netTask.execute(param);
	    }

    } // end myAdapter
}