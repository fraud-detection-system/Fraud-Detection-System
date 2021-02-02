package com.ml.classifier;

import com.github.chen0040.data.frame.BasicDataRow;
import com.github.chen0040.data.frame.DataFrame;
import com.github.chen0040.data.frame.DataQuery;
import com.github.chen0040.data.frame.DataRow;
import com.github.chen0040.trees.isolation.IsolationForest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnomalyDetector implements Serializable {

    private static final double THRESHOLD = 0.4;

    private static final String ANOMALY_COLUMN_NAME = "anomaly";

    public transient IsolationForest algorithm;

    private String trainingFileName;

    private List<String> columns;

    public AnomalyDetector(String trainingFileName, List<String> columns) throws FileNotFoundException {
        this.trainingFileName = trainingFileName;
        this.columns = columns;
        //init(this.trainingFileName, this.columns);
    }

    public void init() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(trainingFileName);
        DataQuery.DataFrameQueryBuilder dataFrameQueryBuilder = DataQuery.csv().from(inputStream);

        int colNum = 0;
        for(String columnName : columns) {
            dataFrameQueryBuilder.selectColumn(colNum++).asNumeric().asInput(columnName);
        }
        dataFrameQueryBuilder.selectColumn(colNum).asNumeric().asOutput(ANOMALY_COLUMN_NAME);

        DataFrame dataFrame = dataFrameQueryBuilder.build();

        algorithm = new IsolationForest();
        algorithm.setThreshold(THRESHOLD);
        DataFrame learnedData = algorithm.fitAndTransform(dataFrame);

//        int cnt = 0;
//        for(int i = 0; i < learnedData.rowCount(); i++){
//            //boolean predicted = learnedData.row(i).categoricalTarget().equals("1");
//            DataRow learnedRow = dataFrame.row(i);
//            double givenTarget = learnedRow.target();
//            String learntTargetStr = learnedRow.categoricalTarget();
//            double learntTarget = Double.parseDouble(learntTargetStr);
//
//            if(givenTarget != learntTarget) {
//                System.out.println(learnedRow);
//                cnt++;
//            }
//
//
//            //evaluator.evaluate(actual, predicted);
//            //logger.info("predicted: {}\texpected: {}", predicted, actual);
//        }
        System.out.println("***************");
        System.out.println("Finished Training model");
//        System.out.println("Finished Training model " + (learnedData.rowCount() - cnt) + "/"+learnedData.rowCount());
        System.out.println("***************");
    }

    public boolean isAnomaly(Map<String, Double> eventData) throws FileNotFoundException {
        if(algorithm == null) {
            synchronized (AnomalyDetector.class) {
                if(algorithm == null) {
                    init();
                }
            }
        }
        long x = System.nanoTime();
        DataRow testTuple = new BasicDataRow();
//        testTuple.setCell("userId", eventData.get("userId"));
//        testTuple.setCell("amount", eventData.get("amount"));
//        testTuple.setCell("milesFromLastTxn", eventData.get("milesFromLastTxn"));
//        testTuple.setCell("hoursFromLastTxn", eventData.get("hoursFromLastTxn"));

        for(Map.Entry<String, Double> entry : eventData.entrySet()) {
            testTuple.setCell(entry.getKey(), entry.getValue());
        }

        boolean isAnomaly = algorithm.isAnomaly(testTuple);
        long y = System.nanoTime();
        System.out.println("*************");
        System.out.println("Time = " + (y-x));
        System.out.println("*************");
        return isAnomaly;
    }

    public double evaluate(Map<String, Double> eventData) {
        DataRow testTuple = new BasicDataRow();
//        testTuple.setCell("userId", eventData.get("userId"));
//        testTuple.setCell("amount", eventData.get("amount"));
//        testTuple.setCell("milesFromLastTxn", eventData.get("milesFromLastTxn"));
//        testTuple.setCell("hoursFromLastTxn", eventData.get("hoursFromLastTxn"));

        for(Map.Entry<String, Double> entry : eventData.entrySet()) {
            testTuple.setCell(entry.getKey(), entry.getValue());
        }

        return algorithm.evaluate(testTuple);
    }

   public static void main(String[] args) throws IOException, FileNotFoundException {

       List<String> columns = Arrays.asList("userId", "amount", "milesFromLastTxn", "hoursFromLastTxn");
       AnomalyDetector algorithm = new AnomalyDetector("data.txt", columns);
       algorithm.init();

       DataRow testTuple = new BasicDataRow();
       Map<String,Double> cells = new HashMap<>();
       cells.put("userId", 25D);
       cells.put("amount", 1000000D);
       cells.put("milesFromLastTxn", 50D);
       cells.put("hoursFromLastTxn", 2D);
       for(Map.Entry<String, Double> entry : cells.entrySet()) {

           testTuple.setCell(entry.getKey(), entry.getValue());
       }
//       testTuple.setCell("amount", 1000000);
//       testTuple.setCell("milesFromLastTxn", 50);
//       testTuple.setCell("hoursFromLastTxn", 2);
       System.out.println(algorithm.algorithm.evaluate(testTuple));
       System.out.println(algorithm.algorithm.isAnomaly(testTuple));

//        StringWriter writer = new StringWriter();
//        IOUtils.copy(inputStream, writer, "UTF-8");
//        System.out.println(writer.toString());
//        System.out.println("-------");





       /* int cnt = 0;
        for(int i = 0; i < learnedData.rowCount(); i++){
            //boolean predicted = learnedData.row(i).categoricalTarget().equals("1");
            DataRow learnedRow = data.row(i);
            double givenTarget = learnedRow.target();
            String learntTargetStr = learnedRow.categoricalTarget();
            double learntTarget = Double.parseDouble(learntTargetStr);

            if(givenTarget != learntTarget) {
                System.out.println(learnedRow);
                cnt++;
            }


            //evaluator.evaluate(actual, predicted);
            //logger.info("predicted: {}\texpected: {}", predicted, actual);
        }
        System.out.println(cnt + " " + learnedData.rowCount());


    }*/
   }
}