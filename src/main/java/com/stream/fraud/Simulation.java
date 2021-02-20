package com.stream.fraud;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simulation {
	
	private final static ScriptEngineManager manager = new ScriptEngineManager();
	private final static Logger logger = LoggerFactory.getLogger(
			   Simulation.class.getName());
	
	public Simulation() {
		
	}
	
	public Object run(String jsFileName, final String nameOfOutput,
		      final Map<String, Object> inputParameters) throws FileNotFoundException {
		Object result = null;
	      final ScriptEngine engine = manager.getEngineByName("js");
	      try
	      {
	         if (inputParameters != null)
	         {
	            for (final Map.Entry<String,Object> parameter :
	                 inputParameters.entrySet())
	            {
	               engine.put(parameter.getKey(), parameter.getValue());
	            }
	         }
	         logger.info("Running simulation script : "+jsFileName);
	         engine.eval(new FileReader(jsFileName)); 
	         result = engine.getBindings(ScriptContext.GLOBAL_SCOPE).get(nameOfOutput);
	         //result = engine.get(nameOfOutput);
	      }
	      catch (ScriptException scriptException)
	      {
	         logger.error(
	              "ScriptException encountered trying to write arbitrary JavaScript '"
	            + jsFileName + "': "
	            + scriptException.toString());
	      }
	      return result;
	}
	
	public static void main(String []args) throws FileNotFoundException {
		final ScriptEngineManager manager = new ScriptEngineManager();
		for (final ScriptEngineFactory scriptEngine : manager.getEngineFactories())
		{
		   logger.info(
		         scriptEngine.getEngineName() + " ("
		       + scriptEngine.getEngineVersion() + ")" );
		   logger.info(
		         "\tLanguage: " + scriptEngine.getLanguageName() + "("
		       + scriptEngine.getLanguageVersion() + ")" );
		   logger.info("\tCommon Names/Aliases: ");
		   for (final String engineAlias : scriptEngine.getNames())
		   {
			   logger.info(engineAlias + " ");
		   }
		}
		logger.info("--------------------------------------------");
		logger.info("            Starting simulation");
		logger.info("--------------------------------------------");
		
		Simulation simulation = new Simulation();
		HashMap inputParameters = new HashMap();
		inputParameters.put("logger", logger);
		for(String arg: args) {
			Object result = simulation.run(arg, "result", inputParameters); //TODO: read from classpath
			logger.info(result.toString());
		}
	}

}
