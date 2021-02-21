package com.stream.simulation;

import java.util.Map.Entry;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.stream.fraud.model.AccessEvent;

public class State {
	private String stateName;
	private AccessEvent accessEventTemplate = new AccessEvent();
	
	public State(String stateName) {
		this.stateName = stateName;
	}
	
	public State() {
		
	}
	
	private static Object eval(Object value, AccessEvent accessEvent) {
		try {
			ExpressionParser parser = new SpelExpressionParser();
			StandardEvaluationContext context=new StandardEvaluationContext(accessEvent);
			return parser.parseExpression(value.toString()).getValue(context);
		}catch(Exception e) {
			//TODO: log error
			//e.printStackTrace();
			return value;
		}
	}

	public State addResource(String key, String value) {
		accessEventTemplate.getResource().setAttribute(key, value);
		return this;

	}

	public State addAction(String key, String value) {
		accessEventTemplate.getAction().setAttribute(key, value);
		return this;
	}

	public State addSubject(String key, String value) {
		accessEventTemplate.getSubject().setAttribute(key, value);
		return this;
	}

	public State addEnvironment(String key, String value) {
		accessEventTemplate.getEnvironment().setAttribute(key, value);
		return this;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public AccessEvent ehanceAccessEvent(AccessEvent accessEvent) {
			for(Entry<String, Object> entry: this.accessEventTemplate.getResource().getAttributes().entrySet()) {
				accessEvent.getResource().setAttribute(entry.getKey(), eval(entry.getValue(), accessEvent));
			}


			for(Entry<String, Object> entry: this.accessEventTemplate.getSubject().getAttributes().entrySet()) {
				accessEvent.getSubject().setAttribute(entry.getKey(), eval(entry.getValue(), accessEvent));
			}
			
			for(Entry<String, Object> entry: this.accessEventTemplate.getAction().getAttributes().entrySet()) {
				accessEvent.getAction().setAttribute(entry.getKey(), eval(entry.getValue(), accessEvent));
			}
			
			for(Entry<String, Object> entry: this.accessEventTemplate.getEnvironment().getAttributes().entrySet()) {
				accessEvent.getEnvironment().setAttribute(entry.getKey(), eval(entry.getValue(), accessEvent));
			}

		return accessEvent;
	}

}
