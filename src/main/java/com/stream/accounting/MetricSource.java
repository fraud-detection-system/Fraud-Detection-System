package com.stream.accounting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class MetricSource implements SourceFunction<Metric> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isCancelled = false;
	private static List<Metric> metrics = Collections.synchronizedList(new ArrayList<Metric>());

	@Override
	public void cancel() {
		isCancelled = true;
		
	}
	
	public void streamMetric (Metric metric) {
		metrics.add(metric);
		//System.out.println("BalaDutt: created a metric to stream");
	}

	@Override
	public void run(SourceContext<Metric> context) throws Exception {
		//System.out.println("BalaDutt: running metric to stream");
		while(!isCancelled) {
			List<Metric> localMetrics = metrics;
			synchronized(this) {
				metrics = Collections.synchronizedList(new ArrayList<Metric>());
			}
			for(Metric m: localMetrics) {
					//System.out.println("BalaDutt: Collected a metric to stream");
					context.collect(m);
			}
			//System.out.println("BalaDutt: sleeping metric to stream");
			Thread.sleep(1000);
		}
		
	}

}
