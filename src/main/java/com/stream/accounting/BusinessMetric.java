package com.stream.accounting;

public class BusinessMetric {
	
	private String name;
	private String value;
	private long longValue;
	
	public BusinessMetric() {
		
	}
	
	public BusinessMetric(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public BusinessMetric(String name, long longValue) {
		this.name = name;
		this.setLongValue(longValue);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

}
