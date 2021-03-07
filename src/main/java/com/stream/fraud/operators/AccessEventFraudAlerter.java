package com.stream.fraud.operators;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;

import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.FraudAccessEvent;
import com.stream.ml.classifier.OnlineAnomalyDetector;

public class AccessEventFraudAlerter extends ProcessWindowFunction<AccessEvent, FraudAccessEvent, Object, GlobalWindow> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int HIGH_THRESHOLD =  2;

	private final OnlineAnomalyDetector anomalyDetector;

	public AccessEventFraudAlerter(OnlineAnomalyDetector anomalyDetector) {
		this.anomalyDetector = anomalyDetector;
	}

	@Override
	public void process(Object key, Context context, Iterable<AccessEvent> txnEvents, Collector<FraudAccessEvent> out) {
		for(AccessEvent accessEvent: txnEvents) {
			try {
				if (anomalyDetector.isAnomaly(accessEvent)) {
					
					out.collect(new FraudAccessEvent(accessEvent,  " appears to be fraud, deviationScore = " + anomalyDetector.evaluate(accessEvent)));
				}
				anomalyDetector.onlineFit(new AccessEvent[] {accessEvent });
			}catch (Exception e){
				e.printStackTrace();
			}
		}
			
	}
}
