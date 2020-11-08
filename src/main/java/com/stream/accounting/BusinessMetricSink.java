package com.stream.accounting;


import org.apache.flink.streaming.api.functions.sink.SinkFunction;


public class BusinessMetricSink implements SinkFunction<BusinessMetric> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessMetricSink() {
    }

    @Override
    public void invoke(BusinessMetric businessMetric, Context context) throws Exception {
        TraceSink.log(null, BusinessMetricSink.class.getSimpleName(), "name="+businessMetric.getName()+", value=" + businessMetric.getLongValue());        
    }
}
