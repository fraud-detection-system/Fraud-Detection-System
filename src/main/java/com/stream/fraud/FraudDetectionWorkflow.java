package com.stream.fraud;


import com.stream.Workflow;

import com.stream.fraud.model.FraudTxn;
import com.stream.fraud.model.TxnEvent;
import com.stream.fraud.operators.FraudEventSink;
import com.stream.fraud.operators.JsonToTxnEvent;
import com.stream.fraud.operators.TxnThresholdBasedAlerter;
import com.stream.fraud.operators.ValidTxnRecordTrigger;
import com.stream.telecom.integration.LocalKafka;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class FraudDetectionWorkflow extends Workflow {

    private static final int WINDOW_SIZE=5;

    @SuppressWarnings("deprecation")
    public void run() throws Exception {


        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        /**
         * Definition of workflows
         */
        LocalKafka kafkaConsumer = new LocalKafka();
        SourceFunction<ObjectNode> kafkaSource = kafkaConsumer.getSourceAsJson();

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStream<TxnEvent> stream = env.addSource(kafkaSource)
                .returns(ObjectNode.class)
                .flatMap(new JsonToTxnEvent());

        //stream.print();

        /*
         *
         * 1.1.1. collect all calls originating from a location
         * 1.1.2. Window based aggregates on it
         * 1.1.3. Compare to low and high thresholds, and generate alerts
         * 1.1.4. Print alerts on stdout and also to a file
         *
         *
         * 1.2.1. collect all calls terminating to a location
         * 1.2.2. Window based aggregates on it
         * 1.2.3. Compare to low and high thresholds, and generate alerts
         * 1.2.4. Print alerts on stdout and also to a file
         *
         * 1.3.1. collect all calls originating/terminating from/to a location
         * 1.3.2. Window based aggregates on it
         * 1.3.3. Compare to low and high thresholds, and generate alerts
         * 1.3.4. Print alerts on stdout and also to a file
         *
         * 2. Do the above first two per user
         *
         * That gives us total 5 streams.
         *
         */

        //1.1

		KeyedStream<TxnEvent, Object> txnKeyedStream = stream
			.keyBy(new KeySelector<TxnEvent, Object>() {
				private static final long serialVersionUID = 1L;

				@Override
				   public Object getKey(TxnEvent txnEvent) throws Exception {
				      return txnEvent.getUserId();
				   }
				});

		SingleOutputStreamOperator<FraudTxn> fraudDetectionStream =  txnKeyedStream
			.window(TumblingEventTimeWindows.of(Time.seconds(WINDOW_SIZE)))
			.trigger(new ValidTxnRecordTrigger())
			.process(new TxnThresholdBasedAlerter());

		fraudDetectionStream
			.addSink(new FraudEventSink("from current location alert"));




        // env.setParallelism(1);
        //env.enableCheckpointing(1000);

        env.execute(LocalKafka.class.getCanonicalName());
    }

}