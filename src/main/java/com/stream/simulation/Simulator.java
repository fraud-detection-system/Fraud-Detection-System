package com.stream.simulation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.AttributeContainer;
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

	public Actor defineActor(String nameOfActor) {
		logger.info("defined");
		Actor actor = new Actor(nameOfActor);
		actorRegistry.put(nameOfActor, actor);
		return actor;

	}
	
	private static Object eval(Object value, AttributeContainer attributeContainer) {
		try {
			ExpressionParser parser = new SpelExpressionParser();
			StandardEvaluationContext context=new StandardEvaluationContext(attributeContainer);
			return parser.parseExpression(value.toString()).getValue(context);
		}catch(Exception e) {
			//TODO: log error
			//e.printStackTrace();
			return value;
		}
	}
	
	public Resource[] definePool(int count, Resource resourceTemplate) {
		Resource [] resourcePool = new Resource[count]; 
		for(int i=0; i<count; i++) {
			Resource resource = new Resource();
			for(Entry<String, Object> entry: resourceTemplate.getAttributes().entrySet()) {
				resource.setAttribute(entry.getKey(), eval(entry.getValue().toString(), resourceTemplate));
			}
			resourcePool[i]=resource;
		}
		return resourcePool;

	}
	
	public Subject[] definePool(int count, Subject subjectTemplate) {
		Subject [] subjectPool = new Subject[count]; 
		for(int i=0; i<count; i++) {
			Subject subject = new Subject();
			for(Entry<String, Object> entry: subjectTemplate.getAttributes().entrySet()) {
				subject.setAttribute(entry.getKey(), eval(entry.getValue().toString(), subjectTemplate));
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

	public ActorInstance[] startActors(String nameOfActor, Object [] pool, double percentActive, double percentConcurrent) {
		Actor actor = actorRegistry.get(nameOfActor);
		if(actor == null) {
			return null;
		}
		
		if(percentActive> 100 || percentConcurrent > 100) {
			logger.info("Wrong percentages given, they should be less than 100. Given: active="+percentActive+", concurrent="+percentConcurrent);
			return null;
		}
		
		int count = (int) (pool.length*percentActive*percentConcurrent/(100.0*100.0));
		ActorInstance [] actorInstances = new ActorInstance[count];
		
		for(int i=0; i<count; i++) {
			ActorInstance actorInstance = new ActorInstance(actor, this);
			if(pool != null && pool.length > i) {
				
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
		logger.info("started "+count+" actor instances");
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
	
	public void pauseAndPrompt(String msg) throws IOException {
		logger.info(msg+": press enter to continue");
		System.in.read();
		//System.console().readLine();
	}

}
