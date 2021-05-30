package com.stream.ml.classifier;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.jpmml.evaluator.TargetField;
import org.xml.sax.SAXException;

import com.stream.fraud.model.AccessEvent;

public class PMMLEvaluator {
	Evaluator evaluator;
	
	public PMMLEvaluator(String name) {
		
		try {
			evaluator = new LoadingModelEvaluatorBuilder()
			        .load(new File(name))
			        .build();
			evaluator.verify();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}	 
	}
	
	public boolean isAnomaly(AccessEvent event) {
		Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
        List<? extends InputField> inputFields = evaluator.getInputFields();
        for(InputField inputField : inputFields){
            FieldName inputName = inputField.getName();
            Object rawValue = event.getSubject().getAttribute(inputName.getValue());
            FieldValue inputValue = inputField.prepare(rawValue);
            arguments.put(inputName, inputValue);
        }
        Map<FieldName, ?> results = evaluator.evaluate(arguments);
        List<? extends TargetField> targetFields = evaluator.getTargetFields();
        for(TargetField targetField : targetFields){
            FieldName targetName = targetField.getName();
            Object targetValue = results.get(targetName);
            String result = targetValue.toString();
            System.out.println("Value is :  " + result);
            List<String> resultList = Arrays.asList(result.split(","));
            String resultVal = resultList.get(0).substring(1);
            System.out.println(resultVal);
            List<String> resultValList = Arrays.asList(resultVal.split("="));
            String fraudVal = resultValList.get(1);
            if(fraudVal.equals("1")){
                return true;
            }else {
            	return false;
            }
        }
        return true;
	}

	public static void main(String[] args) throws IOException, SAXException, JAXBException {
		 Evaluator evaluator = new LoadingModelEvaluatorBuilder()
	                .load(new File("src/main/resources/DecisionTree.pmml"))
	                .build();
		 // Performing the self-check
	        evaluator.verify();
	        Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
	        List<? extends InputField> inputFields = evaluator.getInputFields();
	        System.out.println("Input fields: " + inputFields);
	        for(InputField inputField : inputFields){
	            FieldName inputName = inputField.getName();
	            Object rawValue = 10;
	            // Transforming an arbitrary user-supplied value to a known-good PMML value
	            FieldValue inputValue = inputField.prepare(rawValue);
	            arguments.put(inputName, inputValue);
	        }
	        System.out.println("Done");
	        Map<FieldName, ?> results = evaluator.evaluate(arguments);
	        
	        List<? extends TargetField> targetFields = evaluator.getTargetFields();
	        for(TargetField targetField : targetFields){
	        	FieldName targetName = targetField.getName();

	        	Object targetValue = results.get(targetName);
	        	//targetValue.
	        	System.out.println("Value: "+targetValue);
	        }
	        
	        System.out.println("Results are" + evaluator.evaluate(arguments));

	}

}
