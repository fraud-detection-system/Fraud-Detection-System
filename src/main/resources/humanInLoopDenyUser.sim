//----------------------------------------------
//Configuration
//----------------------------------------------


//----------------------------------------------

logger.info("Running the human in loop simulation")

var importIt = new JavaImporter(java.lang.String,java.util,java.io,java.time,com.stream.simulation,com.stream.fraud.model);  
with (importIt) {  
  var simulator = new Simulator();

  var entity = new Entity();
  entity.setAttribute("type", "denyUser");
  entity.setAttribute("id","024");
  entity.setAttribute("name", "A rogue user");
  entity.setAttribute("denyUsers", "true");
  simulator.newReferenceData(entity);
  simulator.end()
}  
var result="success";
logger.info("Done - simulation script")

