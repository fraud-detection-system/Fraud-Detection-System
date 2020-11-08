package com.stream;

import com.stream.workflow.AccountingWorkflow;

/**
 * Application main - start here
 *
 */
public class AccountingServer 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( AccountingServer.class.getCanonicalName()+" Starting!" );
        AccountingWorkflow workflow = new AccountingWorkflow();
        workflow.run();
        System.out.println(AccountingServer.class.getCanonicalName()+" Done");
    }
}
