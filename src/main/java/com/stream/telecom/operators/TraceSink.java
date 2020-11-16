package com.stream.telecom.operators;


import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import com.stream.telecom.TelecomSystem;


public class TraceSink implements SinkFunction<TelecomUsageAlert> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TraceSink() {
    }

    @Override
    public void invoke(TelecomUsageAlert Alert, Context context) throws Exception {
    	log(Alert, TraceSink.class.getSimpleName());
    }
    
    public static void log(TelecomUsageAlert Alert, String logSource) {
    	log(Alert, logSource, null);
    	
    }

	public static void log(TelecomUsageAlert Alert, String logSource, String msg) {
		StringBuffer sb = new StringBuffer();
    	sb
    	   .append("--------     system=")
    	   .append(TelecomSystem.SYSTEM_NAME)
    	   .append(",")
    	   .append("src=")
    	   .append(logSource)
    	   .append(",");
    	if(Alert != null) {
    	   sb//.append("flink-tid=").append(Alert.getProperty(GenerateFlinkTid.FLINK_TID))
    	   .append(",")
    	   //.append("time-spent(ms)=").append(System.currentTimeMillis() - (Long)Alert.getProperty(GenerateFlinkTimestamp.FLINK_TIMESTAMP))
    	   .append(",")
    	   .append("Alert=").append(Alert);
    	}
    	if(msg != null) {
    		sb
    			.append(",")
    			.append(msg);
    	}
    	
    	System.out.println(sb.toString()); 
		
	}
}
