package com.stream.accounting;

import com.stream.Server;

/**
 * Application main - start here
 *
 */
public class AccountingServer extends Server
{
    public static void main( String[] args ) throws Exception
    {
       (new AccountingServer()).run();
    }

	@Override
	public void run() throws Exception {
		System.out.println(AccountingServer.class.getCanonicalName() + " Starting!");
		AccountingWorkflow workflow = new AccountingWorkflow();
		workflow.run();
		System.out.println(AccountingServer.class.getCanonicalName() + " Done");
		
	}
}
