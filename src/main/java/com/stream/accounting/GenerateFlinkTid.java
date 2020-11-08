package com.stream.accounting;

import java.util.UUID;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

public class GenerateFlinkTid implements FlatMapFunction<EntityEvent, EntityEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FLINK_TID = "rtdpp-flink-tid";

	@Override
	public void flatMap(EntityEvent entityEvent, Collector<EntityEvent> collector) throws Exception {
		entityEvent.setProperty(FLINK_TID, UUID.randomUUID().toString());
		collector.collect(entityEvent);
	}
}
