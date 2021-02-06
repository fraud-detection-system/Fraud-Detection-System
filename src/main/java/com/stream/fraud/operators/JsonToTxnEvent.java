package com.stream.fraud.operators;

import com.stream.fraud.model.TxnEvent;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.util.Collector;

public class JsonToTxnEvent implements FlatMapFunction<ObjectNode, TxnEvent> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void flatMap(ObjectNode jsonObj, Collector<TxnEvent> collector) throws Exception {
        TxnEvent fraudEvent = new TxnEvent();
        JsonNode jsonNode = jsonObj.get("value");
        fraudEvent.setUserId(jsonNode.get("userId").textValue());
        fraudEvent.setAmount(jsonNode.get("amount").doubleValue());
        fraudEvent.setMilesFromLastTxn(jsonNode.get("milesFromLastTxn").intValue());
        fraudEvent.setHoursFromLastTxn(jsonNode.get("hoursFromLastTxn").intValue());

        collector.collect(fraudEvent);
    }

}
