package com.stream.simulation;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class State {
	private String stateName;
	private AccessEvent accessEvent = new AccessEvent();
	
	public State(String stateName) {
		this.stateName = stateName;
	}
	
	public State() {
		
	}
	
	private static Object getValue(String value) {
		try {
			ExpressionParser parser = new SpelExpressionParser();  
			return parser.parseExpression(value).getValue();
		}catch(Exception e) {
			//TODO: log error
			return value;
		}
	}

	public State addResource(String key, String value) {
		accessEvent.getResource().setAttribute(key, getValue(value));
		return this;

	}

	public State addAction(String key, String value) {
		accessEvent.getAction().setAttribute(key, getValue(value));
		return this;
	}

	public State addSubject(String key, String value) {
		accessEvent.getSubject().setAttribute(key, getValue(value));
		return this;
	}

	public State addEnvironment(String key, String value) {
		accessEvent.getEnvironment().setAttribute(key, getValue(value));
		return this;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public AccessEvent getAccessEvent() {
		return accessEvent;
	}

}
