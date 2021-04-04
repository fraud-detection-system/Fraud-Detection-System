package com.stream.fraud.operators;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.util.Collector;

import com.stream.delivery.Monitoring;
import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.AttributeContainer;

public class JsonToAccessEvent implements FlatMapFunction<ObjectNode, AccessEvent> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void flatMap(ObjectNode jsonObj, Collector<AccessEvent> collector) throws Exception {
        AccessEvent accessEvent = new AccessEvent();
        JsonNode jsonNode = jsonObj.get("value");
        fillAttributeContainer(jsonNode.get("subject"), accessEvent.getSubject());
        fillAttributeContainer(jsonNode.get("resource"), accessEvent.getResource());
        fillAttributeContainer(jsonNode.get("action"), accessEvent.getAction());
        fillAttributeContainer(jsonNode.get("environment"), accessEvent.getEnvironment());

        collector.collect(accessEvent);
        Monitoring.register(accessEvent);
    }
    
    private void fillAttributeContainer(JsonNode jsonNode, AttributeContainer attributeContainer) {
    	final JsonNode attributeJsonNode = jsonNode.get("attributes");
    	attributeJsonNode.fieldNames().forEachRemaining((fieldName) -> {
    		attributeContainer.setAttribute(fieldName, attributeJsonNode.get(fieldName));
    	});
    }

}
