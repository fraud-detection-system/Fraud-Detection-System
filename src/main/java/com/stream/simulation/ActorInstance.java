package com.stream.simulation;

import com.stream.fraud.SimulatorEventGenerator;

public class ActorInstance implements Runnable{
	
	private Actor actor;
	private Simulator simulator;
	private State state;
	
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
		if (state == null) {
			String startStateName = actor.getStartStateName();
			state = simulator.getState(startStateName);
		}

		if (state == null) {
			return;
		}
		AccessEvent accessEvent = state.getAccessEvent();
		accessEvent = actor.ehanceAccessEvent(accessEvent);
		SimulatorEventGenerator.fireAccessEvent(accessEvent);

		// go through state transitions and find the next transition
		State fromState = state;
		state = null;
		for (StateTransition stateTransition : actor.getStateTransitions()) {
			if (stateTransition.fromState.equals(fromState.getStateName())) {
				if (Math.random() <= stateTransition.getProbability()) {
					state = simulator.getState(stateTransition.getToState());
					simulator.schedule(this, stateTransition.getDelayInMillis());
					break;
				}
			}
		}
		
		//Done, nothing to do

	}

}
