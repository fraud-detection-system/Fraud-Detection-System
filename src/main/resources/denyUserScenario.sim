//----------------------------------------------
//Configuration
//----------------------------------------------


//----------------------------------------------

logger.info("Running the Deny User simulation")

var importIt = new JavaImporter(java.lang.String,java.util,java.io,java.time,com.stream.simulation,com.stream.fraud.model);  
with (importIt) {  
  var simulator = new Simulator();

  var state = simulator.defineState("SingleState")
  	.addResource("id", "account")
  	.addResource("amount", "(new java.util.Random()).nextInt(10000-0) + 0")
	.addAction("id", "atmWithdrawal");

  var markovChain = [
  	new StateTransition("SingleState", "SingleState", 1 , 1000)
  ];

  var resourceTemplate = new Resource();
  resourceTemplate.setAttribute("accountId","420");
  resourceTemplate.setAttribute("simulation","denyUser");
  var resourcePool = simulator.definePool(1, resourceTemplate);
  var subjectTemplate = new Subject();
  subjectTemplate.setAttribute("id","420");
  subjectTemplate.setAttribute("IPAddress","172.1.1.1");
  subjectTemplate.setAttribute("simulation","denyUser");
  var subjectPool = simulator.definePool(1, subjectTemplate);
  var subjectResourcePool = simulator.pair(resourcePool,subjectPool);
  simulator.defineActor("420").stateTransition("SingleState", markovChain);

  simulator.startActors("420", subjectResourcePool, 100, 100);
  simulator.sleepInMilliSecs(300000);
  simulator.pauseAndPrompt("End the simulation?");
  simulator.end()
}  
result=simulator;
logger.info("Done - simulation script")

