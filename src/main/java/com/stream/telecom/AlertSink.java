package com.stream.telecom;


import org.apache.flink.streaming.api.functions.sink.SinkFunction;


public class AlertSink implements SinkFunction<TelecomUsageAlert> {
	String streamName = null;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlertSink(String streamName) {
		this.streamName = streamName;
    }

    @Override
    public void invoke(TelecomUsageAlert alert, Context context) throws Exception {
    	TraceSink.log(alert, AlertSink.class.getSimpleName(), "Stream Name="+this.streamName +", Start time = "+alert.getStartTime()+ ", name = "+ alert.getName()+ ", detail = "+ alert.getDetail()); 
    }
}
