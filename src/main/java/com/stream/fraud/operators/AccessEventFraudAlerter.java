package com.stream.fraud.operators;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;

import com.stream.delivery.Monitoring;
import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.FraudAccessEvent;
import com.stream.ml.classifier.OnlineAnomalyDetector;
import com.stream.ml.classifier.OnlineAnomalyDetector.FRAUD_CLASS;
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
				FraudAccessEvent fraudAccessEvent = null;
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
				
				Object obj = accessEvent.getEnvironment().getAttribute("fraud");
				FRAUD_CLASS fraudClass = FRAUD_CLASS.UNKNOWN;
				if(obj != null) {
					if(obj.equals("true")) {
						fraudClass = FRAUD_CLASS.ANOMALY;
					}else {
						fraudClass = FRAUD_CLASS.NORMAL;
					}
				}
				anomalyDetector.onlineFit(accessEvent, fraudClass);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
			
	}
}
