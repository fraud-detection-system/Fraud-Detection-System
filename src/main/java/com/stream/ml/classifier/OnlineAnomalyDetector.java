package com.stream.ml.classifier;

import java.io.Serializable;
import java.util.List;

import com.stream.fraud.model.AccessEvent;

public abstract class OnlineAnomalyDetector implements Serializable {
	private static final long serialVersionUID = -2247083247745434666L;
	
	public enum FRAUD_CLASS { NORMAL, ANOMALY, UNKNOWN};

	public class MultiClassAnomalyOutput {
    	String classifierName;
    	boolean isAnomaly;
    	MultiClassAnomalyOutput (String classifierName, boolean isAnomaly){
    		this.classifierName = classifierName;
    		this.isAnomaly = isAnomaly;
    	}
    	public String getClassifierName() {
    		return classifierName;
    	}
    	
    	public boolean getIsAnomaly() {
    		return this.isAnomaly;
    	}
    	
    	public String toString() {
    		return this.classifierName+" : "+isAnomaly;
    	}
    }
	
    public OnlineAnomalyDetector(List<String[]> attributes) {
    }
    
    public abstract void onlineFit(AccessEvent accessEvent, FRAUD_CLASS fraudClass) ;
    
    public abstract MultiClassAnomalyOutput[] isAnomaly(AccessEvent accessEvent);
}