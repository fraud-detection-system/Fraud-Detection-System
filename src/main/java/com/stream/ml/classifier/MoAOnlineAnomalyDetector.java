package com.stream.ml.classifier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.minlog.Log;
import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.AttributeContainer;
import com.yahoo.labs.samoa.instances.Attribute;
import com.yahoo.labs.samoa.instances.DenseInstance;
import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.InstancesHeader;

import moa.classifiers.Classifier;
import moa.classifiers.trees.HoeffdingTree;

public class MoAOnlineAnomalyDetector extends OnlineAnomalyDetector{
	
	private final static Logger logger = LoggerFactory.getLogger(MoAOnlineAnomalyDetector.class.getName());

	private static final long serialVersionUID = 1L;

	private static final double THRESHOLD = 0.01;

    private static final String ANOMALY_COLUMN_NAME = "anomaly";

    public transient Classifier learner = null;

    private List<String[]> attributes;

    public MoAOnlineAnomalyDetector(List<String[]> attributes) {
    	super(attributes);
        this.attributes = attributes;
    }
    
    private int getOrdinal(String entity, String attribute, String val) {
    	ArrayList<String> list = new ArrayList<>();
    	list.add("null");
    	if (entity.equals("action") && attribute.equals("id")) {
			list.add("atmWithdrawal");
			list.add("onlineShopping");
			list.add("debit");
			list.add("logout");
			list.add("login");
			list.add("readBalance");
			list.add("transferMoney");
		} else if (entity.equals("resource") && attribute.equals("id")) {
			list.add("account");
		}else if (entity.equals("subject") && attribute.equals("id")) {
			
		}
    	return list.indexOf(val);
    }
    
    private InstancesHeader getHeader() {
		ArrayList<Attribute> moaAttributes = new ArrayList<Attribute>();
		for (String[] attribute : attributes) {
			if (attribute.length > 2) {
				if (attribute[2].equals("double")) {
					moaAttributes.add(new Attribute(attribute[0] + "." + attribute[1]));
				} else if (attribute[2].equals("categorical")) {
					ArrayList<String> list = new ArrayList<>();
					list.add("null");
					if (attribute[0].equals("action") && attribute[1].equals("id")) {
						list.add("atmWithdrawal");
						list.add("onlineShopping");
						list.add("debit");
						list.add("logout");
						list.add("login");
						list.add("readBalance");
						list.add("transferMoney");
					} else if (attribute[0].equals("resource") && attribute[1].equals("id")) {
						list.add("account");
					}
					moaAttributes.add(new Attribute(attribute[0] + "." + attribute[1], list));
				}
			}
		}
		ArrayList<String> classLabels = new ArrayList<String>();
		classLabels.add("normal");
		classLabels.add("anomaly");
		moaAttributes.add(new Attribute(ANOMALY_COLUMN_NAME, classLabels));
		InstancesHeader header = new InstancesHeader();
		header.setAttributes((Attribute[]) moaAttributes.toArray(new Attribute[0]));
		header.setClassIndex(header.numAttributes() - 1);
		return header;		
    }
    
    private Instance convertToInstance(AccessEvent accessEvent, double classValue) {
    	Instance inst = new DenseInstance(getHeader().numAttributes());
    	inst.setDataset(getHeader());
        int index = 0;
	       
	        for(String [] attribute : attributes) {
	        	AttributeContainer attributeContainer = null;
	        	switch(attribute[0]) {
	        	case "subject":
	        		attributeContainer = accessEvent.getSubject();
	        		break;
	        	case "resource":
	        		attributeContainer = accessEvent.getResource();
	        		break;
	        	case "action":
	        		attributeContainer = accessEvent.getAction();
	        		break;
	        	case "environment":
	        		attributeContainer = accessEvent.getEnvironment();
	        		break;
	        	}
	        	Object val = attributeContainer.getAttribute(attribute[1]);
	        	if(attribute.length > 2) {
	        		if(attribute[2].equals("double")) {
	        			if(val instanceof Double) {
	        				inst.setValue(index, (Double)val);
	    	        		
	    	        	}
	        			else if(val == null){
	        				inst.setValue(index, 0);
    	        		}
	    	        	else {
	    	        		String strValue = null;
	    	        		if(val instanceof String) {
	    	        			strValue = (String)val;
	    	        		}
	    	        		else if(val instanceof TextNode) {
	    	        			strValue = ((TextNode)val).asText();
	    	        		}
	    	        		if(strValue == null){
	    	        			inst.setValue(index, 0);
	    	        		}else {
	    	        			inst.setValue(index, (Double)Double.parseDouble(strValue));
	    	        		}
	    	        	}
	        			index++;
	        			continue;
	        		}
	        	}
	        	if(val instanceof Double) {
	        		inst.setValue(index, (Double)val);
	        	}
	        	else if(val instanceof String) {
	        		inst.setValue(index, getOrdinal(attribute[0], attribute[1], (String)val));
	        	}
	        	else if(val instanceof TextNode) {
	        		inst.setValue(index, getOrdinal(attribute[0], attribute[1], (String)val));
	        		
	        	}else if(val != null){
	        		Log.error("ERROR ERROR ERRROR : Did not understand value");
	        	}else {
	        		//NULL is set as 0
	        		inst.setValue(index, 0);
	        	}
	        	index++;
	        }
	        inst.setClassValue(classValue);
	        return inst;
    }
    
    public void onlineFit(AccessEvent accessEvent, boolean isAnamoly) {

		Instance instance = convertToInstance(accessEvent, isAnamoly? 1: 0);
		if (null == learner) {
			synchronized (MoAOnlineAnomalyDetector.class) {
				if (learner == null) {
					learner = new HoeffdingTree();
					learner.setModelContext(getHeader());
		            learner.prepareForUse();
				}
			}
		}
		learner.trainOnInstance(instance);
    }
    
    public boolean isAnomaly(AccessEvent accessEvent) {
    	if(null == learner) {
    		return true;
    	}
    	Instance instance = convertToInstance(accessEvent, 0);
    	
    	double []votes = learner.getVotesForInstance(instance);
    	
    	logger.info("evalulate: "+String.format("%,.4f", votes[0])+", event: "+accessEvent+", tensor : "+instance.toString() );
    	return votes[0] < THRESHOLD;
    }

   public static void main(String[] args) throws IOException, FileNotFoundException {

       List<String []> attributes = Arrays.asList(
    		   new String[] {"subject", "id", "double"}, new String[] {"resource", "id", "categorical"}, new String[] {"action", "id", "categorical"});
       MoAOnlineAnomalyDetector detector = new MoAOnlineAnomalyDetector( attributes);
       

       //Train
       AccessEvent accessEvent = new AccessEvent();
       for(int i=0; i<100; i++) {
	       accessEvent.getSubject().setAttribute("id", 10*Math.random());
	       accessEvent.getResource().setAttribute("id", "account");
	       accessEvent.getAction().setAttribute("id", "atmWithdrawal");
	       detector.onlineFit(accessEvent, false);
       }
       
       /*for(int i=0; i<100; i++) {
	       accessEvent.getSubject().setAttribute("id", 1000+1000*Math.random());
	       accessEvent.getResource().setAttribute("id", "account");
	       accessEvent.getAction().setAttribute("id", "atmWithdrawal");
	       detector.onlineFit(accessEvent, true);
       }*/

       //Test
       //With last normal access event added
       logger.info("isAnamoly: "+detector.isAnomaly(accessEvent));
       
       //With a new normal access event
       accessEvent.getSubject().setAttribute("id", 9D);
       accessEvent.getResource().setAttribute("id", "account");
       accessEvent.getAction().setAttribute("id", "atmWithdrawal");
       logger.info("isAnamoly: "+detector.isAnomaly(accessEvent));

       //With a new fraud access event
       accessEvent.getSubject().setAttribute("id", 2500D);
       accessEvent.getResource().setAttribute("id", "account");
       accessEvent.getAction().setAttribute("id", "onlineShopping");
       logger.info("isAnamoly: "+detector.isAnomaly(accessEvent));
   }
}