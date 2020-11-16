# Streaming with Flink

## Setup

### Apache Kafka and Zookeeper Installation
####  1. Install Java and Maven
These are pre-requisites for this project.
####  2. ZooKeeper Framework Installation

2.1 Download the latest version of Apache ZooKeeper from:
                 
                 http://zookeeper.apache.org/releases.html
              
2.2 Extract tar file using the following command
```$ cd opt/
$ tar -zxf zookeeper-3.4.6.tar.gz
$ cd zookeeper-3.4.6
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
To install Kafka on your machine, click on the below link −

    https://www.apache.org/dyn/closer.cgi?path=/kafka/0.9.0.0/kafka_2.11-0.9.0.0.tgz        

3.2 Extract the tar file

```$ cd opt/ <br>
$ tar -zxf kafka_2.11.0.9.0.0 tar.gz <br>
$ cd kafka_2.11.0.9.0. <br>
```
3.3 Start Server
    $ bin/kafka-server-start.sh config/server.properties

4. Try below commands to check if Kafka is installed and is running properly.
    
```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka
bin/kafka-topics.sh --list --zookeeper localhost:2181
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Hello-Kafka
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Hello-Kafka --from-beginning
```

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
  
# Details of implementation

   * Focus on com.stream.telecom package
   * There are 5 streams as shown in TelecomUsageWorkflow
   * Look in alerts* folder for output 
