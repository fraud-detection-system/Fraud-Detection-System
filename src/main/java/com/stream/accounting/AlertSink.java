package com.stream.accounting;


import org.apache.flink.streaming.api.functions.sink.SinkFunction;


public class AlertSink implements SinkFunction<Alert> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlertSink() {
    }

    @Override
    public void invoke(Alert alert, Context context) throws Exception {
    	TraceSink.log(alert.getEntityEvent(), AlertSink.class.getSimpleName(), "Start time = "+alert.getStartTime()+ ", name = "+ alert.getName()+ ", detail = "+ alert.getDetail()); 
    }
}
