mode = "evaluation";
load("src/main/resources/historyUserMoveScenario.js");

simulator.startActors("alice", subjectResourcePool, 100, 100);
simulator.pauseAndPrompt("End the simulation?");
simulator.end()
