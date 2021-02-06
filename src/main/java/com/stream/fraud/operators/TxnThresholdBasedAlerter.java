package com.stream.fraud.operators;

import com.stream.fraud.model.FraudTxn;
import com.stream.fraud.model.TxnEvent;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class TxnThresholdBasedAlerter extends ProcessWindowFunction<TxnEvent, FraudTxn, Object, TimeWindow> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int HIGH_THRESHOLD =  2;

	@Override
	public void process(Object key, Context context, Iterable<TxnEvent> txnEvents, Collector<FraudTxn> out) {
		int count = 0;
		for(TxnEvent txnEvent: txnEvents) {
			count++;
		}

		if (count > HIGH_THRESHOLD) {
			out.collect(new FraudTxn(txnEvents.iterator().next().getUserId(), "High txn count",
					"found "+count+", exceeding threshold of " + HIGH_THRESHOLD));
		}
			
	}
}
