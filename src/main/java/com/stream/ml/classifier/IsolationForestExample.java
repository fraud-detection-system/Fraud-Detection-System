package com.stream.ml.classifier;

import com.github.chen0040.data.frame.BasicDataFrame;
import com.github.chen0040.data.frame.BasicDataRow;
import com.github.chen0040.data.frame.DataColumn;
import com.github.chen0040.data.frame.DataFrame;
import com.github.chen0040.data.frame.DataQuery;
import com.github.chen0040.data.frame.DataRow;
import com.github.chen0040.data.frame.InputDataColumn;
import com.github.chen0040.data.frame.OutputDataColumn;
import com.github.chen0040.data.utils.TupleTwo;
import com.github.chen0040.trees.isolation.IsolationForest;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class IsolationForestExample {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("data.txt");

//        StringWriter writer = new StringWriter();
//        IOUtils.copy(inputStream, writer, "UTF-8");
//        System.out.println(writer.toString());
//        System.out.println("-------");

        DataFrame data = DataQuery.csv().from(inputStream)
                .selectColumn(0).asNumeric().asInput("userId")
                .selectColumn(1).asNumeric().asInput("amount")
                .selectColumn(2).asNumeric().asInput("milesFromLastTxn")
                .selectColumn(3).asNumeric().asInput("hoursFromLastTxn")
                .selectColumn(4).asNumeric().asOutput("anomaly")
                .build();

        IsolationForest algorithm = new IsolationForest();
        algorithm.setThreshold(0.4);
        DataFrame learnedData = algorithm.fitAndTransform(data);

        int cnt = 0;
        for(int i = 0; i < data.rowCount(); i++){
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

        DataRow testTuple = new BasicDataRow();
        testTuple.setCell("userId", 25D);
        testTuple.setCell("amount", 1000D);
        testTuple.setCell("milesFromLastTxn", 50D);
        testTuple.setCell("hoursFromLastTxn", 2D);
        System.out.println(algorithm.evaluate(testTuple));
        System.out.println(algorithm.isAnomaly(testTuple));
    }
}