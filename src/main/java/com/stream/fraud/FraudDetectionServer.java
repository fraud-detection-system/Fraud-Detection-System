package com.stream.fraud;

import java.io.File;
import java.util.ArrayList;

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
        
        ArrayList<FraudDetectionStreamProcessing> fraudDetectionSystemList = new ArrayList<>();
        
        File f = new File(args[0]);

        // For each pathname in the pathnames array
        for (String pathname : f.list()) {
           if(pathname.endsWith(".fdsp")) {
        	   logger.info("Starting Fraud Detection Stream Processing : "+pathname);
        	   fraudDetectionSystemList.add( FraudDetectionDataFlowConfiguration.run(new String[] {args[0] + File.separator+pathname}) );
           }
        }
        
        
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
        for(FraudDetectionStreamProcessing fraudDetectionSystem : fraudDetectionSystemList) {
        	(new Thread(new Runnable() {

    			@Override
    			public void run() {
    				StreamingDataFlow workflow = new FraudDetectionStreamingDataFlow(fraudDetectionSystem);
    		        try {
    					workflow.run();
    				} catch (Exception e) {
    					logger.error("Error running reference data", e);
    					System.exit(1);
    				}
    				
    			}})).start();
        }
        logger.info(" Done");
    }
}
