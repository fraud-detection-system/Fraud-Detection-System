package com.stream.telecom;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import com.stream.TelecomSystem;

public class ThresholdBasedAlerter extends ProcessWindowFunction<TelecomCallRecord, TelecomUsageAlert, Object, TimeWindow> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void process(Object key, Context context, Iterable<TelecomCallRecord> telecomCallRecords, Collector<TelecomUsageAlert> out) {
		int count = 0;
		for(TelecomCallRecord telecomRecord: telecomCallRecords) {
			count++;
		}
		if(count < TelecomSystem.LOW_THRESHOLD) {
			out.collect(new TelecomUsageAlert(context.window().maxTimestamp(), "Low Threshold",
					"found "+count+" calls, below threshold of "+TelecomSystem.LOW_THRESHOLD));
			
		}
		if (count > TelecomSystem.HIGH_THRESHOLD) {
			out.collect(new TelecomUsageAlert(context.window().maxTimestamp(), "High Threshold",
					"found "+count+" calls, exceeding threshold of "+TelecomSystem.HIGH_THRESHOLD));
		}
			
	}
}
