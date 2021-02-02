package com.stream.fraud.operators;

import com.ml.classifier.AnomalyDetector;
import com.stream.fraud.model.FraudTxn;
import com.stream.fraud.model.TxnEvent;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.Map;

public class TxnFraudAlerter extends ProcessWindowFunction<TxnEvent, FraudTxn, Object, GlobalWindow> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int HIGH_THRESHOLD =  2;

	private final AnomalyDetector anomalyDetector;

	public TxnFraudAlerter(AnomalyDetector anomalyDetector) {
		this.anomalyDetector = anomalyDetector;
	}

	@Override
	public void process(Object key, Context context, Iterable<TxnEvent> txnEvents, Collector<FraudTxn> out) {
		for(TxnEvent txnEvent: txnEvents) {
			Map<String, Double> txnEventMap = txnEvent.toMap();
			try {
				System.out.println("*************");
				System.out.println("Received " + txnEvent.toMap());
				System.out.println("*************");
				if (anomalyDetector.isAnomaly(txnEventMap)) {
					System.out.println("*************");
					System.out.println("Fraud detected "+ txnEventMap);
					System.out.println("*************");
					out.collect(new FraudTxn(txnEvent.getUserId(), "A transaction by user " + txnEvent.getUserId(),
							"of value " + txnEvent.getAmount() + " appears to be fraud, deviationScore = " + anomalyDetector.evaluate(txnEventMap)));
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
			
	}
}
