package com.stream.fraud.operators;


import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stream.fraud.model.Entity;
import com.stream.referencedata.ReferenceData;


public class EntitySink implements SinkFunction<Entity> {
	private final static Logger logger = LoggerFactory.getLogger(EntitySink.class.getName());
	String streamName = null;

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public EntitySink(String streamName) {
		this.streamName = streamName;
    }

    @Override
    public void invoke(Entity entity, Context context) throws Exception {
		logger.info(EntitySink.class.getSimpleName(), "Stream Name="+this.streamName +", Reference data!!! = "+entity.toString());
		ReferenceData.getReferenceData().save(entity);
    }
}
