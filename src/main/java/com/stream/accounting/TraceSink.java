package com.stream.accounting;


import org.apache.flink.streaming.api.functions.sink.SinkFunction;


public class TraceSink implements SinkFunction<EntityEvent> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String PROJECT_NAME ="rtdpp";

	public TraceSink() {
    }

    @Override
    public void invoke(EntityEvent entityEvent, Context context) throws Exception {
    	log(entityEvent, TraceSink.class.getSimpleName());
    }
    
    public static void log(EntityEvent entityEvent, String logSource) {
    	log(entityEvent, logSource, null);
    	
    }

	public static void log(EntityEvent entityEvent, String logSource, String msg) {
		StringBuffer sb = new StringBuffer();
    	sb
    	   .append("project=")
    	   .append(PROJECT_NAME)
    	   .append(",")
    	   .append("src=")
    	   .append(logSource)
    	   .append(",");
    	if(entityEvent != null) {
    	   sb.append("flink-tid=").append(entityEvent.getProperty(GenerateFlinkTid.FLINK_TID))
    	   .append(",")
    	   .append("time-spent(ms)=").append(System.currentTimeMillis() - (Long)entityEvent.getProperty(GenerateFlinkTimestamp.FLINK_TIMESTAMP))
    	   .append(",")
    	   .append("entityEvent=").append(entityEvent);
    	}
    	if(msg != null) {
    		sb
    			.append(",")
    			.append(msg);
    	}
    	
    	System.out.println(sb.toString()); 
		
	}
}
