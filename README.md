# Fraud Detection System

A new PAIR architecture for fraud detection system for new age internet economy that takes us from following from to to,


| From|to |
|----|---|
| *Large delay txns, online txns by privileged few, a small percentage of fraud* | *Instant txns, sophisticated attacks (OTP stealing), adversarial attacks and ultra-scale, online txns by non-tech savvy people* |
| *“Write off approach”, after the fact, actor focussed approach* | *Realtime, streaming, online learning with Human In the Loop* |
| *Single well-tuned algorithm* | *Multiple algorithms (potentially weak learners), Cold start, Unseen situations, high accuracy* |
| *Siloed, difficult to change and integrate* | *Modular, Integrative, easy to experiment & change* |


## Abstract

Key Words: Fraud, Realtime, Streaming, Scale, PAIR, MASSES, Simulator, SBE, Modularity, XACML, EAV, Markov-chain

*A fast, adaptive and effective fraud detection system architecture, PAIR, is proposed and demonstrated. It allows multiple transactional data streams and supporting reference data streams with information fusion support. An ensemble of ML algorithms, with a combination of supervised/unsupervised, stream learning/offline learning and rule based are supported. The output is actionable, with multiple delivery mechanisms, to bring human in the loop. The system is responsive in reacting before the transaction completes and also in adapting to evolving situations. Later is enabled by modularity in the design to allow changes on the fly. New stream processing can be defined in a newly designed language. System provides for a set of integrative approaches, ability to define features, maintain history, compare with it and allow multiple separate processing at the same time. System is designed for scale at runtime and scale of development. A MASSES Simulator was built to validate the system from functional and non-functional (scale, response time etc.) point of view. A language for creating multiple simultaneous simulations, on the philosophy of specification by example, was built.*

[A modular, scalable and realtime fraud detection system](https://github.com/fraud-detection-system/Fraud-Detection-System/blob/main/docs/A%20modular%2C%20scalable%20and%20realtime%20fraud%20detection%20system.pdf) - a MTech Dissertation outlining the system's objectives and design.

[Fraud Detection System - slideshare](https://www.slideshare.net/baladutt/fraud-detection-system-249354277)


# Streaming with Flink

Read the medium article - [Streaming system tutorial with Flink and Kafka](https://medium.com/@bala.dutt/streaming-system-tutorial-with-flink-and-kafka-9c445e4daa6c)

## Setup

### Apache Kafka and Zookeeper Installation
####  1. Install Java and Maven
These are pre-requisites for this project.
####  2. ZooKeeper Framework Installation

2.1 Download the latest version of Apache ZooKeeper from:
                 
                 http://zookeeper.apache.org/releases.html
              
2.2 Extract tar file using the following command
```$ cd opt/
$ tar -zxf zookeeper-<version>.tar.gz
$ cd zookeeper-<version>
$ mkdir data
```
2.3. Create Configuration File
   ``` $ vi conf/zoo.cfg
    tickTime=2000
    dataDir=/path/to/zookeeper/data
    clientPort=2181
    initLimit=5
    syncLimit=2   
````

2.4 Start ZooKeeper Server 

    $ bin/zkServer.sh start

2.5 Start CLI

    $ bin/zkCli.sh 
    
#### 3. Apache Kafka Installation

3.1 Download Kafka
To install Kafka on your machine, start here,

    https://kafka.apache.org/downloads       

3.2 Extract the tar file

```$ cd opt/
$ tar -zxf kafka_<version> tar.gz
$ cd kafka_<version>
```

3.3 Start Server
    $ bin/kafka-server-start.sh config/server.properties

#### 4. Try below commands to check if Kafka is installed and is running properly.
    
```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka
bin/kafka-topics.sh --list --zookeeper localhost:2181
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Hello-Kafka
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Hello-Kafka --from-beginning
```

Make sure start-local-stream.sh is updated with versions of ZooKeeper and Kafka

## How to run

This system needs the sub-systems to be running.

   * Apache Kafka
   * Apache Zookeeper
   * Flink based Stream server
   * Event generator to simulate real life events
   
Run following commands to bring these systems up

	Run ./start-local-stream.sh         -> This brings us first two

	mvn clean install                   -> This brings up third

	generate events from TelecomEventGenerator  -> For fourth component

 For generating eclipse project - run
 
 	mvn eclipse:clean eclipse:eclipse  
  
If you use vim, set this in .vimrc

	
	autocmd BufNewFile,BufRead *.fdsp set syntax=javascript
	
	autocmd BufNewFile,BufRead *.sim set syntax=javascript
## Details of implementation

   * Focus on com.stream.telecom package
   * There are 5 streams as shown in TelecomUsageWorkflow
   * Look in alerts* folder for output 
