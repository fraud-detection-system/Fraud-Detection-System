package com.stream.fraud.operators;


import com.stream.fraud.model.FraudTxn;
import com.stream.telecom.operators.TraceSink;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;


public class FraudEventSink implements SinkFunction<FraudTxn> {
	String streamName = null;

	private TraceSink<FraudTxn> traceSink = new TraceSink<>();

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FraudEventSink(String streamName) {
		this.streamName = streamName;
    }

    @Override
    public void invoke(FraudTxn alert, Context context) throws Exception {
		traceSink.log(alert, FraudEventSink.class.getSimpleName(), "Stream Name="+this.streamName +", userId = "+alert.getUserId());
    }
}
