package com.stream.telecom.operators;

import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import com.stream.telecom.model.TelecomCallRecord;

public class ValidTelecomCallRecordTrigger extends Trigger<TelecomCallRecord, TimeWindow> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -661345183977280671L;

	@Override
	public TriggerResult onElement(TelecomCallRecord entityEvent, long l, TimeWindow timeWindow,
			org.apache.flink.streaming.api.windowing.triggers.Trigger.TriggerContext triggerContext) throws Exception {
		
		//TODO: check if the record is valid
		return TriggerResult.FIRE_AND_PURGE;
	}

	@Override
	public TriggerResult onProcessingTime(long l, TimeWindow timeWindow,
			org.apache.flink.streaming.api.windowing.triggers.Trigger.TriggerContext triggerContext) throws Exception {
		return null;
	}

	@Override
	public TriggerResult onEventTime(long l, TimeWindow timeWindow,
			org.apache.flink.streaming.api.windowing.triggers.Trigger.TriggerContext triggerContext) throws Exception {
		return null;
	}

	@Override
	public void clear(org.apache.flink.streaming.api.windowing.windows.TimeWindow timeWindow,
			org.apache.flink.streaming.api.windowing.triggers.Trigger.TriggerContext triggerContext) throws Exception {

	}
}
