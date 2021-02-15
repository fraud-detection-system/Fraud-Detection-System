package com.stream.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.stream.fraud.model.Resource;
import com.stream.fraud.model.Subject;

public class Simulator {
	private final static Logger logger = LoggerFactory.getLogger(Simulator.class.getName());
	
	HashMap<String, Actor> actorRegistry = new HashMap<>();
	HashMap<String, State> stateRegistry = new HashMap<>();
	
	ScheduledExecutorService scheduledExecutorService =
	        Executors.newScheduledThreadPool(5);

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
	
	public Resource[] definePool(int count, Resource resourceTemplate) {
		Resource [] resourcePool = new Resource[count];
		ExpressionParser parser = new SpelExpressionParser();  
		for(int i=0; i<count; i++) {
			Resource resource = new Resource();
			for(Entry<String, Object> entry: resourceTemplate.getAttributes().entrySet()) {
				resource.setAttribute(entry.getKey(), parser.parseExpression(entry.getValue().toString()).getValue());
			}
			resourcePool[i]=resource;
		}
		return resourcePool;

	}
	
	public Subject[] definePool(int count, Subject subjectTemplate) {
		Subject [] subjectPool = new Subject[count];
		ExpressionParser parser = new SpelExpressionParser();  
		for(int i=0; i<count; i++) {
			Subject subject = new Subject();
			for(Entry<String, Object> entry: subjectTemplate.getAttributes().entrySet()) {
				subject.setAttribute(entry.getKey(), parser.parseExpression(entry.getValue().toString()).getValue());
			}
			subjectPool[i]=subject;
		}
		return subjectPool;

	}
	
	public Object[] pair(Resource [] resourcePool, Subject[] subjectPool) {
		if(resourcePool.length != subjectPool.length) {
			logger.info("Incompatible lengths of pools to pair");
			return null;
		}
	
		Object [] pairPool = new Object[resourcePool.length];
		  
		for(int i=0; i<pairPool.length; i++) {
			pairPool[i] = new Object[] {
					resourcePool[i],
					subjectPool[i]
			};
		}
		return pairPool;

	}

	public ActorInstance[] startActors(String nameOfActor, Object [] pool, int count) {
		logger.info("started");
		Actor actor = actorRegistry.get(nameOfActor);
		if(actor == null) {
			return null;
		}
		ActorInstance [] actorInstances = new ActorInstance[count];
		
		for(int i=0; i<count; i++) {
			ActorInstance actorInstance = new ActorInstance(actor, this);
			if(pool != null && pool.length > count) {
				
				Object obj = pool[i];
				if(obj.getClass().isArray()) {
					Object [] objArray = (Object[])obj;
					if(objArray.length == 2) {
						actorInstance.getActor().setResource(objArray[0]);
						actorInstance.getActor().setSubject(objArray[1]);
					}
				}
			}
			
			scheduledExecutorService.schedule(actorInstance, 0, TimeUnit.MILLISECONDS);
			actorInstances[i] = actorInstance;
		}
		return actorInstances;
	}

	public void sleepInMilliSecs(int ms) throws InterruptedException {
		Thread.sleep(ms);

	}
	
	public void schedule(Runnable task, int delayInMillis) {
		scheduledExecutorService.schedule(task, delayInMillis, TimeUnit.MILLISECONDS);
	}

	public void end() {
		// TODO: cleanup
		scheduledExecutorService.shutdownNow();

	}

	public State getState(String startStateName) {
		return stateRegistry.get(startStateName);
		
	}

}
