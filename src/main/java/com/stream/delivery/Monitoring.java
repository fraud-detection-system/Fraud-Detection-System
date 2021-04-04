package com.stream.delivery;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.FraudAccessEvent;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;

public class Monitoring {
	static final CollectorRegistry registry = new CollectorRegistry();
	
	static final Map<String, Counter> counters = new HashMap<>();
	
	static final Counter accessEventsCounter = Counter.build()
	    	.name("access_events_total").help("Total number of access events.").register(registry);
	
	static final Counter fraudEventsCounter = Counter.build()
	    	.name("fraud_events_total").help("Total number of fraud events.").register(registry);
	
	static PushGateway pg = new PushGateway("127.0.0.1:9091");
	
	private static void run(AccessEvent accessEvent) {
		String prefix = "";
		if(accessEvent instanceof FraudAccessEvent) {
			prefix = "fraud_";
			fraudEventsCounter.inc();
		}else {
			accessEventsCounter.inc();
		}
		String resourceId = prefix+"resource_id_"+accessEvent.getResource().getAttribute("id");
		Counter counter = counters.get(resourceId);
		if(counter == null) {
			counter = Counter.build()
			    	.name(resourceId).help(resourceId).register(registry);
			counters.put(resourceId, counter);
		}
		counter.inc();
		
		String actionId = prefix+"action_id_"+accessEvent.getAction().getAttribute("id");
		counter = counters.get(actionId);
		if(counter == null) {
			counter = Counter.build()
			    	.name(actionId).help(actionId).register(registry);
			counters.put(actionId, counter);
		}
		counter.inc();
		
		try {
			pg.pushAdd(registry, "streaming-with-flink");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void register(FraudAccessEvent fraudAccessEvent) {
		run(fraudAccessEvent);
	}
	
	public static void register(AccessEvent accessEvent) {
		run(accessEvent);	
	}
	
	

}
