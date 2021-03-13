package com.stream.fraud.operators;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;

import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.FraudAccessEvent;
import com.stream.ml.classifier.OnlineAnomalyDetector;
import com.stream.ml.classifier.OnlineAnomalyDetector.MultiClassAnomalyOutput;

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
				for(MultiClassAnomalyOutput multiClassAnomalyOutput : anomalyDetector.isAnomaly(accessEvent)) {
					if(null != multiClassAnomalyOutput && multiClassAnomalyOutput.getIsAnomaly()) {
						FraudAccessEvent fraudAccessEvent = new FraudAccessEvent(accessEvent);
						fraudAccessEvent.getEnvironment().setAttribute("nameOfClassfierThatDetectedAnomaly", multiClassAnomalyOutput.getClassifierName());
						out.collect(fraudAccessEvent);
					}
				}
				anomalyDetector.onlineFit(accessEvent, false);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
			
	}
}
