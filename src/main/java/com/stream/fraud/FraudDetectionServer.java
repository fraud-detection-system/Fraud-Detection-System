package com.stream.fraud;

import com.stream.Workflow;

public class FraudDetectionServer
{
    public static void main( String[] args ) throws Exception
    {
        run();
    }
    public static void run() throws Exception {
        System.out.println( FraudDetectionServer.class.getCanonicalName()+" Starting!" );
        Workflow workflow = new FraudDetectionWorkflow();
        workflow.run();
        System.out.println(FraudDetectionServer.class.getCanonicalName()+" Done");
    }
}
