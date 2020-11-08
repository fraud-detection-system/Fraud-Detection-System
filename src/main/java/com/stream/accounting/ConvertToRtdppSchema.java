package com.stream.accounting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

public class ConvertToRtdppSchema implements FlatMapFunction<GenericData.Record, GenericData.Record>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Schema schema = null;
	private static final String KAFKA_RECORD_KEY = "kafka-record";
	
	private static Schema getSchema (Schema kafkaRecordSchema) {
		if(schema == null) {

		    //create a new "empty" schema
		    //public static Schema createRecord(String name, String doc, String namespace, boolean isError) {

		    Schema newSchema = Schema.createRecord("rtdpp_schema", "info", "com.bds.datastreaming", false);
		    
		    //this here is just to show how to create an optional string, its a union of null and string types
	
		    String[] sArray = {KAFKA_RECORD_KEY, GenerateFlinkTid.FLINK_TID, GenerateFlinkTimestamp.FLINK_TIMESTAMP, AvroToEntity.ENTITY_KEY};

		    List<Schema.Field> new_list = Arrays.asList(

		       new Schema.Field( KAFKA_RECORD_KEY, 
		        		Schema.createUnion(Arrays.asList(
		        			Schema.create(Schema.Type.NULL),
		        			kafkaRecordSchema
		        		) ),
		        		KAFKA_RECORD_KEY,
		        		null)
		       ,
		       
		       new Schema.Field( GenerateFlinkTid.FLINK_TID, 
		        		Schema.createUnion(Arrays.asList(
		        			Schema.create(Schema.Type.NULL),
		        			Schema.create(Schema.Type.STRING)
		        		) ),
		        		GenerateFlinkTid.FLINK_TID,
		        		null)
		       
		       ,
		       
		       new Schema.Field( GenerateFlinkTimestamp.FLINK_TIMESTAMP, 
		        		Schema.createUnion(Arrays.asList(
		        			Schema.create(Schema.Type.NULL),
		        			Schema.create(Schema.Type.LONG)
		        		) ),
		        		GenerateFlinkTimestamp.FLINK_TIMESTAMP,
		        		null)
		       
		       ,
		       
		       new Schema.Field( AvroToEntity.ENTITY_KEY, 
		        		Schema.createUnion(Arrays.asList(
		        			Schema.create(Schema.Type.NULL),
		        			Schema.create(Schema.Type.MAP)
		        		) ),
		        		AvroToEntity.ENTITY_KEY,
		        		null)
		        
		
			);

		    newSchema.setFields(new_list);
		    schema = newSchema;
		}
		return schema;
		
	}

	@Override
	public void flatMap(GenericData.Record record, Collector<GenericData.Record> collector) throws Exception {
		
		GenericData.Record rtdppRecord = new GenericData.Record(getSchema(record.getSchema()));
		rtdppRecord.put(KAFKA_RECORD_KEY, record);	
		collector.collect(rtdppRecord);
	}
}
