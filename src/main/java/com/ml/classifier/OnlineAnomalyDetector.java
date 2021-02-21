package com.ml.classifier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.chen0040.data.frame.BasicDataFrame;
import com.github.chen0040.data.frame.BasicDataRow;
import com.github.chen0040.data.frame.DataFrame;
import com.github.chen0040.data.frame.DataRow;
import com.github.chen0040.trees.isolation.IsolationForest;
import com.stream.fraud.model.AttributeContainer;
import com.stream.simulation.AccessEvent;

public class OnlineAnomalyDetector implements Serializable {
	
	private final static Logger logger = LoggerFactory.getLogger(OnlineAnomalyDetector.class.getName());

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final double THRESHOLD = 0.4;

    private static final String ANOMALY_COLUMN_NAME = "anomaly";

    public transient IsolationForest algorithm;

    private List<String[]> attributes;

    public OnlineAnomalyDetector(List<String[]> attributes) throws FileNotFoundException {
        this.attributes = attributes;
    }
    
    private DataRow convertToDataRow(AccessEvent accessEvent) {
    	// DataRow row = dataFrame.newRow();
    	DataRow row = new BasicDataRow();
	       
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
	        	if(val instanceof Double) {
	        		row.setCell(attribute[0]+"."+attribute[1], (Double)attributeContainer.getAttribute(attribute[1]));
	        	}
	        	if(val instanceof String) {
	        		row.setCategoricalCell(attribute[0]+"."+attribute[1], (String)attributeContainer.getAttribute(attribute[1]));
	        	}
	        }
	        return row;
    }
    
    public void onlineFit(AccessEvent[] accessEvents) {
        DataFrame dataFrame = new BasicDataFrame();
        
        for(AccessEvent accessEvent: accessEvents) {
        	DataRow row = convertToDataRow(accessEvent);
	        dataFrame.addRow(row);
        }
        
		if (null == algorithm) {
			synchronized (OnlineAnomalyDetector.class) {
				if (algorithm == null) {
					algorithm = new IsolationForest();
					algorithm.setThreshold(THRESHOLD);
				}
			}
		}
        algorithm.incrementalFit(dataFrame);
    }
    
    public boolean isAnomaly(AccessEvent accessEvent) {
    	DataRow row = convertToDataRow(accessEvent);
    	Map<String, List<String>> levels = new HashMap<>();
        ArrayList list = new ArrayList();
        list.add("atmWithdrawal");
        list.add("onlineShopping");
        levels.put("action.id", list);
        row.setLevels(levels);
    	logger.info("evalulate: "+algorithm.evaluate(row));
    	return algorithm.isAnomaly(row);
    }

   public static void main(String[] args) throws IOException, FileNotFoundException {

       List<String []> attributes = Arrays.asList(
    		   new String[] {"subject","id"}, new String[] {"resource","id"}, new String[] {"action","id"});
       OnlineAnomalyDetector algorithm = new OnlineAnomalyDetector( attributes);
       

       //Train
       //AccessEvent [] accessEvents = new AccessEvent[100];
       AccessEvent accessEvent = new AccessEvent();
       for(int i=0; i<100; i++) {
    	   //accessEvents[i]=accessEvent = new AccessEvent();
	       accessEvent.getSubject().setAttribute("id", 10*Math.random());
	       accessEvent.getResource().setAttribute("id", 10*Math.random());
	       accessEvent.getAction().setAttribute("id", "atmWithdrawal");
	       algorithm.onlineFit(new AccessEvent[] {accessEvent});
       }
       //algorithm.onlineFit(accessEvents);
       
       System.out.println("Algorithm: treecount="+algorithm.algorithm.getTreeCount()+", rowcount="+algorithm.algorithm.getRowCount());

       //Test
       //With last normal access event added
       logger.info("isAnamoly: "+algorithm.isAnomaly(accessEvent));
       
       //With a new normal access event
       accessEvent.getSubject().setAttribute("id", 9D);
       accessEvent.getResource().setAttribute("id", 9D);
       accessEvent.getAction().setAttribute("id", "atmWithdrawal");
       logger.info("isAnamoly: "+algorithm.isAnomaly(accessEvent));

       //With a new fraud access event
       accessEvent.getSubject().setAttribute("id", 25D);
       accessEvent.getResource().setAttribute("id", 25D);
       accessEvent.getAction().setAttribute("id", "onlineShopping");
       logger.info("isAnamoly: "+algorithm.isAnomaly(accessEvent));
   }
}