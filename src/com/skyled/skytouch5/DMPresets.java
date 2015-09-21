package com.skyled.skytouch5;

public class DMPresets {
	private String pid;
	private String name;
	private String status;
	private String timing;
	private String brightness;
	private String chanal1;
	private String chanal2;
	private String chanal3;
	private String chanal4;
	private String chanal5;
	private String chanalbr1;
	private String chanalbr2;
	private String chanalbr3;
	private String chanalbr4;
	private String chanalbr5;
	private String changeMode;

	public DMPresets(String pid, String name, String timing, String brightness, String status,
			String chanal1, String chanal2, String chanal3 , String chanal4 , String chanal5, 
			String chanalbr1, String chanalbr2, String chanalbr3 , String chanalbr4 , String chanalbr5) {
		this.pid = pid;
		this.status=status;
		this.name = name;
		this.timing = timing;
		this.brightness=brightness;
		this.chanal1 = chanal1;
		this.chanal2 = chanal2;
		this.chanal3 = chanal3;
		this.chanal4 = chanal4;
		this.chanal5 = chanal5;
		this.chanalbr1 = chanalbr1;
		this.chanalbr2 = chanalbr2;
		this.chanalbr3 = chanalbr3;
		this.chanalbr4 = chanalbr4;
		this.chanalbr5 = chanalbr5;
		this.changeMode=changeMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}
	
	public String getBrightnes() {
		return brightness;
	}

	public void setBrightnes(String brightness) {
		this.brightness = brightness;
	}
	//______________________model color
	public String getChanal1() {
		return chanal1;
	}

	public void setChanal1(String chanal1) {
		this.chanal1 = chanal1;
	}
	
	
	public String getChanal2() {
		return chanal2;
	}

	public void setChanal2(String chanal2) {
		this.chanal2 = chanal2;
	}
	
	
	public String getChanal3() {
		return chanal3;
	}

	public void setChanal3(String chanal3) {
		this.chanal3 = chanal3;
	}
	
	
	
	public String getChanal4() {
		return chanal4;
	}

	public void setChanal4(String chanal4) {
		this.chanal4 = chanal4;
	}
	
	
	public String getChanal5() {
		return chanal5;
	}

	public void setChanal5(String chanal5) {
		this.chanal5 = chanal5;
	}

	
	//______________________model brightness
	public String getChanalbr1() {
		return chanalbr1;
	}

	public void setChanalbr1(String chanalbr1) {
		this.chanalbr1 = chanalbr1;
	}
	
	public String getChanalbr2() {
		return chanalbr2;
	}
	public void setChanalbr2(String chanalbr2) {
		this.chanalbr2 = chanalbr2;
	}
	
	public String getChanalbr3() {
		return chanalbr3;
	}
	public void setChanalbr3(String chanalbr3) {
		this.chanalbr3 = chanalbr3;
	}
	
	public String getChanalbr4() {
		return chanalbr4;
	}

	public void setChanalbr4(String chanalbr4) {
		this.chanalbr4 = chanalbr4;
	}
	
	
	public String getChanalbr5() {
		return chanalbr5;
	}

	public void setChanalbr5(String chanalbr5) {
		this.chanalbr5 = chanalbr5;
	}

	public String getChangeMode() {
		return changeMode;
	}

	public void setChangeMode(String changeMode) {
		this.changeMode = changeMode;
	}

}