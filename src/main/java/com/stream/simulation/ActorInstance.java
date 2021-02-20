package com.stream.simulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorInstance implements Runnable{
	
	private Actor actor;
	private Simulator simulator;
	private State state;
	AccessEvent accessEvent;
	
	private final static Logger logger = LoggerFactory.getLogger(
			ActorInstance.class.getName());
	
	public ActorInstance() {
		
	}
	
	public ActorInstance(Actor actor, Simulator simulator) {
		this.actor = actor;
		this.simulator = simulator;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public void run() {
		if(accessEvent ==  null) {
			accessEvent = new AccessEvent();
			accessEvent = actor.ehanceAccessEvent(accessEvent);
		}
		if (state == null) {
			String startStateName = actor.getStartStateName();
			state = simulator.getState(startStateName);
		}

		if (state == null) {
			return;
		}
		
		accessEvent = state.ehanceAccessEvent(accessEvent);
		if(accessEvent.getResource().getAttribute("id") != null) {
			Publisher.fireAccessEvent(accessEvent);
			logger.info(state.getStateName()+ ": "+accessEvent.toString());
		}else {
			logger.info("Just a state change : "+accessEvent);
		}
		

		// go through state transitions and find the next transition
		State fromState = state;
		state = null;
		double coinToss = Math.random();
		
		for (StateTransition stateTransition : actor.getStateTransitions()) {
			if (stateTransition.fromState.equals(fromState.getStateName())) {
				coinToss -= stateTransition.getProbability();
				if (coinToss <= 0) {
					state = simulator.getState(stateTransition.getToState());
					simulator.schedule(this, stateTransition.getDelayInMillis());
					break;
				}
			}
		}
		
		//Done, nothing to do

	}

}
