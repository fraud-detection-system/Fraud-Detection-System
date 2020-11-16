package com.stream.telecom.integration;

import java.util.Collections;
import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class LocalKafka {
	
	public static String KAFKA_BROKERS = "localhost:9092";
	
	public static Integer MESSAGE_COUNT=1000;
	
	public static String CLIENT_ID="client1";
	
	public static String TOPIC_NAME="telecom-callrecords";
	
	public static String GROUP_ID_CONFIG="consumerGroup10";
	
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
	
	public static String OFFSET_RESET_LATEST="latest";
	
	public static String OFFSET_RESET_EARLIER="earliest";
	
	public static Integer MAX_POLL_RECORDS=1;
	
	@SuppressWarnings("serial")
	public SourceFunction<String> getSource() throws Exception {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer(TOPIC_NAME,
				// new
				// KafkaGenericAvroDeserializationSchema("https://schema-registry.bdpd.ppd.a.intuit.com:9090"),
				new SimpleStringSchema(), getProps());

		// consumer.setStartFromEarliest();
		consumer.setStartFromLatest();
		
		return consumer;
	}
	
	public SourceFunction<ObjectNode> getSourceAsJson() throws Exception {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		FlinkKafkaConsumer<ObjectNode> consumer = new FlinkKafkaConsumer(TOPIC_NAME,
				new JSONKeyValueDeserializationSchema(true), getProps());

		// consumer.setStartFromEarliest();
		consumer.setStartFromLatest();

		return consumer;
	}
	
	private static Properties getProps() {
		final Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, MAX_POLL_RECORDS);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OFFSET_RESET_EARLIER);
		return props;
	}
	
	public static Consumer<Long, String> createConsumer() {
		

		final Consumer<Long, String> consumer = new KafkaConsumer<>(getProps());
		consumer.subscribe(Collections.singletonList(TOPIC_NAME));
		return consumer;
	}
	
	static void runConsumer() {
		Consumer<Long, String> consumer = createConsumer();

		int noMessageToFetch = 0;

		while (true) {
			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
			if (consumerRecords.count() == 0) {
				noMessageToFetch++;
				if (noMessageToFetch > MAX_NO_MESSAGE_FOUND_COUNT)
					break;
				else
					continue;
			}

			consumerRecords.forEach(record -> {
				System.out.println("Record Key " + record.key());
				System.out.println("Record value " + record.value());
				System.out.println("Record partition " + record.partition());
				System.out.println("Record offset " + record.offset());
			});
			consumer.commitAsync();
		}
		consumer.close();
	}
	
	public static void main(String []args) {
		runConsumer();
	}

}
