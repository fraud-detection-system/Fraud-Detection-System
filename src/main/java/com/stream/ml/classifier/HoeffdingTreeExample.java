package com.stream.ml.classifier;

import moa.classifiers.trees.HoeffdingTree;
import moa.classifiers.Classifier;
import moa.core.TimingUtils;
import moa.streams.generators.RandomRBFGenerator;

import com.yahoo.labs.samoa.instances.Attribute;
import com.yahoo.labs.samoa.instances.DenseInstance;
import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.Instances;
import com.yahoo.labs.samoa.instances.InstancesHeader;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.mahout.math.Arrays;


public class HoeffdingTreeExample {

        public HoeffdingTreeExample(){
        }

        public void run(int numInstances, boolean isTesting){
                Classifier learner = new HoeffdingTree();
                RandomRBFGenerator stream = new RandomRBFGenerator();
                stream.prepareForUse();

                learner.setModelContext(stream.getHeader());
                learner.prepareForUse();

                int numberSamplesCorrect = 0;
                int numberSamples = 0;
                boolean preciseCPUTiming = TimingUtils.enablePreciseTiming();
                long evaluateStartTime = TimingUtils.getNanoCPUTimeOfCurrentThread();
                while (stream.hasMoreInstances() && numberSamples < numInstances) {
                        Instance trainInst = stream.nextInstance().getData();
                        //System.out.println(trainInst.numValues()+": "+trainInst.classValue()+": "+trainInst.toString());
                        if (isTesting) {
                                if (learner.correctlyClassifies(trainInst)){
                                        numberSamplesCorrect++;
                                }
                        }
                        numberSamples++;
                        learner.trainOnInstance(trainInst);
                }
                double accuracy = 100.0 * (double) numberSamplesCorrect/ (double) numberSamples;
                double time = TimingUtils.nanoTimeToSeconds(TimingUtils.getNanoCPUTimeOfCurrentThread()- evaluateStartTime);
                System.out.println(numberSamples + " instances processed with " + accuracy + "% accuracy in "+time+" seconds.");
        }
        
        public void run1(int numInstances, boolean isTesting){
            Classifier learner = new HoeffdingTree();
            

            ArrayList<Attribute> attributes = new ArrayList<Attribute>();
           
            attributes.add(new Attribute("att1"));
            attributes.add(new Attribute("att2"));
            attributes.add(new Attribute("att3"));
            attributes.add(new Attribute("att4"));

            
            ArrayList<String> classLabels = new ArrayList<String>();
            
              classLabels.add("class1");
              classLabels.add("class2");

            
            
            attributes.add(new Attribute("class", classLabels));
            InstancesHeader header = new InstancesHeader();
            header.setAttributes((Attribute[]) attributes.toArray(new Attribute[0]));
            header.setClassIndex(header.numAttributes() - 1); 
           
           
            learner.setModelContext(header);
            learner.prepareForUse();

            int numberSamplesCorrect = 0;
            int numberSamples = 0;
            boolean preciseCPUTiming = TimingUtils.enablePreciseTiming();
            long evaluateStartTime = TimingUtils.getNanoCPUTimeOfCurrentThread();
            while (numberSamples < numInstances) {
            	Instance inst = new DenseInstance(header.numAttributes());
                inst.setValue(0, Math.random());
                inst.setValue(1, Math.random());
                inst.setValue(2, Math.random());
                inst.setValue(3, Math.random());
                inst.setDataset(header);
                inst.setClassValue(Math.random() > 0.5? 0.0: 1.0);
              System.out.println(inst.numValues()+": "+inst.classValue()+": "+Arrays.toString(learner.getPredictionForInstance(inst).getVotes())+" : "+inst.toString());   
                    if (isTesting) {
                            if (learner.correctlyClassifies(inst)){
                                    numberSamplesCorrect++;
                            }
                    }
                    
                    numberSamples++;
                    learner.trainOnInstance(inst);
            }
            double accuracy = 100.0 * (double) numberSamplesCorrect/ (double) numberSamples;
            double time = TimingUtils.nanoTimeToSeconds(TimingUtils.getNanoCPUTimeOfCurrentThread()- evaluateStartTime);
            System.out.println(numberSamples + " instances processed with " + accuracy + "% accuracy in "+time+" seconds.");
    }

        public static void main(String[] args) throws IOException {
                HoeffdingTreeExample exp = new HoeffdingTreeExample();
                exp.run1(1000000, true);
        }
}