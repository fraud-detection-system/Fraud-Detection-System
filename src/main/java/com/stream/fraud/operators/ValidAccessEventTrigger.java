package com.stream.fraud.operators;

import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;

import com.stream.fraud.model.AccessEvent;

public class ValidAccessEventTrigger extends Trigger<AccessEvent, GlobalWindow> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -661345183977280683L;

	@Override
	public TriggerResult onElement(AccessEvent entityEvent, long l, GlobalWindow timeWindow,
								   TriggerContext triggerContext) throws Exception {
		
		//TODO: check if the record is valid
		return TriggerResult.FIRE_AND_PURGE;
	}

	@Override
	public TriggerResult onProcessingTime(long l, GlobalWindow timeWindow,
			TriggerContext triggerContext) throws Exception {
		return null;
	}

	@Override
	public TriggerResult onEventTime(long l, GlobalWindow timeWindow,
			TriggerContext triggerContext) throws Exception {
		return null;
	}

	@Override
	public void clear(GlobalWindow timeWindow,
                      TriggerContext triggerContext) throws Exception {

	}
}
