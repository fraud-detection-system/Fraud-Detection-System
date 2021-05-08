package com.stream.fraud;


import java.util.List;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;

import com.stream.FraudDetectionSystem;
import com.stream.Workflow;
import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.FraudAccessEvent;
import com.stream.fraud.operators.AccessEventFraudAlerter;
import com.stream.fraud.operators.EnrichAndSaveHistory;
import com.stream.fraud.operators.EnrichWithReferenceData;
import com.stream.fraud.operators.FraudAccessEventSink;
import com.stream.fraud.operators.JsonToAccessEvent;
import com.stream.fraud.operators.ValidAccessEventTrigger;
import com.stream.integration.LocalKafka;

public class OnlineFraudDetectionWorkflow extends Workflow {

    private static final int WINDOW_SIZE=5;
    private FraudDetectionSystem fraudDetectionSystem = null;
    
    private static class SwimlaneKeySelector implements KeySelector<AccessEvent, Object>{
    	private static final long serialVersionUID = 1L;
    	private List<String[]> swimlaneAttributes;
    	
    	public SwimlaneKeySelector(List<String[]> swimlaneAttributes) {
    		this.swimlaneAttributes = swimlaneAttributes;
    	}
    	
    	

		@Override
		public Object getKey(AccessEvent accessEvent) throws Exception {
			String key = "";
			String keySeparator = "#";
			for(String []swimlaneAttribute: swimlaneAttributes) {
				Object val = accessEvent.get(swimlaneAttribute);
				key += keySeparator+val;
			}
			return key;
		}
    	
    	
    }
    public OnlineFraudDetectionWorkflow(FraudDetectionSystem fraudDetectionSystem) {
		this.fraudDetectionSystem = fraudDetectionSystem;
	}
    
	@SuppressWarnings("deprecation")
    public void run() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        /**
         * Definition of workflows
         */
        LocalKafka kafkaConsumer = new LocalKafka();
        SourceFunction<ObjectNode> kafkaSource = kafkaConsumer.getAccessEventSourceAsJson();

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStream<AccessEvent> stream = env.addSource(kafkaSource)
                .returns(ObjectNode.class)
                .flatMap(new JsonToAccessEvent());
        
        //Enrich the object
        stream = stream.process(new EnrichWithReferenceData());
        
      
        stream = stream.process(new EnrichAndSaveHistory(fraudDetectionSystem.getHistoryKeyAttributes(), fraudDetectionSystem.getHistoryDiffAttributes()));

       

		KeyedStream<AccessEvent, Object> txnKeyedStream = stream.keyBy(new SwimlaneKeySelector(fraudDetectionSystem.getSwimlaneAttributes()));

		SingleOutputStreamOperator<FraudAccessEvent> fraudDetectionStream =  txnKeyedStream
			.window(GlobalWindows.create())
			.trigger(new ValidAccessEventTrigger())
			.process(new AccessEventFraudAlerter(fraudDetectionSystem.getFeatureAttributes()));

		fraudDetectionStream
			.addSink(new FraudAccessEventSink("from current location alert"));


        //env.setParallelism(1);
        //env.enableCheckpointing(1000);

        env.execute(LocalKafka.class.getCanonicalName());
    }

}