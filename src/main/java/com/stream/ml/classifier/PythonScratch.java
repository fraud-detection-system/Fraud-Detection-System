package com.stream.ml.classifier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.List;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;


//TODO: delete it

public class PythonScratch {
	
	public static void main(String []args) throws FileNotFoundException, ScriptException {
		StringWriter writer = new StringWriter();
	    ScriptContext context = new SimpleScriptContext();
	    context.setWriter(writer);
	    
	    ScriptEngineFactory pythonFactory = null;
	    ScriptEngineManager manager = new ScriptEngineManager();
	    List<ScriptEngineFactory> factories = 
	    	      manager.getEngineFactories();
	    	  for (ScriptEngineFactory factory: factories) {
	    	    System.out.println("ScriptEngineFactory Info");
	    	    String engName = factory.getEngineName();
	    	    String engVersion = factory.getEngineVersion();
	    	    String langName = factory.getLanguageName();
	    	    String langVersion = factory.getLanguageVersion();
	    	    System.out.printf("\tScript Engine: %s (%s)\n", 
	    	        engName, engVersion);
	    	    List<String> engNames = factory.getNames();
	    	    for(String name: engNames) {
	    	    	if(name.equals("python")) {
	    	    		pythonFactory = factory;
	    	    	}
	    	      System.out.printf("\tEngine Alias: %s\n", name);
	    	    }
	    	    System.out.printf("\tLanguage: %s (%s)\n", 
	    	        langName, langVersion);
	    	  }

	    	  System.setProperty("python.import.site","false");
	       
	    ScriptEngine engine = manager.getEngineByName("python");
	    System.out.println("Engine got: "+engine);
	    engine = manager.getEngineByExtension("py");
	    System.out.println("Engine got: "+engine);
	    engine = manager.getEngineByMimeType("text/python");
	    System.out.println("Engine got: "+engine);
	    engine = pythonFactory.getScriptEngine();
	    System.out.println("Engine got: "+engine);
	    engine.eval(new FileReader("src/main/resources/hello.py"), context);
	    System.out.println( writer.toString().trim());
	}

}
