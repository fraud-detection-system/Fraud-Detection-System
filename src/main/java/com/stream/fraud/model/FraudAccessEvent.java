package com.stream.fraud.model;

import java.util.HashMap;

public class FraudAccessEvent extends AccessEvent {
	
	public FraudAccessEvent() {
		
	}
	
	@SuppressWarnings("unchecked")
	public FraudAccessEvent(AccessEvent accessEvent) {
		this.setEventId(accessEvent.getEventId());
		//Deep copy
		this.getResource().setAttributes((HashMap<String, Object>) accessEvent.getResource().getAttributes().clone());
		this.getSubject().setAttributes((HashMap<String, Object>) accessEvent.getSubject().getAttributes().clone());
		this.getAction().setAttributes((HashMap<String, Object>) accessEvent.getAction().getAttributes().clone());
		this.getEnvironment().setAttributes((HashMap<String, Object>) accessEvent.getEnvironment().getAttributes().clone());
	}

}
