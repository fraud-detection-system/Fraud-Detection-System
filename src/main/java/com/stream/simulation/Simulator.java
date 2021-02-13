package com.stream.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simulator {
	private final static Logger logger = LoggerFactory.getLogger(Simulator.class.getName());
	
	HashMap<String, Actor> actorRegistry = new HashMap<>();
	HashMap<String, State> stateRegistry = new HashMap<>();
	
	int  corePoolSize  =    5;
	int  maxPoolSize   =   10;
	long keepAliveTime = 5000;

	ExecutorService threadPoolExecutor =
	        new ThreadPoolExecutor(
	                corePoolSize,
	                maxPoolSize,
	                keepAliveTime,
	                TimeUnit.MILLISECONDS,
	                new LinkedBlockingQueue<Runnable>()
	                );

	public State defineState(String stateName) {
		State state = new State(stateName);
		stateRegistry.put(stateName, state);
		return state;
	}

	public Actor defineActor(String nameOfActor, Map<String, String> properties) {
		logger.info("defined");
		Actor actor = new Actor(nameOfActor, properties);
		actorRegistry.put(nameOfActor, actor);
		return actor;

	}

	public ActorInstance[] startActors(String nameOfActor, int count) {
		logger.info("started");
		Actor actor = actorRegistry.get(nameOfActor);
		if(actor == null) {
			return null;
		}
		ActorInstance [] actorInstances = new ActorInstance[count];
		for(int i=0; i<count; i++) {
			ActorInstance actorInstance = new ActorInstance(actor, this);
			threadPoolExecutor.submit(actorInstance);
			actorInstances[i] = actorInstance;
		}
		return actorInstances;
	}

	public void sleepInMilliSecs(int ms) throws InterruptedException {
		Thread.sleep(ms);

	}

	public void end() {
		// TODO: cleanup

	}

	public State getState(String startStateName) {
		return stateRegistry.get(startStateName);
		
	}

}
