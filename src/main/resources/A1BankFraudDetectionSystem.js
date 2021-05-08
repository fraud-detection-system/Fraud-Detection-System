//Define a fraud detection system
var systemName = "A1 Bank Fraud Detection System"
logger.info("setting up "+systemName)

var importIt = new JavaImporter(java.lang.String,java.util,java.io,java.time,com.stream);  
with (importIt) {  
  logger.info("setting up "+systemName)
var afraudDetectionSystem = fraudDetectionSystem;

  //The attributes that will be taken as features for ML. Rest will be ignored.
  afraudDetectionSystem.addFeatureAttribute("resource", "id", "categorical")
                      .addFeatureAttribute("action","id", "categorical")
                      .addFeatureAttribute("resource","amount", "double")
                      .addFeatureAttribute("environment","location-diff", "double")
                      .addFeatureAttribute("resource","amount-diff", "double");
        
  //Attributes for swimlane. Each swimlanes analyzes events in it, learns from it and maintains state.
  afraudDetectionSystem.addSwimlaneAttribute("resource", "id")
                      .addSwimlaneAttribute("resource", "accountId");
        
  //Attributes for keeping history. These keys decide the identity of the event. Same event may have two histories based on two keys.
  afraudDetectionSystem.addHistoryKeyAttribute("resource", "id", "5")
                      .addHistoryKeyAttribute("subject", "id", "5");
        
  //Attributes for keeping history. These keys decide the identity of the event. Same event may have two histories based on two keys.
  afraudDetectionSystem.addHistoryDiffAttribute("environment", "location", "diff", "environment", "location-diff")
                      .addHistoryDiffAttribute("environment", "time", "diff", "environment", "time-diff")
                      .addHistoryDiffAttribute("resource", "amount", "diff", "resource", "amount-diff");

}  
logger.info("Done setup of "+systemName)
result=fraudDetectionSystem;

