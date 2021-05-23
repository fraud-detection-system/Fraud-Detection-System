//----------------------------------------------
//Configuration
//----------------------------------------------


//----------------------------------------------

logger.info("Running the Random simulation")

var importIt = new JavaImporter(java.lang.String,java.util,java.io,java.time,com.stream.simulation,com.stream.fraud.model);  
with (importIt) {  
  var simulator = new Simulator();

  var state = simulator.defineState("SingleState")
  	.addResource("id", "account")
  	.addResource("tenant", "Random")
  	.addResource("amount", "100")
	.addAction("id", "atmWithdrawal");

  var markovChain = [
  	new StateTransition("SingleState", "SingleState", 1 , 1000)
  ];

  var resourceTemplate = new Resource();
  resourceTemplate.setAttribute("accountId","123");
  resourceTemplate.setAttribute("simulation","random");
  var resourcePool = simulator.definePool(1, resourceTemplate);
  var subjectTemplate = new Subject();
  subjectTemplate.setAttribute("id","123");
  subjectTemplate.setAttribute("simulation","random");
  var subjectPool = simulator.definePool(1, subjectTemplate);
  var subjectResourcePool = simulator.pair(resourcePool,subjectPool);
  simulator.defineActor("123").stateTransition("SingleState", markovChain);

  simulator.startActors("123", subjectResourcePool, 100, 100);
  simulator.sleepInMilliSecs(300000);
  simulator.pauseAndPrompt("End the simulation?");
  simulator.end()
}  
result=simulator;
logger.info("Done - simulation script")

