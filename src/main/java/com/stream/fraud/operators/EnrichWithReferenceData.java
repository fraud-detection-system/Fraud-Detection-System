package com.stream.fraud.operators;

import java.util.Map.Entry;

import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.AttributeContainer;
import com.stream.fraud.model.Entity;
import com.stream.referencedata.ReferenceData;

public class EnrichWithReferenceData extends ProcessFunction<AccessEvent, AccessEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2865413824127954515L;

	@Override
	public void processElement(AccessEvent accessEvent, ProcessFunction<AccessEvent, AccessEvent>.Context context,
			Collector<AccessEvent> collector) throws Exception {
		ReferenceData referenceData = ReferenceData.getReferenceData();

		String id = (String) accessEvent.getSubject().getAttribute("id");
		Entity entity = null;
		if (id != null) {
			entity = referenceData.find("denyUsers", id);
			if (entity != null) {
				fillAttributes(entity, accessEvent.getSubject());
			}

			entity = referenceData.find("user", id);
			if (entity != null) {
				fillAttributes(entity, accessEvent.getSubject());
			}

			entity = referenceData.find("userConfiguration", id);
			if (entity != null) {
				fillAttributes(entity, accessEvent.getSubject());
			}
		}

		Object accountId = accessEvent.getResource().getAttribute("accountId");
		if (accountId != null) {
			entity = referenceData.find("account", (String) accountId);
			if (entity != null) {
				fillAttributes(entity, accessEvent.getResource());
			}
		}

		Object ipAddress = accessEvent.getSubject().getAttribute("IPAddress");
		if (ipAddress != null) {
			entity = referenceData.find("denyIPAddress", (String) ipAddress);
			if (entity != null) {
				fillAttributes(entity, accessEvent.getResource());
			}
		}
		collector.collect(accessEvent);

	}

	private void fillAttributes(AttributeContainer from, AttributeContainer to) {
		for (Entry<String, Object> entry : from.getAttributes().entrySet()) {
			if (!to.getAttributes().containsKey(entry.getKey())) {
				to.setAttribute(entry.getKey(), entry.getValue());
			}
		}
	}

}
