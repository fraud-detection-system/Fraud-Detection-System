/**
 * 
 */
package com.stream.simulation;

import static com.stream.telecom.integration.LocalKafka.CLIENT_ID;
import static com.stream.telecom.integration.LocalKafka.KAFKA_BROKERS;
import static com.stream.telecom.integration.LocalKafka.MESSAGE_COUNT;
import static com.stream.telecom.integration.LocalKafka.ACCESS_EVENTS_IN_TOPIC_NAME;
import static com.stream.telecom.integration.LocalKafka.FRAUD_ACCESS_EVENTS_OUT_TOPIC_NAME;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.Action;
import com.stream.fraud.model.FraudAccessEvent;
import com.stream.fraud.model.Resource;
import com.stream.fraud.model.Subject;
import com.stream.fraud.model.TxnEvent;

/**
 * @author bdutt
 *
 */
public class Publisher {
	private final static Logger logger = LoggerFactory.getLogger(
			Publisher.class.getName());
	
	private static Producer producer = null;
	
	
	public static Producer createProducer() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
		return new KafkaProducer<>(props);
	}
	
	static AccessEvent generateAccess() {
		AccessEvent access = new AccessEvent();
		Subject subject = new Subject();
		subject.setAttribute("id", 1234);
		Resource resource = new Resource();
		resource.setAttribute("id", 567);
		resource.setAttribute("type", "bankAccount");
		resource.setAttribute("bank", "abc");
		Action action = new Action();
		action.setAttribute("id", "readBalance");
		access.setAction(action);
		access.setResource(resource);
		access.setSubject(subject);
		return access;
	}
	
	static TxnEvent generateTxnEvent() {
		TxnEvent txnEvent = new TxnEvent();
		txnEvent.setAmount(10.0);
		txnEvent.setUserId("123");
		txnEvent.setHoursFromLastTxn(100);
		txnEvent.setMilesFromLastTxn(100);
		return txnEvent;
	}
	
	static void runProducer() {
		Producer producer = createProducer();
		ObjectMapper objectMapper = new ObjectMapper();

		for (int index = 0; index < MESSAGE_COUNT; index++) {
			ProducerRecord record =  null;

				
				//JsonNode  jsonNode = objectMapper.valueToTree(generateTxnEvent());
				JsonNode  jsonNode = objectMapper.valueToTree(generateAccess());
				
				record = new ProducerRecord<Long, JsonNode>(ACCESS_EVENTS_IN_TOPIC_NAME,
						jsonNode);
				
			
			try {
				RecordMetadata metadata = (RecordMetadata) producer.send(record).get();
				System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
						+ " with offset " + metadata.offset());
				Thread.sleep(5000);
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}
		}
	}
	
	public static void fireAccessEvent(AccessEvent accessEvent) {
		if (null == producer) {
			synchronized (Publisher.class) {
				if (null == producer) {
					producer = createProducer();
				}
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();

		ProducerRecord record = null;
		JsonNode jsonNode = objectMapper.valueToTree(accessEvent);

		record = new ProducerRecord<Long, JsonNode>(ACCESS_EVENTS_IN_TOPIC_NAME, jsonNode);

		try {
			RecordMetadata metadata = (RecordMetadata) producer.send(record).get();
			logger.info("Published AccessEvent - Record sent with key " + " to partition " + metadata.partition() + " with offset "
					+ metadata.offset() +", "+accessEvent);
		} catch (ExecutionException e) {
			logger.error("Error in sending record", e);
		} catch (InterruptedException e) {
			logger.error("Error in sending record", e);
		}
	}
	
	public static void fireFraudAccessEvent(FraudAccessEvent fraudAccessEvent) {
		if (null == producer) {
			synchronized (Publisher.class) {
				if (null == producer) {
					producer = createProducer();
				}
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();

		ProducerRecord record = null;
		JsonNode jsonNode = objectMapper.valueToTree(fraudAccessEvent);

		record = new ProducerRecord<Long, JsonNode>(FRAUD_ACCESS_EVENTS_OUT_TOPIC_NAME, jsonNode);

		try {
			RecordMetadata metadata = (RecordMetadata) producer.send(record).get();
			logger.info("Published FraudAccessEvent - Record sent with key " + " to partition " + metadata.partition() + " with offset "
					+ metadata.offset() +", "+fraudAccessEvent);
		} catch (ExecutionException e) {
			logger.error("Error in sending record", e);
		} catch (InterruptedException e) {
			logger.error("Error in sending record", e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		runProducer();
	}
	
}