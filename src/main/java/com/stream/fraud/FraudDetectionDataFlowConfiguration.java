package com.stream.fraud;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stream.FraudDetectionStreamProcessing;

public class FraudDetectionDataFlowConfiguration extends AbstractScriptingEngine{
	private final static Logger logger = LoggerFactory.getLogger(
			   FraudDetectionDataFlowConfiguration.class.getName());
	
	public FraudDetectionDataFlowConfiguration() {
	}
	
public static FraudDetectionStreamProcessing run(String []args) throws FileNotFoundException {
		
		AbstractScriptingEngine engine = new AbstractScriptingEngine();
		HashMap inputParameters = new HashMap();
		FraudDetectionStreamProcessing fraudDetectionSystem =  new FraudDetectionStreamProcessing();
		inputParameters.put("fraudDetectionStreamProcessing", fraudDetectionSystem);
		for(String arg: args) {
			Object result = engine.run(arg, "result", inputParameters); //TODO: read from classpath
			if(result != null) {
				logger.info(result.toString());
			}
		}
		return fraudDetectionSystem;
	}

}
