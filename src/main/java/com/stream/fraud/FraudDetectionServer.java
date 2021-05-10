package com.stream.fraud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stream.FraudDetectionStreamProcessing;
import com.stream.Server;
import com.stream.StreamingDataFlow;
import com.stream.delivery.Monitoring;

public class FraudDetectionServer extends Server
{
	private final static Logger logger = LoggerFactory.getLogger(FraudDetectionServer.class.getName());
	
    public static void main( String[] args ) throws Exception
    {
        (new FraudDetectionServer()).run(args);
    }
    
    /**
     * Start two pipelines - fraud detection and reference data.
     * 
     * @throws Exception
     */
    public void run(String[] args) throws Exception {
        logger.info(" Starting!" );
        Monitoring.reset();
        FraudDetectionStreamProcessing fraudDetectionSystem = FraudDetectionDataFlowConfiguration.run(args);
        (new Thread(new Runnable() {

			@Override
			public void run() {
				StreamingDataFlow workflow = new ReferenceDataStreamingDataFlow();
		        try {
					workflow.run();
				} catch (Exception e) {
					logger.error("Error running reference data", e);
					System.exit(1);
				}
				
			}})).start();
        StreamingDataFlow workflow = new FraudDetectionStreamingDataFlow(fraudDetectionSystem);
        workflow.run();
        logger.info(" Done");
    }
}
