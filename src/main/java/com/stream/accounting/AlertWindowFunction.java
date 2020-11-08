package com.stream.accounting;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class AlertWindowFunction 
extends ProcessWindowFunction<EntityEvent, Alert, Object, TimeWindow>{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void process(Object key,	
                    Context context,
                    Iterable<EntityEvent> entityEvents,
                    Collector<Alert> out) {
      EntityEvent entityEvent = entityEvents.iterator().next();
      
      //In a window aggregated amount is greater than 100000
      long amount = entityEvent.getEntity().getAmount();
      if(amount > 10000) {
    	  out.collect(new Alert(context.window().getStart(), entityEvent, "Aggregated amount fraud", "amount exceeded the max threshold and is ::"+ amount));
      }
      
    //In a window aggregated count is greater than 2
      long count = entityEvent.getCount();
      if(count > 2) {
    	  out.collect(new Alert(context.window().getStart(), entityEvent, "Aggregated count fraud", "amount exceeded the max threshold and is ::"+ amount));
      }
      
  }
}
