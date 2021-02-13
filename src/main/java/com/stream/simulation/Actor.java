package com.stream.simulation;

import java.util.Map;

public class Actor {
	
	/**
	 * @return the nameOfActor
	 */
	public String getNameOfActor() {
		return nameOfActor;
	}

	/**
	 * @param nameOfActor the nameOfActor to set
	 */
	public void setNameOfActor(String nameOfActor) {
		this.nameOfActor = nameOfActor;
	}

	/**
	 * @return the properties
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	private String nameOfActor;
	private Map<String, String> properties;
	private String startStateName;
	private StateTransition [] stateTransitions;
	
	public Actor() {
		
	}
	
	public Actor(String nameOfActor, Map<String, String> properties) {
		this.nameOfActor = nameOfActor;
		this.properties = properties;
	}

	public void stateTransition(String startStateName, StateTransition [] stateTransitions) {
		this.setStartStateName(startStateName);
		this.setStateTransitions(stateTransitions);
		
	}

	public StateTransition [] getStateTransitions() {
		return stateTransitions;
	}

	public void setStateTransitions(StateTransition [] stateTransitions) {
		this.stateTransitions = stateTransitions;
	}

	public String getStartStateName() {
		return startStateName;
	}

	public void setStartStateName(String startStateName) {
		this.startStateName = startStateName;
	}

}
