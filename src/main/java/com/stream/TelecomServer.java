package com.stream;

import com.stream.workflow.TelecomUsageWorkflow;
import com.stream.workflow.Workflow;

/**
 * Application main - start here
 *
 */
public class TelecomServer 
{
    public static void main( String[] args ) throws Exception
    {
       run();
    }
    public static void run() throws Exception {
    	 System.out.println( TelecomServer.class.getCanonicalName()+" Starting!" );
         Workflow workflow = new TelecomUsageWorkflow();
         workflow.run();
         System.out.println(TelecomServer.class.getCanonicalName()+" Done");
    }
}
