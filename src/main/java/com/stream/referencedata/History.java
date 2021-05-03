package com.stream.referencedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.math.Arrays;

import com.stream.fraud.model.AccessEvent;

class CircularBuffer {
	AccessEvent [] accessEvents;
	int index = 0;
	
	public CircularBuffer(int capacity) {
		this.accessEvents = new AccessEvent[capacity];
	}
	
	public void add(AccessEvent accessEvent) {
		this.accessEvents [index%accessEvents.length] = accessEvent;
		index++;
		//TODO: take care of index overflow
	}
	
	public AccessEvent[] getData() {
		AccessEvent [] result = new AccessEvent[accessEvents.length];
		int i=0;
		for(AccessEvent accessEvent: accessEvents) {
			result[(accessEvents.length + (i-index%accessEvents.length) )%accessEvents.length] = accessEvent;
			i++;
		}
		return result;
	}
}

public class History {
	
	private List<String []> historyAttributes = null;
	private Map<Object, CircularBuffer> resourceHistory = new HashMap<>();
	private Map<Object, CircularBuffer> subjectHistory = new HashMap<>();
	
	
	public History(List<String []> historyAttributes) {
		this.historyAttributes = historyAttributes;
	}
	
	public void save(AccessEvent event) {
		for(String [] historyAttributeNameElements : historyAttributes) {
			Object val = event.get(historyAttributeNameElements);
			switch(historyAttributeNameElements[0]) {
			case "resource":
				CircularBuffer cb = resourceHistory.get(val);
				if(cb == null) {
					cb = new CircularBuffer(Integer.parseInt(historyAttributeNameElements[2]));
					resourceHistory.put(val, cb);
				}
				cb.add(event);
				break;
			case "subject" :
				cb = subjectHistory.get(val);
				if(cb == null) {
					cb = new CircularBuffer(Integer.parseInt(historyAttributeNameElements[2]));
					resourceHistory.put(val, cb);
				}
				cb.add(event);
				break;
			}
			
		}
	}
	
	public List<AccessEvent> get(AccessEvent event) {
		ArrayList<AccessEvent> result = new ArrayList<>();
		for(String [] historyAttributeNameElements : historyAttributes) {
			Object val = event.get(historyAttributeNameElements);
			switch(historyAttributeNameElements[0]) {
			case "resource":
				CircularBuffer cb = resourceHistory.get(val);
				if(cb != null) {
					result.addAll(java.util.Arrays.asList(cb.getData()));
				}
				break;
			case "subject" :
				cb = subjectHistory.get(val);
				if(cb != null) {
					result.addAll(java.util.Arrays.asList(cb.getData()));
				}
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		
		CircularBuffer buf = new CircularBuffer(5);
		for(int i=0; i<100; i++) {
			AccessEvent accessEvent = new AccessEvent();
			accessEvent.getResource().setAttribute("id", i);
			buf.add(accessEvent);
			System.out.println(Arrays.toString(buf.getData()));
		}

	}

}
