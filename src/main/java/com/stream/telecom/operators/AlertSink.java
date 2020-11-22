package com.stream.telecom.operators;


import org.apache.flink.streaming.api.functions.sink.SinkFunction;


public class AlertSink implements SinkFunction<TelecomUsageAlert> {
	String streamName = null;

	private TraceSink<TelecomUsageAlert> traceSink = new TraceSink<>();
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlertSink(String streamName) {
		this.streamName = streamName;
    }

    @Override
    public void invoke(TelecomUsageAlert alert, Context context) throws Exception {
		traceSink.log(alert, AlertSink.class.getSimpleName(), "Stream Name="+this.streamName +", Start time = "+alert.getStartTime()+ ", name = "+ alert.getName()+ ", detail = "+ alert.getDetail());
    }
}
