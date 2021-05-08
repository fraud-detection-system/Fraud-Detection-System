package com.stream.fraud;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stream.FraudDetectionSystem;

public class FraudDetectionSystemConfiguration extends AbstractScriptingEngine{
	private final static Logger logger = LoggerFactory.getLogger(
			   FraudDetectionSystemConfiguration.class.getName());
	
	public FraudDetectionSystemConfiguration() {
	}
	
public static FraudDetectionSystem run(String []args) throws FileNotFoundException {
		
		AbstractScriptingEngine engine = new AbstractScriptingEngine();
		HashMap inputParameters = new HashMap();
		FraudDetectionSystem fraudDetectionSystem =  new FraudDetectionSystem();
		inputParameters.put("fraudDetectionSystem", fraudDetectionSystem);
		for(String arg: args) {
			Object result = engine.run(arg, "result", inputParameters); //TODO: read from classpath
			if(result != null) {
				logger.info(result.toString());
			}
		}
		return fraudDetectionSystem;
	}

}
