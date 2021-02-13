package com.stream.simulation;

import com.stream.fraud.SimulatorEventGenerator;

public class ActorInstance implements Runnable{
	
	private Actor actor;
	private Simulator simulator;
	
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
		String startStateName = actor.getStartStateName();
		State state = simulator.getState(startStateName);
		do {
			if (state == null) {
				continue;
			}
			AccessEvent accessEvent = state.getAccessEvent();
			SimulatorEventGenerator.fireAccessEvent(accessEvent);

			//go through state transitions and find the next transition
			State fromState = state;
			state = null;
			for (StateTransition stateTransition : actor.getStateTransitions()) {
				if (stateTransition.fromState.equals(fromState.getStateName())) {
					if (Math.random() <= stateTransition.getProbability()) {
						state = simulator.getState(stateTransition.getToState());
						break;
					}
				}
			}
		}while(state!=null);
		//TODO: see how to add sleep
	}

}
