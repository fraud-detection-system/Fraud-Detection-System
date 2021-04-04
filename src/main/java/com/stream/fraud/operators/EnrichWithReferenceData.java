package com.stream.fraud.operators;

import java.util.Map.Entry;

import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.Entity;
import com.stream.referencedata.ReferenceData;

public class EnrichWithReferenceData extends ProcessFunction<AccessEvent, AccessEvent>{
	transient ReferenceData referenceData = null;

	@Override
	public void processElement(AccessEvent accessEvent, ProcessFunction<AccessEvent, AccessEvent>.Context context,
			Collector<AccessEvent> collector) throws Exception {
		String id = (String)accessEvent.getSubject().getAttribute("id");
		if(id != null) {
			if(referenceData == null) {
				referenceData = new ReferenceData();
			}
			Entity entity = referenceData.find("denyUsers", id);
			if(entity != null) {
				accessEvent.getSubject().setAttribute("denyUser", "true");
				for(Entry<String, Object> entry: entity.getAttributes().entrySet()) {
					if(!accessEvent.getSubject().getAttributes().containsKey(entry.getKey())) {
						accessEvent.getSubject().setAttribute(entry.getKey(), entry.getValue()); 
					}
				}
				
			}
		}
		collector.collect(accessEvent);
		
	}

}
