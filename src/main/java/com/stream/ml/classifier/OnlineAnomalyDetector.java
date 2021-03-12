package com.stream.ml.classifier;

import java.io.Serializable;
import java.util.List;

import com.stream.fraud.model.AccessEvent;

public abstract class OnlineAnomalyDetector implements Serializable {
    public OnlineAnomalyDetector(List<String[]> attributes) {
    }
    
    public abstract void onlineFit(AccessEvent accessEvent, boolean isAnamoly) ;
    
    public abstract boolean isAnomaly(AccessEvent accessEvent);
}