package com.stream.fraud.operators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;

import com.stream.delivery.Monitoring;
import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.FraudAccessEvent;
import com.stream.ml.classifier.MoAOnlineAnomalyDetector;
import com.stream.ml.classifier.OnlineAnomalyDetector;
import com.stream.ml.classifier.OnlineAnomalyDetector.FRAUD_CLASS;
import com.stream.ml.classifier.OnlineAnomalyDetector.MultiClassAnomalyOutput;

public class AccessEventFraudAlerter extends ProcessWindowFunction<AccessEvent, FraudAccessEvent, Object, GlobalWindow> {
	private static final long serialVersionUID = 1L;

	private final Map<Object, OnlineAnomalyDetector> swimlanedAnomalyDetectorsMap = new HashMap<>();
	
	List<String []> featureAttributes = null;

	public AccessEventFraudAlerter(List<String []> featureAttributes) {
		this.featureAttributes = featureAttributes;
	}

	@Override
	public void process(Object swimlaneKey, Context context, Iterable<AccessEvent> accessEvents, Collector<FraudAccessEvent> out) {
		OnlineAnomalyDetector anomalyDetector = swimlanedAnomalyDetectorsMap.get(swimlaneKey);
		if( anomalyDetector == null) {
			synchronized(this) {
				anomalyDetector = swimlanedAnomalyDetectorsMap.get(swimlaneKey);
				if( anomalyDetector == null) {
					anomalyDetector = new MoAOnlineAnomalyDetector(featureAttributes);
					swimlanedAnomalyDetectorsMap.put(swimlaneKey, anomalyDetector);
				}
			}
			
		}
		for(AccessEvent accessEvent: accessEvents) {
			try {
				FraudAccessEvent fraudAccessEvent = null;
				Object isTraining = accessEvent.getEnvironment().getAttribute("training");
				FRAUD_CLASS fraudClass = FRAUD_CLASS.UNKNOWN;
				
				if(isTraining == null || !"true".equalsIgnoreCase((String) isTraining))
				{
					for(MultiClassAnomalyOutput multiClassAnomalyOutput : anomalyDetector.isAnomaly(accessEvent)) {
						if(null != multiClassAnomalyOutput && multiClassAnomalyOutput.getIsAnomaly()) {
							fraudAccessEvent = new FraudAccessEvent(accessEvent);
							fraudAccessEvent.getEnvironment().setAttribute("nameOfClassfierThatDetectedAnomaly", multiClassAnomalyOutput.getClassifierName());
							fraudAccessEvent.getEnvironment().setAttribute("fraud", "true");
							out.collect(fraudAccessEvent);
						}
					}
					if(null != fraudAccessEvent) {
						Monitoring.register(fraudAccessEvent);
					}
				} else { //This is training event
					Object isFraud = accessEvent.getEnvironment().getAttribute("fraud");
					if(isFraud != null) {
						if(isFraud.equals("true")) {
							fraudClass = FRAUD_CLASS.ANOMALY;
						}else {
							fraudClass = FRAUD_CLASS.NORMAL;
						}
					}
				}
				anomalyDetector.onlineFit(accessEvent, fraudClass);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
			
	}
}
