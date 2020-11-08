package com.stream.accounting;

public class Alert {
	
	private String name;
	private String detail;
	private long startTime;
	private EntityEvent entityEvent ;
	
	
	public Alert() {
		
	}
	
	public Alert(long startTime, EntityEvent entityEvent, String name, String detail) {
		this.setStartTime(startTime);
		this.setEntityEvent(entityEvent);
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

	public EntityEvent getEntityEvent() {
		return entityEvent;
	}

	public void setEntityEvent(EntityEvent entityEvent) {
		this.entityEvent = entityEvent;
	}
	
	

}
