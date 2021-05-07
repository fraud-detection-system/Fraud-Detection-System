mode = "evaluation";
load("src/main/resources/stateUserMoveScenario.js");

simulator.startActors("trudy", subjectResourcePool, 1, 1);
simulator.pauseAndPrompt("End the simulation?");
simulator.end()
