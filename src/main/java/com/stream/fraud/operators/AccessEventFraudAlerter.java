package com.stream.fraud.operators;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;

import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.FraudAccessEvent;
import com.stream.ml.classifier.OnlineAnomalyDetector;

public class AccessEventFraudAlerter extends ProcessWindowFunction<AccessEvent, FraudAccessEvent, Object, GlobalWindow> {
	private static final long serialVersionUID = 1L;

	private final OnlineAnomalyDetector anomalyDetector;

	public AccessEventFraudAlerter(OnlineAnomalyDetector anomalyDetector) {
		this.anomalyDetector = anomalyDetector;
	}

	@Override
	public void process(Object key, Context context, Iterable<AccessEvent> accessEvents, Collector<FraudAccessEvent> out) {
		for(AccessEvent accessEvent: accessEvents) {
			try {
				if (anomalyDetector.isAnomaly(accessEvent)) {
					
					out.collect(new FraudAccessEvent(accessEvent,  " appears to be fraud, deviationScore = " + anomalyDetector.isAnomaly(accessEvent)));
				}
				anomalyDetector.onlineFit(accessEvent, false);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
			
	}
}
