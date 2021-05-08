package com.stream.fraud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stream.FraudDetectionSystem;
import com.stream.Workflow;
import com.stream.delivery.Monitoring;

public class OnlineFraudDetectionServer
{
	private final static Logger logger = LoggerFactory.getLogger(OnlineFraudDetectionServer.class.getName());
	
    public static void main( String[] args ) throws Exception
    {
        run(args);
    }
    
    /**
     * Start two pipelines - fraud detection and reference data.
     * 
     * @throws Exception
     */
    public static void run(String[] args) throws Exception {
        logger.info(" Starting!" );
        Monitoring.reset();
        FraudDetectionSystemConfiguration.run(args);
        FraudDetectionSystem fraudDetectionSystem = FraudDetectionSystemConfiguration.run(args);
        (new Thread(new Runnable() {

			@Override
			public void run() {
				Workflow workflow = new OnlineReferenceDataWorkflow();
		        try {
					workflow.run();
				} catch (Exception e) {
					logger.error("Error running reference data", e);
					System.exit(1);
				}
				
			}})).start();
        Workflow workflow = new OnlineFraudDetectionWorkflow(fraudDetectionSystem);
        workflow.run();
        logger.info(" Done");
    }
}
