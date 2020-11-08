package com.stream.accounting;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;

public class CompanyIdAndAmountFraudDetector extends ProcessWindowFunction<EntityEvent, Alert, Object, GlobalWindow> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void process(Object key, Context context, Iterable<EntityEvent> entityEvents, Collector<Alert> out) {
		EntityEvent entityEvent = entityEvents.iterator().next();
		long companyId = Long.parseLong(entityEvent.getEntity().getCompanyId());
		if (companyId % 2 == 0) {
			out.collect(new Alert(context.window().maxTimestamp(), entityEvent, "CompanyId fraud",
					"companyId is " + companyId));
		}
		long amount = entityEvent.getEntity().getAmount();
		if (amount > 10000) {
			out.collect(new Alert(context.window().maxTimestamp(), entityEvent, "Amount fraud",
					"amount exceeded the max threshold and is ::" + amount));
		}
	}
}
