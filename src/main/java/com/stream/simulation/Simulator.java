package com.stream.simulation;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simulator {
	 private final static Logger logger = LoggerFactory.getLogger(
			 Simulator.class.getName());
	
	 public State defineState(String stateName) {
		 State state = new State(stateName);
		 return state;
	 }
	 
	 
	 public Actor defineActor(String nameOfActor, Map<String, String> properties) {
			logger.info("defined");
			Actor actor = new Actor();
			return actor;
			
		}
	 
	public void startActors(String nameOfActor, int count) {
		logger.info("started");
		
	}
	public void sleepInMilliSecs(int ms) {
		
	}
	public void end() {
		
	}

}
