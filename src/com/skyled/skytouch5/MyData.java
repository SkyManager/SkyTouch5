package com.skyled.skytouch5;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyData implements Serializable{
	
	private long id;
	private String ip;
	private String ipCam;
	private String title;
	private int icon;
	
	public MyData (long id, String ip, String ipCam, String title, int icon) {
		this.id = id;
		this.ip = ip;
		this.ipCam = ipCam;
		this.title = title;
		this.icon = icon;
	}
	
	public long getID () {return id;}
	public String getIP () {return ip;}
	public String getIPCam () {return ipCam;}
	public String getTitle () {return title;}
	public int getIcon () {return icon;}
}