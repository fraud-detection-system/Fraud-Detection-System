package com.stream;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FraudDetectionSystem {
	private final static Logger logger = LoggerFactory.getLogger(FraudDetectionSystem.class.getName());
	
	private List<String []> featureAttributes = new ArrayList<>();
	private List<String []> selectorAttributes = new ArrayList<>();
	private List<String []> ml = new ArrayList<>();
	private List<String []> swimlaneAttributes = new ArrayList<>();
	private List<String []> historyKeyAttributes = new ArrayList<>();
	private List<String []> historyDiffAttributes = new ArrayList<>();
	
	/**
	 * @return the featureAttributes
	 */
	public List<String[]> getFeatureAttributes() {
		return featureAttributes;
	}

	/**
	 * @param featureAttributes the featureAttributes to set
	 */
	public void setFeatureAttributes(List<String[]> featureAttributes) {
		this.featureAttributes = featureAttributes;
	}

	/**
	 * @return the swimlaneAttributes
	 */
	public List<String[]> getSwimlaneAttributes() {
		return swimlaneAttributes;
	}

	/**
	 * @param swimlaneAttributes the swimlaneAttributes to set
	 */
	public void setSwimlaneAttributes(List<String[]> swimlaneAttributes) {
		this.swimlaneAttributes = swimlaneAttributes;
	}

	/**
	 * @return the historyKeyAttributes
	 */
	public List<String[]> getHistoryKeyAttributes() {
		return historyKeyAttributes;
	}

	/**
	 * @param historyKeyAttributes the historyKeyAttributes to set
	 */
	public void setHistoryKeyAttributes(List<String[]> historyKeyAttributes) {
		this.historyKeyAttributes = historyKeyAttributes;
	}

	/**
	 * @return the historyDiffAttributes
	 */
	public List<String[]> getHistoryDiffAttributes() {
		return historyDiffAttributes;
	}

	/**
	 * @param historyDiffAttributes the historyDiffAttributes to set
	 */
	public void setHistoryDiffAttributes(List<String[]> historyDiffAttributes) {
		this.historyDiffAttributes = historyDiffAttributes;
	}
	
	public FraudDetectionSystem addFeatureAttribute(String attributeCategory, String attributeName, String attributeType) {
		featureAttributes.add(new String[] {
				attributeCategory,
				attributeName,
				attributeType
		});
		return this;
	}
	
	public FraudDetectionSystem addSwimlaneAttribute(String attributeCategory, String attributeName) {
		swimlaneAttributes.add(new String[] {
				attributeCategory,
				attributeName
		});
		return this;
	}
	
	public FraudDetectionSystem addHistoryKeyAttribute(String attributeCategory, String attributeName, String historySize) {
		historyKeyAttributes.add(new String[] {
				attributeCategory,
				attributeName,
				historySize
		});
		return this;
	}
	
	public FraudDetectionSystem addHistoryDiffAttribute(String attributeCategory, String attributeName, String diffStrategy, String diffAttributeCategory, String diffAttributeName) {
		historyDiffAttributes.add(new String[] {
				attributeCategory,
				attributeName,
				diffStrategy,
				diffAttributeCategory,
				diffAttributeName
		});
		return this;
	}
	
	public FraudDetectionSystem addSelectorAttribute(String attributeCategory, String attributeName, String attributeValue) {
		selectorAttributes.add(new String[] {
				attributeCategory,
				attributeName,
				attributeValue
		});
		return this;
	}
	
	public FraudDetectionSystem addML(String mlName) {
		ml.add(new String[] {
				mlName
		});
		return this;
	}

	public static void main(String[] args) {

	}

}
