load("src/main/resources/seniorCitizenScenario.js");

simulator.startActors("aliceGrandMa", subjectResourcePool, 100, 100);
simulator.pauseAndPrompt("End the simulation?");
simulator.end()
