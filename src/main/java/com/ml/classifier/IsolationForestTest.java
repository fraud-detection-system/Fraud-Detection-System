package com.ml.classifier;

import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.misc.IsolationForest;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class IsolationForestTest {

    public static void main(String[] args) throws Exception {
        //new IsolationForest().main(args);
        Instances testInstances = new Instances(new BufferedReader(new FileReader("test.arff")));
        testInstances.setClassIndex(1);
        Classifier classifier = new IsolationForest();

        //Evaluation evaluation = new Evaluation(testInstances);
        //evaluation.evaluateModel(new IsolationForest(), testInstances);
        Instance instance1 = new DenseInstance(1.0, new double[]{-11,-22});
        Instance instance2 = new DenseInstance(1.0, new double[]{11,22});
        Instance instance3 = new DenseInstance(1.0, new double[]{3,4});
        Instance instance4 = new DenseInstance(1.0, new double[]{5,6});
        Instance instance5 = new DenseInstance(1.0, new double[]{-3,-4});
        Instance instance6 = new DenseInstance(1.0, new double[]{-5,-6});
        Instance instance7 = new DenseInstance(1.0, new double[]{30,5});
        ArrayList<Attribute> attInfo = new ArrayList<>();
        Instances instances = new Instances("test", attInfo, 7);
        instances.insertAttributeAt(new Attribute("one"), 0);
        instances.insertAttributeAt(new Attribute("two"), 1);
        instances.add(instance1);
        instances.add(instance2);
        instances.add(instance3);
        instances.add(instance4);
        instances.add(instance5);
        instances.add(instance6);
        instances.add(instance7);

        classifier.buildClassifier(testInstances);
        classifier.classifyInstance(new DenseInstance(1.0, new double[]{40,40}));
    }
}
