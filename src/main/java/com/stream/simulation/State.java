package com.stream.simulation;

public class State {
	private String stateName;
	private AccessEvent accessEvent = new AccessEvent();
	
	public State(String stateName) {
		this.stateName = stateName;
	}
	
	public State() {
		
	}

	public State addResource(String key, String value) {
		accessEvent.getResource().setAttribute(key, value);
		return this;

	}

	public State addAction(String key, String value) {
		accessEvent.getAction().setAttribute(key, value);
		return this;
	}

	public State addSubject(String key, String value) {
		accessEvent.getSubject().setAttribute(key, value);
		return this;
	}

	public State addEnvironment(String key, String value) {
		accessEvent.getEnvironment().setAttribute(key, value);
		return this;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public AccessEvent getAccessEvent() {
		return accessEvent;
	}

}
