package com.stream.fraud;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractScriptingEngine {

	private final static ScriptEngineManager manager = new ScriptEngineManager();
	private final static Logger logger = LoggerFactory.getLogger(AbstractScriptingEngine.class.getName());

	public AbstractScriptingEngine() {

	}

	private ScriptEngine getEngine(String engineName) {
		System.setProperty("python.import.site", "false");

		final ScriptEngine engine = manager.getEngineByName(engineName);
		if (engine != null) {
			return engine;
		}

		ScriptEngineFactory engineFactory = null;
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = manager.getEngineFactories();
		for (ScriptEngineFactory factory : factories) {
			List<String> engNames = factory.getNames();
			for (String name : engNames) {
				if (name.equals(engineName)) {
					engineFactory = factory;
				}
			}
		}
		
		if(null!= engineFactory) {
			return engineFactory.getScriptEngine();
		}
		
		return null;
	}

	public Object run(String jsFileName, final String nameOfOutput, final Map<String, Object> inputParameters)
			throws FileNotFoundException {
		return run(jsFileName, nameOfOutput, inputParameters, "js");
	}

	public Object run(String jsFileName, final String nameOfOutput, final Map<String, Object> inputParameters,
			String engineName) throws FileNotFoundException {
		Object result = null;

		inputParameters.put("logger", logger);

		for (final ScriptEngineFactory scriptEngine : manager.getEngineFactories()) {
			logger.info(scriptEngine.getEngineName() + " (" + scriptEngine.getEngineVersion() + ")");
			logger.info(
					"\tLanguage: " + scriptEngine.getLanguageName() + "(" + scriptEngine.getLanguageVersion() + ")");
			logger.info("\tCommon Names/Aliases: ");
			for (final String engineAlias : scriptEngine.getNames()) {
				logger.info(engineAlias + " ");
			}
		}
		logger.info("--------------------------------------------");
		logger.info("            Starting simulation");
		logger.info("--------------------------------------------");

		final ScriptEngine engine = getEngine(engineName);
		try {
			if (inputParameters != null) {
				for (final Map.Entry<String, Object> parameter : inputParameters.entrySet()) {
					engine.put(parameter.getKey(), parameter.getValue());
				}
			}
			logger.info("Running simulation script : " + jsFileName);
			engine.eval(new FileReader(jsFileName));
			result = engine.getBindings(ScriptContext.GLOBAL_SCOPE).get(nameOfOutput);
			// result = engine.get(nameOfOutput);
		} catch (ScriptException scriptException) {
			logger.error("ScriptException encountered trying to write arbitrary JavaScript '" + jsFileName + "': "
					+ scriptException.toString());
			System.exit(1);
		}
		return result;
	}

	public static void main(String[] args) throws FileNotFoundException {

		AbstractScriptingEngine simulation = new AbstractScriptingEngine();
		HashMap inputParameters = new HashMap();
		for (String arg : args) {
			Object result = simulation.run(arg, "result", inputParameters); // TODO: read from classpath
			logger.info(result.toString());
		}
	}

}
