package com.stream.telecom;

import com.stream.Server;
import com.stream.Workflow;

/**
 * Application main - start here
 *
 */
public class TelecomServer extends Server
{
    public static void main( String[] args ) throws Exception
    {
       (new TelecomServer()).run();
    }
    
    @Override
    public void run() throws Exception {
    	 System.out.println( TelecomServer.class.getCanonicalName()+" Starting!" );
         Workflow workflow = new TelecomUsageWorkflow();
         workflow.run();
         System.out.println(TelecomServer.class.getCanonicalName()+" Done");
    }
}
