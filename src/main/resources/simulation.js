//print("hello")
logger.info("Running the simulation script")

var importIt = new JavaImporter(java.lang.String,java.util,java.io,com.stream.simulation,com.stream.fraud.model);  
with (importIt) {  
  var simulator = new Simulator();

//Simulate blacklisted virtualLocations, SubjectIds, AccountIds (locked), phone numbers
//Simulate one bank account receiving many money in
//Simulate one virtual address, many accounts
//Velocity in one account

  simulator.defineState("login")
  	.addResource("id", "account")
	.addAction("id", "login");
  simulator.defineState("logout")
  	.addResource("id", "account")
	.addAction("id", "logout");
  simulator.defineState("checkBalance")
  	.addResource("id", "account")
	.addAction("id", "readBalance");
  simulator.defineState("transferMoney")
  	.addResource("id", "account")
	.addResource("payee", "abc")
	.addAction("id", "transferMoney");
  simulator.defineState("movePhysically")
  	.addResource("id", null)
  	.addAction("id", null)
	.addSubject("physicalLocationX", "subject.getAttribute(\"physicalLocationX\")+(new java.util.Random()).nextInt(10000-0) + 0")
	.addSubject("physicalLocationY", "subject.getAttribute(\"physicalLocationX\")+(new java.util.Random()).nextInt(10000-0) + 0")
	.addSubject("time", "subject.getAttribute(\"currentTime\") + (new java.util.Random()).nextInt(10000-0) + 0");
  simulator.defineState("moveVirtually")
  	.addResource("id", null)
  	.addAction("id", null)
	.addSubject("virtualLocation", "subject.getAttribute(\"virtualLocation\") + (new java.util.Random()).nextInt(10000-0) + 0")
	.addSubject("time", "subject.getAttribute(\"currentTime\") + (new java.util.Random()).nextInt(10000-0) + 0");


  //How do senior citizens transact: FD interest getting deposited, few debits, only ATM - online withdrawl is fishy
  //Even when OTP is fine - call the owner of back account

  //Delegate - approval - approve for certain transactions - additional approval for flagged transactions
  //Sim exchange while bank transactions are happening

  //Personas to describe - senior citizen, gpay request/demand to withdraw
  //Fraudsters show urgency

  //Regular ATMs - new ATMs

  //var timeToLogin = 10000;
  var timeToLogin = 1000;
  //var timeForAnOperation = 60000;
  var timeForAnOperation = 1000;
  
  var stateTransitions = [
  	new StateTransition("login", "checkBalance", 1 , timeToLogin),
	new StateTransition("checkBalance", "transferMoney", 1, timeForAnOperation),
	new StateTransition("transferMoney", "logout", 1, timeForAnOperation),
	new StateTransition("logout", "movePhysically", 1, timeForAnOperation),
	new StateTransition("movePhysically", "login", 1, timeForAnOperation)
  ];
  var stateTransitions1 = [
  	new StateTransition("login", "transferMoney", 1 , timeToLogin),
	new StateTransition("transferMoney", "logout", 1, timeForAnOperation)
  ];

  var resourceTemplate = new Resource();
  resourceTemplate.setAttribute("accountId","(new java.util.Random()).nextInt(10000-0) + 0");
  var resourcePool = simulator.definePool(1000000, resourceTemplate);
  //var resourcePool = simulator.definePool(1000, resourceTemplate);
  var subjectTemplate = new Subject();
  subjectTemplate.setAttribute("id","(new java.util.Random()).nextInt(10000-0) + 0")
		 .setAttribute("physicalLocationX", "(new java.util.Random()).nextInt(10000-0) + 0")
		 .setAttribute("physicalLocationY", "(new java.util.Random()).nextInt(10000-0) + 0")
	         .setAttribute("virtualLocation","(new java.util.Random()).nextInt(10000-0) + 0")
	         .setAttribute("currentTime","(new java.util.Random()).nextInt(10000-0) + 0");
  var subjectPool = simulator.definePool(1000000, subjectTemplate);
  //var subjectPool = simulator.definePool(1000, subjectTemplate);
  var subjectResourcePool = simulator.pair(resourcePool,subjectPool);
  var normalActorProperties = new HashMap();
  var fraudActorProperties = new HashMap();
  simulator.defineActor("normalActor", normalActorProperties).stateTransition("login", stateTransitions);
  simulator.defineActor("fraudActor", fraudActorProperties).stateTransition("login", stateTransitions1);

  //1mn accounts, 1mn account owners, 10% of them active, 10% of them firing request at same point - 1% of users
  //
  simulator.startActors("normalActor", subjectResourcePool, 10, 10);
  simulator.sleepInMilliSecs(10);
  simulator.startActors("fraudActor", subjectResourcePool, 1, 1);

  simulator.sleepInMilliSecs(300000);
  simulator.pauseAndPrompt("End the simulation?");
  simulator.end()
}  
result=simulator;
logger.info("Done - simulation script")

