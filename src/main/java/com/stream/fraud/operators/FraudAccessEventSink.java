package com.stream.fraud.operators;


import com.stream.fraud.model.FraudAccessEvent;
import com.stream.telecom.operators.TraceSink;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;


public class FraudAccessEventSink implements SinkFunction<FraudAccessEvent> {
	String streamName = null;

	private TraceSink<FraudAccessEvent> traceSink = new TraceSink<>();

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FraudAccessEventSink(String streamName) {
		this.streamName = streamName;
    }

    @Override
    public void invoke(FraudAccessEvent alert, Context context) throws Exception {
		traceSink.log(alert, FraudAccessEventSink.class.getSimpleName(), "Stream Name="+this.streamName +", FRAUD!!! = "+alert.toString());
    }
}
