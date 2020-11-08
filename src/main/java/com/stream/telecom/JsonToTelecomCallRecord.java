package com.stream.telecom;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.util.Collector;

public class JsonToTelecomCallRecord implements FlatMapFunction<ObjectNode, TelecomCallRecord> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ENTITY_KEY = "entity-key";
	

	@Override
	public void flatMap(ObjectNode jsonObj, Collector<TelecomCallRecord> collector) throws Exception {
		TelecomCallRecord telecomCallRecord = new TelecomCallRecord();
		JsonNode jsonNode = jsonObj.get("value");
		telecomCallRecord.setFrom(jsonNode.get("from").textValue());
		telecomCallRecord.setTo(jsonNode.get("to").textValue());
		telecomCallRecord.setStartTime(jsonNode.get("startTime").textValue());
		telecomCallRecord.setDurationInMillis(jsonNode.get("durationInMillis").textValue());
		telecomCallRecord.setFromCurrentLocation(jsonNode.get("fromCurrentLocation").textValue());
		telecomCallRecord.setToCurrentLocation(jsonNode.get("toCurrentLocation").textValue());
		
		
		collector.collect(telecomCallRecord);
	}

}
