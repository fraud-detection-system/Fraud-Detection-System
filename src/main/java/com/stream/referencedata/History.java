package com.stream.referencedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.math.Arrays;

import com.stream.fraud.model.AccessEvent;

class CircularBuffer {
	Object []data;
	int index = 0;
	public CircularBuffer(int capacity) {
		this.data = new Object[capacity];
	}
	
	public void add(Object obj) {
		this.data [index%data.length] = obj;
		index++;
		//TODO: take care of index overflow
	}
	
	public Object[] getData() {
		Object [] result = new Object[data.length];
		int i=0;
		for(Object obj: data) {
			result[(data.length + (i-index%data.length) )%data.length] = obj;
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
	
	public List<Object> get(AccessEvent event) {
		ArrayList<Object> result = new ArrayList<>();
		for(String [] historyAttributeNameElements : historyAttributes) {
			Object val = event.get(historyAttributeNameElements);
			switch(historyAttributeNameElements[0]) {
			case "resource":
				CircularBuffer cb = resourceHistory.get(val);
				if(cb != null) {
					result.add(cb.getData());
				}
				break;
			case "subject" :
				cb = subjectHistory.get(val);
				if(cb != null) {
					result.add(cb.getData());
				}
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		
		CircularBuffer buf = new CircularBuffer(5);
		for(int i=0; i<100; i++) {
			buf.add(i);
			System.out.println(Arrays.toString(buf.getData()));
		}

	}

}
