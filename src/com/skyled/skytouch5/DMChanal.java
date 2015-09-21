package com.skyled.skytouch5;


public class DMChanal {
	private String name;
	private int status;/// exception if rgb stat=0 else if mono stat=1 if mono2 stat= 2 else if mono3 stat=3
	private int brightnes;
	private int color;
	private int id;
	private int mode;
	private int parent;
	private int saturation;
	
	public DMChanal(int id,String name, int status, int mode, int parent, int brightnes, int color, int saturation)
	{
		this.name=name;
		this.id=id;
		this.status=status;
		this.brightnes=brightnes;
		this.color=color;
		this.mode=mode;
		this.parent=parent;
		this.saturation=saturation;
	}
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public int getSaturation()
	{
		return saturation;
	}
	public void setSaturation(int saturation)
	{
		this.saturation=saturation;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status=status;
	}
	public int getBrightnes()
	{
		return brightnes;
	}
	public void setBrightness(int brightnes)
	{
		this.brightnes=brightnes;
	}
	public int getColor()
	{
		return color;
	}
	public void setColor(int color)
	{
		this.color=color;
	}
	
	public int getMode()
	{
		return mode;
	}
	public void setMode(int mode)
	{
		this.mode=mode;
	}
	public int getParent()
	{
		return parent;
	}
	public void setParent(int parent)
	{
		this.parent=parent;
	}
}