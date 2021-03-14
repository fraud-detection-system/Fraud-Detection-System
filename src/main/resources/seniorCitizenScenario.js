logger.info("Running the Alice GrandMa simulation")

var importIt = new JavaImporter(java.lang.String,java.util,java.io,java.time,com.stream.simulation,com.stream.fraud.model);  
with (importIt) {  
  var simulator = new Simulator();

  simulator.defineState("atmWithdrawal")
  	.addResource("id", "account")
	.addResource("desc", "atm")
	.addResource("creationTime", "new java.util.Date()")
	.addAction("id", "atmWithdrawal");
  simulator.defineState("login")
  	.addResource("id", "account")
	.addResource("desc", "login")
	.addResource("creationTime", "new java.util.Date()")
	.addAction("id", "login");
  simulator.defineState("logout")
  	.addResource("id", "account")
	.addResource("desc", "logout")
	.addResource("creationTime", "new java.util.Date()")
	.addAction("id", "logout");
  simulator.defineState("checkBalance")
  	.addResource("id", "account")
	.addResource("desc", "check balance")
	.addResource("creationTime", "new java.util.Date()")
	.addAction("id", "readBalance");
  simulator.defineState("transferMoney")
  	.addResource("id", "account")
	.addResource("desc", "transfer money")
	.addResource("amount", "(new java.util.Random()).nextInt(10000-0) + 0")
	.addResource("payee", "abc")
	.addResource("creationTime", "new java.util.Date()")
	.addAction("id", "transferMoney");
  simulator.defineState("debitMoney")
  	.addResource("id", "account")
	.addResource("desc", "debit money")
	.addResource("amount", "(new java.util.Random()).nextInt(10-0) + 500")
	.addResource("desc", "from xyz")
	.addResource("creationTime", "new java.util.Date()")
	.addAction("id", "debit");
  simulator.defineState("debitInterest")
  	.addResource("id", "account")
	.addResource("desc", "debit interest")
	.addResource("amount", "(new java.util.Random()).nextInt(10-0) + 500")
	.addResource("desc", "Interest on FD")
	.addResource("creationTime", "new java.util.Date()")
	.addAction("id", "debit");


  //How do senior citizens transact: FD interest getting deposited, few debits, only ATM - online withdrawl is fishy

  var timeToLogin = 1000;
  var timeForAnOperation = 1000;
  
  var trudyMarkovChain = [
  	new StateTransition("login", "checkBalance", 1 , timeToLogin),
	new StateTransition("checkBalance", "transferMoney", 1, timeForAnOperation),
	new StateTransition("transferMoney", "logout", 1, timeForAnOperation),
	new StateTransition("logout", "login", 1, timeForAnOperation)
  ];
  var aliceGrandMaMarkovChain = [
  	new StateTransition("atmWithdrawal", "atmWithdrawal", 0.2 , 10000),
  	new StateTransition("atmWithdrawal", "debitMoney", 0.4 , 10000),
  	new StateTransition("atmWithdrawal", "debitInterest", 0.4 , 10000),
  	new StateTransition( "debitMoney", "atmWithdrawal", 0.5 , 10000),
  	new StateTransition( "debitMoney", "debitInterest", 0.5 , 10000),
  	new StateTransition("debitInterest", "atmWithdrawal", 0.5 , 10000),
  	new StateTransition("debitInterest", "debitMoney", 0.5 , 10000)
  ];

  var resourceTemplate = new Resource();
  resourceTemplate.setAttribute("accountId","(new java.util.Random()).nextInt(10000-0) + 0");
  resourceTemplate.setAttribute("simulation","srCitizen");
  var resourcePool = simulator.definePool(1, resourceTemplate);
  var subjectTemplate = new Subject();
  subjectTemplate.setAttribute("id","(new java.util.Random()).nextInt(10000-0) + 0");
  subjectTemplate.setAttribute("simulation","srCitizen");
  subjectTemplate.setAttribute("startTime", "new java.util.Date()")
  var subjectPool = simulator.definePool(1, subjectTemplate);
  var subjectResourcePool = simulator.pair(resourcePool,subjectPool);
  simulator.defineActor("aliceGrandMa").stateTransition("atmWithdrawal", aliceGrandMaMarkovChain);
  simulator.defineActor("trudy").stateTransition("login", trudyMarkovChain);

  simulator.startActors("aliceGrandMa", subjectResourcePool, 100, 100);
  simulator.pauseAndPrompt("Start trudy?");
  simulator.startActors("trudy", subjectResourcePool, 100, 100);

  simulator.sleepInMilliSecs(300000);
  simulator.pauseAndPrompt("End the simulation?");
  simulator.end()
}  
result=simulator;
logger.info("Done - simulation script")

