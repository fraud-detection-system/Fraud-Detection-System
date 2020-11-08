package com.stream.telecom;

public class TelecomUsageAlert {
	
	private String name;
	private String detail;
	private long startTime;
	
	
	public TelecomUsageAlert() {
		
	}
	
	public TelecomUsageAlert(long startTime, String name, String detail) {
		this.setStartTime(startTime);
		this.setDetail(detail);
		this.setName(name);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public String toString() {
		return "type="+this.getClass().getName()+", name="+this.getName()+", detail="+this.getDetail()+", startTime="+this.getStartTime();
	}
}
