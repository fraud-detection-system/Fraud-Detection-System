package com.stream.accounting;

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.avro.generic.GenericRecord;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.util.serialization.KeyedDeserializationSchema;

import java.util.HashMap;
import java.util.Map;

public class KafkaGenericAvroDeserializationSchema
        implements KeyedDeserializationSchema<GenericRecord> {

    private final String registryUrl;
    private transient KafkaAvroDeserializer inner;

    public KafkaGenericAvroDeserializationSchema(String registryUrl) {
        this.registryUrl = registryUrl;
    }

    @Override
    public GenericRecord deserialize(
            byte[] messageKey, byte[] message, String topic, int partition, long offset) {
        checkInitialized();
        Object deserialize = null;
        try {
             deserialize = inner.deserialize(topic, message);
        } catch(Exception e){
            e.printStackTrace();
        }
        return (GenericRecord) deserialize;
    }

    @Override
    public boolean isEndOfStream(GenericRecord nextElement) {
        return false;
    }

    @Override
    public TypeInformation<GenericRecord> getProducedType() {
        return TypeExtractor.getForClass(GenericRecord.class);
    }

    private void checkInitialized() {
        if (inner == null) {
            Map<String, Object> props = new HashMap<>();
            props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
            props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, false);
            SchemaRegistryClient client =
                    new CachedSchemaRegistryClient(
                            registryUrl, AbstractKafkaAvroSerDeConfig.MAX_SCHEMAS_PER_SUBJECT_DEFAULT);
            inner = new KafkaAvroDeserializer(client, props);
        }
    }
}