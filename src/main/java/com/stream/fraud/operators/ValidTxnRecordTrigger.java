package com.stream.fraud.operators;

import com.stream.fraud.model.TxnEvent;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

public class ValidTxnRecordTrigger extends Trigger<TxnEvent, TimeWindow> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -661345183977280671L;

	@Override
	public TriggerResult onElement(TxnEvent entityEvent, long l, TimeWindow timeWindow,
								   TriggerContext triggerContext) throws Exception {
		
		//TODO: check if the record is valid
		return TriggerResult.FIRE_AND_PURGE;
	}

	@Override
	public TriggerResult onProcessingTime(long l, TimeWindow timeWindow,
			TriggerContext triggerContext) throws Exception {
		return null;
	}

	@Override
	public TriggerResult onEventTime(long l, TimeWindow timeWindow,
			TriggerContext triggerContext) throws Exception {
		return null;
	}

	@Override
	public void clear(TimeWindow timeWindow,
                      TriggerContext triggerContext) throws Exception {

	}
}
