package com.stream.accounting;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

public class GenerateFlinkTimestamp implements FlatMapFunction<EntityEvent, EntityEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FLINK_TIMESTAMP = "rtdpp-flink-timestamp";

	@Override
	public void flatMap(EntityEvent entityEvent, Collector<EntityEvent> collector) throws Exception {
		entityEvent.setProperty(FLINK_TIMESTAMP, (Long)System.currentTimeMillis());
		collector.collect(entityEvent);
	}
}
