package com.stream.accounting;

import org.apache.avro.generic.GenericData;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import com.stream.Workflow;

public class AccountingWorkflow extends Workflow {

	public void run() throws Exception{
		
		
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		
		
		/**
		 * Definition of workflows
		 */
		KafkaConsumer kafkaConsumer = new KafkaConsumer();
	    SourceFunction<GenericData.Record> kafkaSource = kafkaConsumer.getSource();
	   
	    env.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime);
		
		DataStream<GenericData.Record> stream = env
													.addSource(kafkaSource)
													.returns(GenericData.Record.class);
		
		//TODO: wanted to use AVRO for throughout, but it is getting complex
		/*stream = stream
					.flatMap(new ConvertToRtdppSchema());*/
		
		DataStream<EntityEvent> entityEventStream = stream
				.flatMap(new AvroToEntityEvent()) ;
				
		entityEventStream = entityEventStream
					.flatMap(new GenerateFlinkTid());
		
		//TODO: use kafka or source system time events and have watermarks
		/*
		stream.assignTimestamps(new TimestampExtractor<GenericData.Record> () {

			@Override
			public long extractTimestamp(Record arg0, long arg1) {
				
				return 0;
			}

			@Override
			public long extractWatermark(Record arg0, long arg1) {
				
				return 0;
			}

			@Override
			public long getCurrentWatermark() {
				
				return 0;
			}})	
		*/
		
		entityEventStream = entityEventStream
				.flatMap(new GenerateFlinkTimestamp());
		

		//1- Streaming ETL - First pipeline - fastest one with watermark - unreliable but fast
		entityEventStream
			.flatMap(new AvroToEntity())
			.addSink(new PostgresSink());
		
		//2a - Fraud detection - detect blacklist companies and large amounts
		entityEventStream
			.flatMap(new AvroToEntity())
			.keyBy(new KeySelector<EntityEvent, Object>() {
				   @Override
				   public Object getKey(EntityEvent entityEvent) throws Exception {
				      return entityEvent.getCompanyId();
				   }
				})
			.window(GlobalWindows.create())
			.trigger(new MyTrigger())
			.process(new CompanyIdAndAmountFraudDetector())
			.addSink(new AlertSink());
		
		//2b -2nd Pipeline for fraud 
		entityEventStream
		.flatMap(new AvroToEntity())
		.keyBy(new KeySelector<EntityEvent, Object>() {
			   @Override
			   public Object getKey(EntityEvent entityEvent) throws Exception {
			      return entityEvent.getCompanyId();
			   }
			})
		.window(TumblingEventTimeWindows.of(Time.seconds(5)))
		.reduce(new AmountAggregator(), new AlertWindowFunction())
		.addSink(new AlertSink());
		
		
		//Business Metrics - Third pipeline - Metric computation
		
		entityEventStream
		.flatMap(new AvroToEntity())
		.keyBy(new KeySelector<EntityEvent, Object>() {
			   @Override
			   public Object getKey(EntityEvent entityEvent) throws Exception {
			      return entityEvent.getCompanyId();
			   }
			})
		.keyBy(new KeySelector<EntityEvent, Object>() {
			   @Override
			   public Object getKey(EntityEvent entityEvent) throws Exception {
			      return entityEvent.getEntity().getTxTypeId();
			   }
			})
		.window(TumblingEventTimeWindows.of(Time.seconds(5)))
		.fold(null, new BusinessMetricAggregator())
		.addSink(new BusinessMetricSink());
		
		//For Debugging
		entityEventStream.print();
		
		//Trace - Fourth pipeline - for log analysis
		entityEventStream.addSink(new TraceSink());
		
		//Operational Metrics - Fifth pipeline - e.g. avg delay
		MetricSource metricsSource = new MetricSource();
		PostgresSink.setMetricsSource(metricsSource);
		AllWindowedStream<Metric, TimeWindow> operationalStream = env
	 		.addSource(metricsSource)
	 		.timeWindowAll(Time.seconds(10));
		operationalStream
			.max("timetaken")
			.print("rtdpp-OperationalMetric-max-time-taken");
		operationalStream
			.max("timetaken")
	 		.addSink(new OperationalMetricSink("max(ms)", 10));
		operationalStream
			.min("timetaken")
			.addSink(new OperationalMetricSink("min(ms)", 10));
		operationalStream
			.sum("count")
			.addSink(new OperationalMetricSink("count/sec", 10));

		// env.setParallelism(1);
		env.enableCheckpointing(1000);

		env.execute(KafkaConsumer.class.getCanonicalName());
	}

}
