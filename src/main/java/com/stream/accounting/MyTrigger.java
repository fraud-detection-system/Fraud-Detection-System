package com.stream.accounting;

import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;

public class MyTrigger extends Trigger<EntityEvent, GlobalWindow> {
	@Override
	public TriggerResult onElement(EntityEvent entityEvent, long l, GlobalWindow globalWindow,
			org.apache.flink.streaming.api.windowing.triggers.Trigger.TriggerContext triggerContext) throws Exception {
		long companyId = Long.parseLong(entityEvent.getEntity().getCompanyId());
		if (companyId % 2 == 0) {
			return TriggerResult.FIRE_AND_PURGE;
		}
		long amount = entityEvent.getEntity().getAmount();
		if (amount > 10000) {
			return TriggerResult.FIRE_AND_PURGE;
		}
		
		return TriggerResult.PURGE;
	}

	@Override
	public TriggerResult onProcessingTime(long l, GlobalWindow globalWindow,
			org.apache.flink.streaming.api.windowing.triggers.Trigger.TriggerContext triggerContext) throws Exception {
		return null;
	}

	@Override
	public TriggerResult onEventTime(long l, GlobalWindow globalWindow,
			org.apache.flink.streaming.api.windowing.triggers.Trigger.TriggerContext triggerContext) throws Exception {
		return null;
	}

	@Override
	public void clear(org.apache.flink.streaming.api.windowing.windows.GlobalWindow globalWindow,
			org.apache.flink.streaming.api.windowing.triggers.Trigger.TriggerContext triggerContext) throws Exception {

	}
}
