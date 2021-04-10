package com.stream.fraud;


import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import com.stream.Workflow;
import com.stream.fraud.model.Entity;
import com.stream.fraud.operators.EntitySink;
import com.stream.fraud.operators.JsonToEntity;
import com.stream.integration.LocalKafka;

public class OnlineReferenceDataWorkflow extends Workflow {

    public void run() throws Exception {


        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        /**
         * Definition of workflow
         */
        LocalKafka kafkaConsumer = new LocalKafka();
        SourceFunction<ObjectNode> kafkaSource = kafkaConsumer.getReferenceDataSourceAsJson();

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStream<Entity> stream = env.addSource(kafkaSource)
                .returns(ObjectNode.class)
                .flatMap(new JsonToEntity());
        
        stream.addSink(new EntitySink("reference data stream"));

        // env.setParallelism(1);
        //env.enableCheckpointing(1000);

        env.execute(LocalKafka.class.getCanonicalName());
    }

}