package com.stream.fraud;

import com.stream.Workflow;

public class OnlineFraudDetectionServer
{
    public static void main( String[] args ) throws Exception
    {
        run();
    }
    public static void run() throws Exception {
        System.out.println( OnlineFraudDetectionServer.class.getCanonicalName()+" Starting!" );
        Workflow workflow = new OnlineFraudDetectionWorkflow();
        workflow.run();
        System.out.println(OnlineFraudDetectionServer.class.getCanonicalName()+" Done");
    }
}
