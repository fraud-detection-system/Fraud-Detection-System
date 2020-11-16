package com.stream.telecom;


import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import com.stream.Workflow;
import com.stream.telecom.integration.LocalKafka;
import com.stream.telecom.model.TelecomCallRecord;
import com.stream.telecom.operators.AlertSink;
import com.stream.telecom.operators.JsonToTelecomCallRecord;
import com.stream.telecom.operators.TelecomUsageAlert;
import com.stream.telecom.operators.ThresholdBasedAlerter;
import com.stream.telecom.operators.ValidTelecomCallRecordTrigger;

public class TelecomUsageWorkflow extends Workflow{

	@SuppressWarnings("deprecation")
	public void run() throws Exception{
		
		
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		
		
		/**
		 * Definition of workflows
		 */
		LocalKafka kafkaConsumer = new LocalKafka();
	    SourceFunction<ObjectNode> kafkaSource = kafkaConsumer.getSourceAsJson();
	   
	    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		
		DataStream<TelecomCallRecord> stream = env
													.addSource(kafkaSource)
													.returns(ObjectNode.class)
													.flatMap(new JsonToTelecomCallRecord());
		
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
		
		KeyedStream<TelecomCallRecord, Object> fromCurrentLocationKeyedStream = stream
			.keyBy(new KeySelector<TelecomCallRecord, Object>() {
				private static final long serialVersionUID = 1L;

				@Override
				   public Object getKey(TelecomCallRecord telecomCallRecord) throws Exception {
				      return telecomCallRecord.getFromCurrentLocation();
				   }
				});
		SingleOutputStreamOperator<TelecomUsageAlert> fromLocationAlertStream =  fromCurrentLocationKeyedStream
			.window(TumblingEventTimeWindows.of(Time.seconds(TelecomSystem.WINDOW_SIZE)))
			.trigger(new ValidTelecomCallRecordTrigger())
			.process(new ThresholdBasedAlerter());
		fromLocationAlertStream
			.writeAsText(TelecomSystem.FROM_LOCATION_ALERT_FILE_DIR, FileSystem.WriteMode.OVERWRITE);
		fromLocationAlertStream
			.addSink(new AlertSink("from current location alert"));
		
		//1.2
		
		KeyedStream<TelecomCallRecord, Object> toCurrentLocationKeyedStream = stream
		.keyBy(new KeySelector<TelecomCallRecord, Object>() {
			private static final long serialVersionUID = 1L;

			@Override
			   public Object getKey(TelecomCallRecord telecomCallRecord) throws Exception {
			      return telecomCallRecord.getToCurrentLocation();
			   }
			});
		SingleOutputStreamOperator<TelecomUsageAlert> toLocationAlertStream = toCurrentLocationKeyedStream
				.window(TumblingEventTimeWindows.of(Time.seconds(TelecomSystem.WINDOW_SIZE)))
				.trigger(new ValidTelecomCallRecordTrigger())
				.process(new ThresholdBasedAlerter());
		
		toLocationAlertStream
		.writeAsText(TelecomSystem.TO_LOCATION_ALERT_FILE_DIR, FileSystem.WriteMode.OVERWRITE);
		toLocationAlertStream
		.addSink(new AlertSink("to current location alert"));
		
		//1.3
		SingleOutputStreamOperator<TelecomUsageAlert> totalCurrentLocationStream = stream.process(new ProcessFunction<TelecomCallRecord, TelecomCallRecord>(){

			@Override
			public void processElement(TelecomCallRecord arg0,
					ProcessFunction<TelecomCallRecord, TelecomCallRecord>.Context arg1,
					Collector<TelecomCallRecord> arg2) throws Exception {
				arg0.setCurrentLocation(arg0.getFromCurrentLocation());
				arg2.collect(arg0);
				arg0 = (TelecomCallRecord) arg0.clone();
				arg0.setCurrentLocation(arg0.getToCurrentLocation());
				arg2.collect(arg0);
				
			}})
	    .keyBy(new KeySelector<TelecomCallRecord, Object>() {
			private static final long serialVersionUID = 1L;

					@Override
					   public Object getKey(TelecomCallRecord telecomCallRecord) throws Exception {
					      return telecomCallRecord.getCurrentLocation();
					   }
					})
	    .window(TumblingEventTimeWindows.of(Time.seconds(TelecomSystem.WINDOW_SIZE)))
	    .trigger(new ValidTelecomCallRecordTrigger())
		.process(new ThresholdBasedAlerter());
				
		totalCurrentLocationStream
				.writeAsText(TelecomSystem.BOTH_LOCATION_ALERT_FILE_DIR, FileSystem.WriteMode.OVERWRITE);
		totalCurrentLocationStream
				.addSink(new AlertSink("total current location alert"));	

		//2.1		
		SingleOutputStreamOperator<TelecomUsageAlert> fromAlertStream = stream
				.keyBy(new KeySelector<TelecomCallRecord, Object>() {
					private static final long serialVersionUID = 1L;

					@Override
					   public Object getKey(TelecomCallRecord telecomCallRecord) throws Exception {
					      return telecomCallRecord.getFrom();
					   }
					})
				.window(TumblingEventTimeWindows.of(Time.seconds(TelecomSystem.WINDOW_SIZE)))
				.trigger(new ValidTelecomCallRecordTrigger())
				.process(new ThresholdBasedAlerter());
		fromAlertStream
			.writeAsText(TelecomSystem.FROM_ALERT_FILE_DIR, FileSystem.WriteMode.OVERWRITE);
		fromAlertStream
			.addSink(new AlertSink("from alert"));
						
		//2.2				
		SingleOutputStreamOperator<TelecomUsageAlert> toAlertStream = stream
				.keyBy(new KeySelector<TelecomCallRecord, Object>() {
					private static final long serialVersionUID = 1L;

					@Override
					   public Object getKey(TelecomCallRecord telecomCallRecord) throws Exception {
					      return telecomCallRecord.getTo();
					   }
					})
				.window(TumblingEventTimeWindows.of(Time.seconds(TelecomSystem.WINDOW_SIZE)))
				.trigger(new ValidTelecomCallRecordTrigger())
				.process(new ThresholdBasedAlerter());
		toAlertStream
				.writeAsText(TelecomSystem.TO_ALERT_FILE_DIR, FileSystem.WriteMode.OVERWRITE);
		toAlertStream
				.addSink(new AlertSink("to alert"));

		
		// env.setParallelism(1);
		//env.enableCheckpointing(1000);

		env.execute(LocalKafka.class.getCanonicalName());
	}

}