//----------------------------------------------
//Configuration
//----------------------------------------------


//----------------------------------------------

logger.info("Running the state based move user simulation")

var importIt = new JavaImporter(java.lang.String,java.util,java.io,java.time,com.stream.simulation,com.stream.fraud.model);  
with (importIt) {  
  var simulator = new Simulator();

  var state1 = simulator.defineState("InBengaluru")
  	.addResource("id", "account")
  	.addResource("amount", "5000")
	.addAction("id", "atmWithdrawal")
	.addEnvironment("location", "bengaluru");

  var state2 = simulator.defineState("InLasVegas")
  	.addResource("id", "account")
  	.addResource("amount", "50000")
	.addAction("id", "atmWithdrawal")
	.addEnvironment("location", "las-vegas");

  var bengaluruMarkovChain = [
  	new StateTransition("InBengaluru", "InBengaluru", 1 , 1000)
  ];

  var lasvegasMarkovChain = [
  	new StateTransition("InLasVegas", "InLasVegas", 1 , 1000)
  ];

  var resourceTemplate = new Resource();
  resourceTemplate.setAttribute("accountId","099");
  resourceTemplate.setAttribute("simulation","historyUserMove");
  var resourcePool = simulator.definePool(1, resourceTemplate);
  var subjectTemplate = new Subject();
  subjectTemplate.setAttribute("id","099");
  subjectTemplate.setAttribute("simulation","historyUserMove");
  var subjectPool = simulator.definePool(1, subjectTemplate);
  var subjectResourcePool = simulator.pair(resourcePool,subjectPool);
  simulator.defineActor("alice").stateTransition("InBengaluru", bengaluruMarkovChain);
  simulator.defineActor("trudy").stateTransition("InLasVegas", lasvegasMarkovChain);
}  
result=simulator;
logger.info("Done setup of simulation script")

