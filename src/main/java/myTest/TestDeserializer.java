package myTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.SerializationUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TestDeserializer {

    public static void main(String[] args) throws IOException {

        String data = "";
        try {
            File myObj = new File("/Users/anagaraj/dev/streaming-with-flink/src/main/resources/report.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Report report = new ObjectMapper().readValue(data, Report.class);
        for(int i=0; i<=1000; i++) {
            long start = System.nanoTime();
            Report clone = (Report) SerializationUtils.clone(report);
            long end = System.nanoTime();
            System.out.println((end-start)/1000000);
        }
    }
}
