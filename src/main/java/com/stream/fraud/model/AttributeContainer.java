package com.stream.fraud.model;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;

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
		HashMap<String, Object> replacedAttributes = null;
		for(Entry<String, Object> entry: attributes.entrySet()) {
			if(entry.getValue() instanceof JsonNode) {
				if(replacedAttributes == null) {
					replacedAttributes = new HashMap<>();
				}
				replacedAttributes.put(entry.getKey(), ((JsonNode)entry.getValue()).asText());
			}
		}
		if(replacedAttributes != null) {
			this.attributes.putAll(replacedAttributes);
		}
	}
	
	private Object toValue(Object value) {
		if(value instanceof JsonNode) {
			return ((JsonNode)value).asText();
		}
		return value;
	}

	public AttributeContainer setAttribute(String key, Object value) {
		attributes.put(key, toValue(value));
		return this;
	}
	
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

}
