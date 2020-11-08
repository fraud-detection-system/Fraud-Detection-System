package com.stream.accounting;


import org.apache.flink.streaming.api.functions.sink.SinkFunction;


public class OperationalMetricSink implements SinkFunction<Metric> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String metricName;
	private int windowSizeInSec;

	public OperationalMetricSink(String metricName, int windowSizeInSec) {
		this.metricName = metricName;
		this.windowSizeInSec = windowSizeInSec;
    }

    @Override
    public void invoke(Metric  operationalMetric, Context context) throws Exception {
    	if(metricName.equals("count")) {
    		TraceSink.log(
            		operationalMetric.getEntityEvent(), 
            		OperationalMetricSink.class.getSimpleName(), 
            		metricName+"="+ (operationalMetric.getCount()*1.0/windowSizeInSec));  
    		return;
    	}
        TraceSink.log(
        		operationalMetric.getEntityEvent(), 
        		OperationalMetricSink.class.getSimpleName(), 
        		metricName+"="+ (operationalMetric.getTimetaken()/windowSizeInSec));        
    }
}
