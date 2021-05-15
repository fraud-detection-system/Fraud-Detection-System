package com.stream;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FraudDetectionStreamProcessing {
	private final static Logger logger = LoggerFactory.getLogger(FraudDetectionStreamProcessing.class.getName());
	
	private List<String []> featureAttributes = new ArrayList<>();
	private List<String []> selectorAttributes = new ArrayList<>();
	private List<String []> ml = new ArrayList<>();
	private List<String []> swimlaneAttributes = new ArrayList<>();
	private List<String []> historyKeyAttributes = new ArrayList<>();
	private List<String []> historyDiffAttributes = new ArrayList<>();
	private List<String []> enrichments = new ArrayList<>();
	private List<String []> preDeliveryEnrichments = new ArrayList<>();
	private List<String []> securityProcessings = new ArrayList<>();
	private boolean monitoring = true;
	
	/**
	 * @return the monitoring
	 */
	public boolean isMonitoring() {
		return monitoring;
	}

	/**
	 * @param monitoring the monitoring to set
	 * @return 
	 */
	public FraudDetectionStreamProcessing setMonitoring(boolean monitoring) {
		this.monitoring = monitoring;
		return this;
	}

	/**
	 * @return the selectorAttributes
	 */
	public List<String[]> getSelectorAttributes() {
		return selectorAttributes;
	}

	/**
	 * @param selectorAttributes the selectorAttributes to set
	 */
	public void setSelectorAttributes(List<String[]> selectorAttributes) {
		this.selectorAttributes = selectorAttributes;
	}

	/**
	 * @return the ml
	 */
	public List<String[]> getMl() {
		return ml;
	}

	/**
	 * @param ml the ml to set
	 */
	public void setMl(List<String[]> ml) {
		this.ml = ml;
	}

	/**
	 * @return the enrichments
	 */
	public List<String[]> getEnrichments() {
		return enrichments;
	}

	/**
	 * @param enrichments the enrichments to set
	 */
	public void setEnrichments(List<String[]> enrichments) {
		this.enrichments = enrichments;
	}

	/**
	 * @return the postProcessingEnrichments
	 */
	public List<String[]> getPreDeliveryEnrichments() {
		return preDeliveryEnrichments;
	}

	/**
	 * @param postProcessingEnrichments the postProcessingEnrichments to set
	 */
	public void setPreDeliveryEnrichments(List<String[]> postProcessingEnrichments) {
		this.preDeliveryEnrichments = postProcessingEnrichments;
	}

	/**
	 * @return the securityProcessings
	 */
	public List<String[]> getSecurityProcessings() {
		return securityProcessings;
	}

	/**
	 * @param securityProcessings the securityProcessings to set
	 */
	public void setSecurityProcessings(List<String[]> securityProcessings) {
		this.securityProcessings = securityProcessings;
	}
	
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
	
	public FraudDetectionStreamProcessing addFeature(String attributeCategory, String attributeName, String attributeType) {
		featureAttributes.add(new String[] {
				attributeCategory,
				attributeName,
				attributeType
		});
		return this;
	}
	
	public FraudDetectionStreamProcessing addSwimlane(String attributeCategory, String attributeName) {
		swimlaneAttributes.add(new String[] {
				attributeCategory,
				attributeName
		});
		return this;
	}
	
	public FraudDetectionStreamProcessing addHistory(String attributeCategory, String attributeName, String historySize) {
		historyKeyAttributes.add(new String[] {
				attributeCategory,
				attributeName,
				historySize
		});
		return this;
	}
	
	public FraudDetectionStreamProcessing addHistoryEnrichment(String attributeCategory, String attributeName, String diffStrategy, String diffAttributeCategory, String diffAttributeName) {
		historyDiffAttributes.add(new String[] {
				attributeCategory,
				attributeName,
				diffStrategy,
				diffAttributeCategory,
				diffAttributeName
		});
		return this;
	}
	
	public FraudDetectionStreamProcessing addSelector(String attributeCategory, String attributeName, String attributeValue) {
		selectorAttributes.add(new String[] {
				attributeCategory,
				attributeName,
				attributeValue
		});
		return this;
	}
	
	public FraudDetectionStreamProcessing addEnrichment(String attributeCategory, String attributeName, String referenceDataType, String referenceDataKey) {
		enrichments.add(new String[] {
				attributeCategory,
				attributeName,
				referenceDataType,
				referenceDataKey
		});
		return this;
	}
	
	public FraudDetectionStreamProcessing addPreDeliveryEnrichment(String attributeCategory, String attributeName, String referenceDataType, String referenceDataKey) {
		preDeliveryEnrichments.add(new String[] {
				attributeCategory,
				attributeName,
				referenceDataType,
				referenceDataKey
		});
		return this;
	}
	
	public FraudDetectionStreamProcessing addSecurityProcessing(String attributeCategory, String attributeName, String typeOfProcessing) {
		securityProcessings.add(new String[] {
				attributeCategory,
				attributeName,
				typeOfProcessing
		});
		return this;
	}
	
	public FraudDetectionStreamProcessing addML(String mlName) {
		ml.add(new String[] {
				mlName
		});
		return this;
	}

	public static void main(String[] args) {

	}

}
