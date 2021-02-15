package com.stream.fraud.model;

import java.util.HashMap;

public class AttributeContainer {
	
	private HashMap<String, Object> attributes = new HashMap<>();
	
	/**
	 * @return the attributes
	 */
	public HashMap<String, Object> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(HashMap<String, Object> attributes) {
		this.attributes = attributes;
	}

	public AttributeContainer setAttribute(String key, Object value) {
		attributes.put(key, value);
		return this;
	}
	
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

}
