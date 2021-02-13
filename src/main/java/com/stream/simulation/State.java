package com.stream.simulation;

public class State {
	private String stateName;
	
	public State(String stateName) {
		this.stateName = stateName;
	}

	public State addResource(String key, String value) {
		return this;

	}

	public State addAction(String key, String value) {
		return this;
	}

	public State addSubject(String key, String value) {
		return this;
	}

	public State addEnvironment(String key, String value) {
		return this;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
