package com.stream.simulation;

import com.stream.fraud.model.Action;
import com.stream.fraud.model.Environment;
import com.stream.fraud.model.Event;
import com.stream.fraud.model.Resource;
import com.stream.fraud.model.Subject;

/**
 * Represents an access of Resource by a Subject
 * 
 * @author bdutt
 *
 */

public class AccessEvent extends Event{
	private Subject subject = new Subject();
	private Resource resource = new Resource();
	private Action action = new Action();
	private Environment environment = new Environment();
	
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	public String toString() {
		return "resource="+this.resource.getAttributes()+", action="+action.getAttributes()+", subject="+subject.getAttributes()+", environment="+environment.getAttributes();
	}

}
