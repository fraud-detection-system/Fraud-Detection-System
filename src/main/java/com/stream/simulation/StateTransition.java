package com.stream.simulation;

public class StateTransition {
	
	public StateTransition(
			String fromState,
			String toState,
			double probability,
			int delayInMillis) {
		setFromState(fromState);
		setToState(toState);
		setProbability(probability);
		setDelayInMillis(delayInMillis);
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
	public int getDelayInMillis() {
		return delayInMillis;
	}
	public void setDelayInMillis(int delayInMillis) {
		this.delayInMillis = delayInMillis;
	}
	String fromState;
	String toState;
	double probability;
	private int delayInMillis;
	

}
