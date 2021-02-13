package com.stream.simulation;

public class StateTransition {
	
	public StateTransition(String fromState,
	String toState,
	
	double probability) {
		setFromState(fromState);
		setToState(toState);
		setProbability(probability);
		
	}
	/**
	 * @return the fromState
	 */
	public String getFromState() {
		return fromState;
	}
	/**
	 * @param fromState the fromState to set
	 */
	public void setFromState(String fromState) {
		this.fromState = fromState;
	}
	/**
	 * @return the toState
	 */
	public String getToState() {
		return toState;
	}
	/**
	 * @param toState the toState to set
	 */
	public void setToState(String toState) {
		this.toState = toState;
	}
	/**
	 * @return the probability
	 */
	public double getProbability() {
		return probability;
	}
	/**
	 * @param probability the probability to set
	 */
	public void setProbability(double probability) {
		this.probability = probability;
	}
	String fromState;
	String toState;
	double probability;
	

}
