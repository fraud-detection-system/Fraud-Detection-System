package com.stream.accounting;

public class Metric {
	private Long timetaken;
	private EntityEvent entityEvent;
	private int count = 1;
	
	public Metric() {}
	
	public Metric(Long timeTaken, EntityEvent entityEvent) {
		this.timetaken = timeTaken;
		this.entityEvent = entityEvent;
	}

	public Long getTimetaken() {
		return timetaken;
	}

	public void setTimetaken(Long timetaken) {
		this.timetaken = timetaken;
	}

	public EntityEvent getEntityEvent() {
		return entityEvent;
	}

	public void setEntityEvent(EntityEvent entityEvent) {
		this.entityEvent = entityEvent;
	}
	
	public String toString() {
		return "timeTaken(ms)="+timetaken;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
