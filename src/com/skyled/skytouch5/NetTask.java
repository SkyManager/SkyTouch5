package com.skyled.skytouch5;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.skyled.skytouch5.RoomsActivity.myGridAdapter;

import android.content.Context;
import android.os.AsyncTask;

public class NetTask extends AsyncTask<String, Integer, String> {
    String dstAddress;
    int dstPort;

    private Context context;
    public interface NetTaskResult {
        void onPreExecuteConcluded();
        void onPostExecuteConcluded(String result);
    }
    private NetTaskResult netTaskResult;
    final public void setNetListener(NetTaskResult netTaskResultListener) {
        netTaskResult = netTaskResultListener;
    }

    public NetTask(String address, int port)
    {
        //this.context = myGridAdapter;
        this.dstAddress=address;
        this.dstPort=port;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (netTaskResult != null)
            netTaskResult.onPreExecuteConcluded();
    }



    @Override
    protected String doInBackground(String... params) {
    	String data=null;
		try {
         Socket   socket = new Socket(dstAddress, dstPort);
         BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
         PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            out.println(params[0]);           
            data = "";
            int s = inputStream.read();
            if(s==-1){
            	 out.flush();
            	 out.close();
            	 inputStream.close();
            	 socket.close();
            	 return null; 
            }else{
               
            data += ""+(char)s;
            int len = inputStream.available();
            if(len > 0) {
                byte[] byteData = new byte[len];
                inputStream.read(byteData);
                data += new String(byteData);
            }
            out.flush();
            out.close();
            inputStream.close();
            socket.close();
           }
         
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (netTaskResult != null)
            netTaskResult.onPostExecuteConcluded(result);
    
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values); 

    }
    @Override
    protected void onCancelled() {
    	super.onCancelled();
    }



}
