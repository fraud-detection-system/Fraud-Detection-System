package com.stream.accounting;

import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.avro.generic.GenericData;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

public class KafkaConsumer {
	
	private static final String BOOTSTRAP_SERVERS = ""; //TODO: put values here
	private static final String SSL_TRUSTSTORE_LOCATION = ""; //TODO: put values here
	private static final String SCHEMA_REGISTRY = ""; //TODO: put values here

	@SuppressWarnings("serial")
	public SourceFunction<GenericData.Record> getSource() throws Exception {
		

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS);
		// only required for Kafka 0.8
		properties.setProperty("group.id", UUID.randomUUID().toString());
		properties.setProperty("ssl.truststore.password", "mypass");
		properties.setProperty("security.protocol", "SSL");
		properties.setProperty("ssl.enabled.protocol", "TLSv1.2");

		properties.setProperty("ssl.truststore.location", SSL_TRUSTSTORE_LOCATION);
		properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.setProperty("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");

		@SuppressWarnings({ "unchecked", "rawtypes" })
		FlinkKafkaConsumer<GenericData.Record> consumer = new FlinkKafkaConsumer(
				Pattern.compile("*_CDC_QA.*"),
				new KafkaGenericAvroDeserializationSchema(SCHEMA_REGISTRY),
				properties);
		//consumer.setStartFromEarliest();
		consumer.setStartFromLatest();
		
		return consumer;
	}

}