package com.stream.accounting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.Record;

public class EntityEvent {
	
	private Entity entity;
	public enum EVENT_TYPE{INSERT, UPDATE, DELETE, INVALID};
	private EVENT_TYPE eventType = EVENT_TYPE.INVALID;
	private GenericData.Record kafkaRecord;
	private HashMap<String, Object> properties = new HashMap<>();
	/**
	 * @return the properties
	 */
	public String getProperties() {
		try {
			return toString(properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(String properties) {
		try {
			this.properties = (HashMap<String, Object>) fromString(properties);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private int count = 0;
	
	
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	public EntityEvent() {
		
	}
	
	public EntityEvent(GenericData.Record kafkaRecord) {
		this.kafkaRecord = kafkaRecord;
		
	}

	public EntityEvent(Entity entity, EVENT_TYPE eventType) {
		this.entity = entity;
		this.eventType = eventType;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public EVENT_TYPE getEventType() {
		return eventType;
	}
	public void setEventType(EVENT_TYPE eventType) {
		this.eventType = eventType;
	}

	public Record getKafkaRecord() {
		return kafkaRecord;
	}
	
	public void setKafkaRecord(Record kafkaRecord) {
		this.kafkaRecord = kafkaRecord;
	}

	public void setProperty(String flinkTid, Object value) {
		properties.put(flinkTid, value);	
	}
	
	public Object getProperty(String key) {
		return properties.get(key);
	}

	public void incrementCount() {
		count ++;
	}
	
	public int getCount() {
		return count;
	}
	
	public String getCompanyId() {
		return entity.getCompanyId();
	}

}
