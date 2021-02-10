package com.stream.simulation;

import com.github.vincentrussell.json.datagenerator.JsonDataGeneratorException;
import com.github.vincentrussell.json.datagenerator.impl.JsonDataGeneratorImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class JsonDataGenerator {

    public static void main(String[] args) throws JsonDataGeneratorException, UnsupportedEncodingException {
        JsonDataGeneratorImpl parser = new JsonDataGeneratorImpl();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //parser.generateTestDataJson(new File("/Users/anagaraj/Downloads/streaming-with-flink-main/src/main/resources/simple.json"), byteArrayOutputStream);
        parser.generateTestDataJson(new File("/Users/bdutt/dev/bds/flink/streaming-with-flink/src/main/resources/simple.json"), byteArrayOutputStream);
        
        System.out.println(byteArrayOutputStream.toString("UTF-8"));
    }
}
