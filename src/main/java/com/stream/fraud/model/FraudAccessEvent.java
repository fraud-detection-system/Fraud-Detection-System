package com.stream.fraud.model;

public class FraudAccessEvent extends AccessEvent {
	
	private String message;
	
	public FraudAccessEvent() {
		
	}
	
	public FraudAccessEvent(AccessEvent accessEvent, String message) {
		this.setResource(accessEvent.getResource());
		this.setSubject(accessEvent.getSubject());
		this.setAction(accessEvent.getAction());
		this.setEnvironment(accessEvent.getEnvironment());
		this.message = message;
	}

}
