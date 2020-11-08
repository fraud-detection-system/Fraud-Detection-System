package com.stream.accounting;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import com.stream.accounting.EntityEvent;

public class BusinessMetricWindowFunction 
extends ProcessWindowFunction<EntityEvent, BusinessMetric, Object, TimeWindow>{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void process(Object key,	
                    Context context,
                    Iterable<EntityEvent> entityEvents,
                    Collector<BusinessMetric> out) {
	
	for(EntityEvent entityEvent: entityEvents) {
		out.collect(new BusinessMetric("amount", entityEvent.getEntity().getAmount()));
	}
      
  }
}
