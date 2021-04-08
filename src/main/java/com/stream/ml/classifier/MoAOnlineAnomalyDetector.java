package com.stream.ml.classifier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.minlog.Log;
import com.stream.fraud.model.AccessEvent;
import com.stream.fraud.model.AttributeContainer;
import com.stream.ml.classifier.OnlineAnomalyDetector.FRAUD_CLASS;
import com.yahoo.labs.samoa.instances.Attribute;
import com.yahoo.labs.samoa.instances.DenseInstance;
import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.InstancesHeader;

import moa.classifiers.Classifier;
import moa.classifiers.bayes.NaiveBayes;
import moa.classifiers.trees.AdaHoeffdingOptionTree;
import moa.classifiers.trees.DecisionStump;
import moa.classifiers.trees.HoeffdingAdaptiveTree;
import moa.classifiers.trees.HoeffdingTree;
import moa.clusterers.Clusterer;
import moa.clusterers.outliers.MyBaseOutlierDetector;
import moa.clusterers.outliers.MyBaseOutlierDetector.Outlier;
import moa.clusterers.outliers.AbstractC.AbstractC;
import moa.clusterers.outliers.Angiulli.ApproxSTORM;
import moa.clusterers.outliers.Angiulli.ExactSTORM;
import moa.clusterers.outliers.MCOD.MCOD;
import moa.clusterers.outliers.SimpleCOD.SimpleCOD;

public class MoAOnlineAnomalyDetector extends OnlineAnomalyDetector{
	
	private final static Logger logger = LoggerFactory.getLogger(MoAOnlineAnomalyDetector.class.getName());

	private static final long serialVersionUID = 1L;

	private static final double THRESHOLD = 0.01;

    private static final String ANOMALY_COLUMN_NAME = "anomaly";
    
    public transient Map<String, Classifier> classifiers = null;
    public transient Map<String, Clusterer> clusterers = null;

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
    
    public void onlineFit(AccessEvent accessEvent, FRAUD_CLASS fraudClass) {

	
		if (null == classifiers) {
			synchronized (MoAOnlineAnomalyDetector.class) {
				if (classifiers == null) {
					classifiers = new HashMap<>();
					clusterers = new HashMap<>();
					
					Classifier classifier = new HoeffdingTree();
					classifier.setModelContext(getHeader());
					classifier.prepareForUse();
					classifiers.put("HoeffdingTree", classifier);
					classifier = new HoeffdingAdaptiveTree();
					classifier.setModelContext(getHeader());
					classifier.prepareForUse();
					classifiers.put("HoeffdingAdaptiveTree", classifier);
					classifier = new NaiveBayes();
					classifier.setModelContext(getHeader());
					classifier.prepareForUse();
					classifiers.put("NaiveBayes", classifier);
					classifier = new DecisionStump();
					classifier.setModelContext(getHeader());
					classifier.prepareForUse();
					classifiers.put("DecisionStump", classifier);
					classifier = new AdaHoeffdingOptionTree();
					classifier.setModelContext(getHeader());
					classifier.prepareForUse();
					classifiers.put("AdaHoeffdingOptionTree", classifier);
					classifier = new RandomBinaryClassifier();
					classifier.setModelContext(getHeader());
					classifier.prepareForUse();
					//classifiers.put("RandomBinaryClassifier", classifier);

					ExactSTORM myOutlierDetector= new ExactSTORM();
			        myOutlierDetector.queryFreqOption.setValue(1);
			        myOutlierDetector.kOption.setValue(2);
			        myOutlierDetector.radiusOption.setValue(100);
			        myOutlierDetector.setModelContext(getHeader());
			        myOutlierDetector.prepareForUse();
			        clusterers.put("ExactSTORM", myOutlierDetector);
			        ApproxSTORM myOutlierDetector1= new ApproxSTORM();
			        myOutlierDetector1.queryFreqOption.setValue(1);
			        myOutlierDetector1.kOption.setValue(2);
			        myOutlierDetector1.radiusOption.setValue(100);
			        myOutlierDetector1.setModelContext(getHeader());
			        myOutlierDetector1.prepareForUse();
			        clusterers.put("ApproxSTORM", myOutlierDetector1);
			        MCOD mcod = new MCOD();
			        mcod.kOption.setValue(2);
			        mcod.radiusOption.setValue(100);
			        mcod.setModelContext(getHeader());
			        mcod.prepareForUse();
			        clusterers.put("MCOD", mcod);
			        SimpleCOD simplecod = new SimpleCOD();
			        simplecod.kOption.setValue(2);
			        simplecod.radiusOption.setValue(100);
			        simplecod.setModelContext(getHeader());
			        simplecod.prepareForUse();
			        clusterers.put("SimpleCOD", simplecod);
			        AbstractC abstractC = new AbstractC();
			        abstractC.kOption.setValue(2);
			        abstractC.radiusOption.setValue(100);
			        abstractC.setModelContext(getHeader());
			        abstractC.prepareForUse();
			        clusterers.put("AbstractC", abstractC);
			           
					// we removed this one because of license issues
					// classifier = new HoeffdingTreeNG();
				}
			}
		}
		
		Instance instance = convertToInstance(accessEvent, fraudClass == FRAUD_CLASS.ANOMALY? 1: 0);
		if(FRAUD_CLASS.UNKNOWN != fraudClass) {
			for(Entry<String, Classifier> classifierEntry: classifiers.entrySet()) {
				try {
					classifierEntry.getValue().trainOnInstance(instance);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		for(Entry<String, Clusterer> clustererEntry: clusterers.entrySet()) {
			try {
				clustererEntry.getValue().trainOnInstance(instance);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
    }
    
    public MultiClassAnomalyOutput [] isAnomaly(AccessEvent accessEvent) {
    	if(null == classifiers && null == clusterers) {
    		MultiClassAnomalyOutput [] result = new MultiClassAnomalyOutput[] {
    				new MultiClassAnomalyOutput("Null Classifier", true)
    		};
    		return result;
    	}
    	Instance instance = convertToInstance(accessEvent, 1);
    	int ruleBasedML = 1;
    	
    	MultiClassAnomalyOutput [] result = new MultiClassAnomalyOutput[classifiers.size()+clusterers.size()+ruleBasedML];
    	int index = 0;
    	StringBuffer sb = new StringBuffer();
    	sb.append("isAnamoly: ");
    	for(Entry<String, Classifier> classifierEntry: classifiers.entrySet()) {
			double []votes = classifierEntry.getValue().getVotesForInstance(instance);
			if(votes == null || votes.length <1) {
				continue;
			}
			MultiClassAnomalyOutput multiClassAnomalyOutput = null;
			if(votes.length == 2) {
				multiClassAnomalyOutput = new MultiClassAnomalyOutput(classifierEntry.getKey(), votes[0] < votes[1]);
			} else {
				multiClassAnomalyOutput = new MultiClassAnomalyOutput(classifierEntry.getKey(), votes[0] < THRESHOLD);
			}
	    	sb.append(multiClassAnomalyOutput + " : "+String.format("%,.4f", votes[0])+", ");
	    	result[index++] = multiClassAnomalyOutput;
		}
    	for(Entry<String, Clusterer> clustererEntry: clusterers.entrySet()) {
			double []votes = clustererEntry.getValue().getVotesForInstance(instance);
			
			if(votes == null || votes.length < 1) {
				if(clustererEntry.getValue() instanceof MyBaseOutlierDetector) {
					MyBaseOutlierDetector myBaseOutlierDetector = (MyBaseOutlierDetector)clustererEntry.getValue();
					myBaseOutlierDetector.processNewInstanceImpl(instance);
					Vector<Outlier> outlierVector = myBaseOutlierDetector.getOutliersResult();
					boolean isOutlier = false;
					for(Outlier outlier: outlierVector) {
						if(outlier.inst.equals(instance)) {
							isOutlier = true;
							break;
						}
					}
					MultiClassAnomalyOutput multiClassAnomalyOutput = new MultiClassAnomalyOutput(clustererEntry.getKey(), isOutlier);
				    sb.append(multiClassAnomalyOutput +", ");
				    result[index++] = multiClassAnomalyOutput;
				}
			}else {
				MultiClassAnomalyOutput multiClassAnomalyOutput = new MultiClassAnomalyOutput(clustererEntry.getKey(), votes[0] < THRESHOLD);
				sb.append(multiClassAnomalyOutput + " : "+String.format("%,.4f", votes[0])+", ");
				result[index++] = multiClassAnomalyOutput;
			}
		}
    	
    	Object denyUser = accessEvent.getSubject().getAttribute("denyUsers");
    	MultiClassAnomalyOutput multiClassAnomalyOutput = new MultiClassAnomalyOutput("DenyUsers", null!=denyUser && "true".equalsIgnoreCase((String) denyUser) );
    	sb.append(multiClassAnomalyOutput +", ");
    	result[index++] = multiClassAnomalyOutput;
    	
    	sb.append(" event: "+accessEvent+", tensor : "+instance.toString() );
    	logger.info(sb.toString());
    	return result;	
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
	       detector.onlineFit(accessEvent, FRAUD_CLASS.NORMAL);
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