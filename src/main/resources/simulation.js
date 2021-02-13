//print("hello")
logger.info("Running the simulation script")

var importIt = new JavaImporter(java.lang.String,java.util,java.io,com.stream.simulation);  
with (importIt) {  
  var simulator = new Simulator();

  simulator.defineState("login").addResource("id", "account").addAction("id", "login").addSubject("id","");
  simulator.defineState("logout").addResource("id", "account").addAction("id", "logout").addSubject("id","");
  simulator.defineState("checkBalance").addResource("id", "balance").addAction("id", "read").addSubject("id","");
  simulator.defineState("transferMoney").addResource("id", "account"). 
  	addResource("accountId","").addResource("payee", "abc").addAction("id", "transfer").addSubject("id","");

  var stateTransitions = [
  	new StateTransition("login", "checkBalance", 100 ),
	new StateTransition("checkBalance", "transferMoney", 100),
	new StateTransition("transferMoney", "logout", 100)
  ];
  var stateTransitions1 = [
  	new StateTransition("login", "transferMoney", 100 ),
	new StateTransition("transferMoney", "logout", 100)
  ];

  var normalActorProperties = new HashMap();
  var fraudActorProperties = new HashMap();
  simulator.defineActor("normalActor", normalActorProperties).stateTransition("login", stateTransitions);
  simulator.defineActor("fraudActor", fraudActorProperties).stateTransition("login", stateTransitions1);
  simulator.startActors("normalActor", 9);
  simulator.sleepInMilliSecs(10);
  simulator.startActors("fraudActor", 1);
  simulator.sleepInMilliSecs(100);
  simulator.end()
}  
result=simulator;
logger.info("Done - simulation script")

