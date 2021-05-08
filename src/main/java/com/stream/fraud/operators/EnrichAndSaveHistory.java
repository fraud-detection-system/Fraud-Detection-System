package com.stream.fraud.operators;

import java.util.Arrays;
import java.util.List;

import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

import com.stream.fraud.model.AccessEvent;
import com.stream.referencedata.History;

public class EnrichAndSaveHistory extends ProcessFunction<AccessEvent, AccessEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2865413824127954515L;
	
	transient History history = null;
	private List<String []> attributesToDiff = null;
	private List<String []> historyAttributes = null;
	
	public EnrichAndSaveHistory(List<String []> historyAttributes, List<String []> attributesToDiff) {
		this.historyAttributes = historyAttributes;
		history = new History(historyAttributes);
		this.attributesToDiff = attributesToDiff;
	}

	@Override
	public void processElement(AccessEvent accessEvent, ProcessFunction<AccessEvent, AccessEvent>.Context context,
			Collector<AccessEvent> collector) throws Exception {
		
		if(history == null) {
			history = new History(historyAttributes);
		}
		
		List<AccessEvent> eventHistory = history.get(accessEvent);
		
		if(eventHistory.size() > 0) {		
			AccessEvent lastEvent = eventHistory.get(eventHistory.size() - 1);

			for (String[] attributeNameElements : attributesToDiff) {
				Object lastVal = lastEvent.get(attributeNameElements);
				Object currentVal = accessEvent.get(attributeNameElements);
				
				// Compute diff and set diff
				Object diff = null;
				if (lastVal != null || currentVal != null) {
					if (lastVal == null || currentVal == null) {
						diff = Integer.MAX_VALUE;
					} else {
						try {
							diff = Integer.parseInt(currentVal.toString()) - Integer.parseInt(lastVal.toString());
						}catch(NumberFormatException e) {
							//This is not a number
							//We may have to use different diffing methods, for now harcoding it here
							if(lastVal.equals(currentVal)) {
								diff = 0;
							}else {
								diff = 100000;
							}
						}
						
					}
				}
				if (diff != null) {
					accessEvent.set(attributeNameElements, 3, diff);
				}
			}
		}
		
		history.save(accessEvent);
		collector.collect(accessEvent);

	}

}
