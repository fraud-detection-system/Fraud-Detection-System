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
  	.addResource("amount", "(new java.util.Random()).nextInt(10000-0) + 0")
	.addAction("id", "atmWithdrawal")
	.addEnvironment("location", "bengaluru");

  var state2 = simulator.defineState("InLasVegas")
  	.addResource("id", "account")
  	.addResource("amount", "(new java.util.Random()).nextInt(10000-0) + 0")
	.addAction("id", "atmWithdrawal")
	.addEnvironment("location", "las-vegas");

  var bengaluruMarkovChain = [
  	new StateTransition("InBengaluru", "InBengaluru", 1 , 1000)
  ];

  var lasvegasMarkovChain = [
  	new StateTransition("InLasVegas", "InLasVegas", 1 , 1000)
  ];

  var resourceTemplate = new Resource();
  resourceTemplate.setAttribute("accountId","024");
  resourceTemplate.setAttribute("simulation","denyUser");
  var resourcePool = simulator.definePool(1, resourceTemplate);
  var subjectTemplate = new Subject();
  subjectTemplate.setAttribute("id","024");
  subjectTemplate.setAttribute("IPAddress","172.1.1.1");
  subjectTemplate.setAttribute("simulation","denyUser");
  var subjectPool = simulator.definePool(1, subjectTemplate);
  var subjectResourcePool = simulator.pair(resourcePool,subjectPool);
  simulator.defineActor("alice").stateTransition("InBengaluru", bengaluruMarkovChain);
  simulator.defineActor("trudy").stateTransition("InLasVegas", lasvegasMarkovChain);
}  
result=simulator;
logger.info("Done setup of simulation script")

