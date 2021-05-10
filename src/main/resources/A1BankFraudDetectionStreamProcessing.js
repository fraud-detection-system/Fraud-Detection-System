//Define a fraud detection system
var systemName = "A1 Bank Fraud Detection Stream Processing"
logger.info("setting up "+systemName)

//Load all other specification files
load("src/main/resources/A1BankFraudDetectionStreamProcessingAlerts.js");

var importIt = new JavaImporter(java.lang.String,java.util,java.io,java.time,com.stream);  
with (importIt) {  
  logger.info("setting up "+systemName)

  //The attributes based on which events will be selected. Rest of the events will be ignored.
  fraudDetectionStreamProcessing.addSelectorAttribute("resource", "tenant", "A1Bank");

  //Add enrichments. Reference data corresponding to this will be used to enhance the event.
  fraudDetectionStreamProcessing.addEnrichment("subject", "id", "denyUsers", "id")
                                .addEnrichment("subject", "id", "user", "id")
                                .addEnrichment("subject", "id", "userConfiguration", "id")
                                .addEnrichment("subject", "IPAddress", "denyIPAddress", "id")
                                .addEnrichment("resource", "accountId", "account", "id");

  //Security handling
  fraudDetectionStreamProcessing.addSecurityProcessing("subject", "social-security-number", "remove")
                                .addSecurityProcessing("subject", "employeeId", "anonymize")
                                .addSecurityProcessing("subject", "credit-card", "mask-till-last-four");

  //The attributes that will be taken as features for ML. Rest will be ignored.
  fraudDetectionStreamProcessing.addFeatureAttribute("resource", "id", "categorical")
                                .addFeatureAttribute("action","id", "categorical")
                                .addFeatureAttribute("resource","amount", "double")
                                .addFeatureAttribute("environment","location-diff", "double")
                                .addFeatureAttribute("resource","amount-diff", "double");

  //The attributes that will be taken as features for ML. Rest will be ignored.
  fraudDetectionStreamProcessing.addML("HoeffdingTree")
                                .addML("HoeffdingAdaptiveTree")
                                .addML("NaiveBayes")
                                .addML("DecisionStump")
                                .addML("AdaHoeffdingOptionTree")
                                .addML("ExactSTORM")
                                .addML("ApproxSTORM")
                                .addML("MCOD")
                                .addML("SimpleCOD")
                                .addML("AbstractC");
        
  //Attributes for swimlane. Each swimlanes analyzes events in it, learns from it and maintains state.
  fraudDetectionStreamProcessing.addSwimlaneAttribute("resource", "id")
                                .addSwimlaneAttribute("resource", "accountId");
        
  //Attributes for keeping history. These keys decide the identity of the event. Same event may have two histories based on two keys.
  fraudDetectionStreamProcessing.addHistoryKeyAttribute("resource", "id", "5")
                                .addHistoryKeyAttribute("subject", "id", "5");
        
  //Attributes for keeping history. These keys decide the identity of the event. Same event may have two histories based on two keys.
  fraudDetectionStreamProcessing.addHistoryDiffAttribute("environment", "location", "diff", "environment", "location-diff")
                                .addHistoryDiffAttribute("environment", "time", "diff", "environment", "time-diff")
                                .addHistoryDiffAttribute("resource", "amount", "diff", "resource", "amount-diff");

  //Add post procesing enrichments. Reference data corresponding to this will be used to enhance the event.
  fraudDetectionStreamProcessing.addPostProcessEnrichment("subject", "id", "denyUsers", "id")
                                .addPostProcessEnrichment("subject", "id", "user", "id");
}  
logger.info("Done setup of "+systemName)
result=fraudDetectionStreamProcessing;

