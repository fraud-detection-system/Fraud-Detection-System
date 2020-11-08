package com.stream.accounting;


import org.apache.avro.generic.GenericData;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;


public class PostgresSink implements SinkFunction<EntityEvent> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	transient PostgresDB db = new PostgresDB();
	private static MetricSource metricsSource = null;

	public PostgresSink() {
    }

    @Override
    public void invoke(EntityEvent entityEvent, Context context) throws Exception {
    	if(db == null) {
    		db = new PostgresDB();
    	}

    	Long timeTaken = null;
    	try {
    		timeTaken = context.currentProcessingTime() - context.timestamp();
    	}catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    	switch(entityEvent.getEventType()) {
    	case INSERT:
    		db.insert(entityEvent.getEntity());
    		break;
    	default:		
    		break;
    	}
        System.out.println(entityEvent.getEntity());
        metricsSource.streamMetric(new Metric(timeTaken, entityEvent));
    }

	public static void setMetricsSource(MetricSource metricsSource) {
		PostgresSink.metricsSource = metricsSource;
		
	}
}
