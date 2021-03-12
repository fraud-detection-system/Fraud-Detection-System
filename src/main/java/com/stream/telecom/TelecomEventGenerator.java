/**
 * 
 */
package com.stream.telecom;

import static com.stream.integration.LocalKafka.*;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonSerializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stream.telecom.model.TelecomCallRecord;

/**
 * @author bdutt
 *
 */
public class TelecomEventGenerator {
	
	
	public static Producer createProducer() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		switch(TelecomSystem.messageType) {
		case JSON:
			props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
			break;
		case STRING:
			props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
			break;
		}
		
		return new KafkaProducer<>(props);
	}
	
	static void runProducer() {
		Producer producer = createProducer();
		ObjectMapper objectMapper = new ObjectMapper();

		for (int index = 0; index < MESSAGE_COUNT; index++) {
			ProducerRecord record =  null;
			switch(TelecomSystem.messageType) {
			case JSON:
				TelecomCallRecord telecomCallRecord = new TelecomCallRecord();
				telecomCallRecord.setFrom("007");
				telecomCallRecord.setFromCurrentLocation("London");
				telecomCallRecord.setTo("100");
				telecomCallRecord.setToCurrentLocation("Bengaluru");
				telecomCallRecord.setDurationInMillis(""+index);
				telecomCallRecord.setStartTime( ""+(new Date()).getTime());
				JsonNode  jsonNode = objectMapper.valueToTree(telecomCallRecord);
				
				record = new ProducerRecord<Long, JsonNode>(ACCESS_EVENTS_IN_TOPIC_NAME,
						jsonNode);
				break;
			case STRING:
				record = new ProducerRecord<Long, String>(ACCESS_EVENTS_IN_TOPIC_NAME,
						"{'type': 'Telecom Call Record', 'from' : '123', 'to' : '456', 'startTime': '" + index+"'");
				break;
			}
			
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		runProducer();
	}
	
}