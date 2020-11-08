package com.stream.accounting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Base64;
import java.util.Map;

import org.apache.avro.generic.GenericData;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import com.google.gson.Gson;

public class AvroToEntity implements FlatMapFunction<EntityEvent, EntityEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ENTITY_KEY = "entity-key";

	@Override
	public void flatMap(EntityEvent entityEvent, Collector<EntityEvent> collector) throws Exception {
		Entity entity = AvroToEntity.transform(entityEvent.getKafkaRecord());
		if (entity != null) {
			entityEvent.setEntity(entity);
			collector.collect(entityEvent);
		}
	}

	public static EntityEvent getEntityEvent(GenericData.Record record) {
		Object obj = record.get(ENTITY_KEY);
		if (null == obj) {
			return null;
		}
		try {
			return (EntityEvent) fromString((String) obj);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private static Object fromString(String s) throws IOException, ClassNotFoundException {
		byte[] data = Base64.getDecoder().decode(s);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return o;
	}

	/** Write the object to a Base64 string. */
	private static String toString(Serializable o) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.close();
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}

	private static Entity transform(GenericData.Record record) {
		Entity entity = new Entity();

		Object payload = record.get("payload");

		if (payload == null) {
			// Huh - don't understand this record
			System.out.println("Skip, no payload : " + payload);
			return null;
		}

		org.apache.avro.generic.GenericData.Record payloadRecord = (org.apache.avro.generic.GenericData.Record) payload;
		Object afterRecord = payloadRecord.get("afterrecord");

		if (afterRecord == null) {
			// Huh - don't understand this record
			System.out.println("Skip, no afterrecord : " + payload);
			return null;
		}

		org.apache.avro.generic.GenericData.Record afterRecordRecord = (org.apache.avro.generic.GenericData.Record) afterRecord;

		Object companyId = afterRecordRecord.get("COMPANY_ID");
		Object entityId = afterRecordRecord.get("RECUR_DATA_ID");
		Object data = afterRecord;
		if (companyId == null || entityId == null) {
			if (companyId != null) {
				// try one more time to get entity id
				entityId = afterRecordRecord.get(1);
			}
			if (entityId == null) {
				System.out.println("Skip, null companyId or null entityId : " + payload);
				return null;
			}
		}
		if (companyId.toString().contains("'") || data.toString().contains("'") || entityId.toString().contains("'")) {
			System.out.println("Skip, found single quotes : " + payload);
			return null;
		}
		entity.setCompanyId(companyId.toString());
		entity.setData(data.toString());
		entity.setEntityId(entityId.toString());
		parseTransactionTypeIdAndAmount(entity);

		return entity;
	}

	private static void parseTransactionTypeIdAndAmount(Entity entity) {
		try {
			Gson gson = new Gson();
			if (entity != null) {
				String data = entity.getData();
				Map<String, String> dataMap = gson.fromJson(new StringReader(data), Map.class);
				String TxnTypeId = dataMap.get("TX_TYPE_ID");
				String amountVal = dataMap.get("AMOUNT");
				if (TxnTypeId == "4" || amountVal != null) {
					if (amountVal != null) {
						entity.setAmount(Long.parseLong(amountVal));
						entity.setTxTypeId(Integer.parseInt(TxnTypeId));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Not able to get values :: " + e.getMessage());
		}

	}

}
