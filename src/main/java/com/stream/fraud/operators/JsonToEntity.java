package com.stream.fraud.operators;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.util.Collector;

import com.stream.delivery.Monitoring;
import com.stream.fraud.model.Entity;
import com.stream.fraud.model.AttributeContainer;

public class JsonToEntity implements FlatMapFunction<ObjectNode, Entity> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void flatMap(ObjectNode jsonObj, Collector<Entity> collector) throws Exception {
    	Entity entity = new Entity();
        JsonNode jsonNode = jsonObj.get("value");
        fillAttributeContainer(jsonNode,entity);
        collector.collect(entity);
        Monitoring.register(entity);
    }
    
    private void fillAttributeContainer(JsonNode jsonNode, AttributeContainer attributeContainer) {
    	final JsonNode attributeJsonNode = jsonNode.get("attributes");
    	attributeJsonNode.fieldNames().forEachRemaining((fieldName) -> {
    		attributeContainer.setAttribute(fieldName, attributeJsonNode.get(fieldName));
    	});
    }

}
