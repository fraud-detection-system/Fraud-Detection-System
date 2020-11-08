package com.stream.accounting;

import org.apache.avro.generic.GenericData;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import com.stream.accounting.EntityEvent.EVENT_TYPE;

public class AvroToEntityEvent implements FlatMapFunction<GenericData.Record, EntityEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ENTITY_KEY = "entity-key";

	@Override
	public void flatMap(GenericData.Record record, Collector<EntityEvent> collector) throws Exception {
		EntityEvent entityEvent = new EntityEvent(record);
		//TODO: figure out this at a later point automatically. For now hard coded it.
		entityEvent.setEventType(EVENT_TYPE.INSERT);
		collector.collect(entityEvent);
	}

}
