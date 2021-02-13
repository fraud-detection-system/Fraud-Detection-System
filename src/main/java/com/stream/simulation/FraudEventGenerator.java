/**
 *
 */
package com.stream.simulation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vincentrussell.json.datagenerator.JsonDataGeneratorException;
import com.github.vincentrussell.json.datagenerator.impl.JsonDataGeneratorImpl;

/**
 * @author anagaraj
 *
 */
public class FraudEventGenerator {

    //private static final String eventGeneratorFile = "/Users/anagaraj/Downloads/streaming-with-flink-main/src/main/resources/txnEventGenerator.json";
    private static final String eventGeneratorFile = "/Users/bdutt/dev/bds/flink/streaming-with-flink/src/main/resources/txnEventGenerator.json";


//    public static Producer createProducer() {
//        Properties props = new Properties();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, LocalKafka.KAFKA_BROKERS);
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, LocalKafka.CLIENT_ID);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
//
//        return new KafkaProducer<>(props);
//    }

    static void runProducer() {
        //Producer producer = createProducer();
        ObjectMapper objectMapper = new ObjectMapper();

        for (int index = 0; index < 500; index++) {
            //ProducerRecord record = null;


            try {
                JsonDataGeneratorImpl parser = new JsonDataGeneratorImpl();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                parser.generateTestDataJson(new File(eventGeneratorFile), byteArrayOutputStream);
                JsonNode jsonNode = objectMapper.readTree(byteArrayOutputStream.toString("UTF-8").getBytes());
                System.out.print(jsonNode.get("userId") + " " + jsonNode.get("amount") + " " + jsonNode.get("milesFromLastTxn") + " " + jsonNode.get("hoursFromLastTxn") );//                record = new ProducerRecord<Long, JsonNode>(LocalKafka.TOPIC_NAME, jsonNode);
                System.out.println(" "+1.0);
//
//                RecordMetadata metadata = (RecordMetadata) producer.send(record).get();
//                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
//                        + " with offset " + metadata.offset());
                //Thread.sleep(5000);
//            } catch (ExecutionException e) {
//                System.out.println("Error in sending record");
//                System.out.println(e);
//            } catch (InterruptedException e) {
//                System.out.println("Error in sending record");
//                System.out.println(e);
            }
            catch (JsonDataGeneratorException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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