package com.stream.fraud.operators;

import java.util.List;

import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

import com.stream.fraud.model.AccessEvent;

public class Selector extends ProcessFunction<AccessEvent, AccessEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2865413824127954515L;
	private List<String []> selectorAttributes;
	
	public Selector(List<String []> selectorAttributes) {
		this.selectorAttributes = selectorAttributes;
	}

	@Override
	public void processElement(AccessEvent accessEvent, ProcessFunction<AccessEvent, AccessEvent>.Context context,
			Collector<AccessEvent> collector) throws Exception {
		
		if(selectorAttributes != null && selectorAttributes.size() > 0) {
			for(String [] attributeNameElements :  selectorAttributes) {
				Object val = accessEvent.get(attributeNameElements);
				if(val != null && attributeNameElements[2].equals(val)) {
					collector.collect(accessEvent);
				}
			}
		}else {
			collector.collect(accessEvent);
		}
	}

}
