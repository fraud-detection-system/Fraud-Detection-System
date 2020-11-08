# Local stream

## Apache Kafka and Zookeeper Installation
####  1. Install Java
####  2. ZooKeeper Framework Installation

2.1 Download the latest version of Apache ZooKeeper from:
                 
                 http://zookeeper.apache.org/releases.html
              
2.2 Extract tar file using the following command
```$ cd opt/ <br>
$ tar -zxf zookeeper-3.4.6.tar.gz <br>
$ cd zookeeper-3.4.6<br>
$ mkdir data <br>
```
2.3. Create Configuration File <br>
   ``` $ vi conf/zoo.cfg <br>
    tickTime=2000 <br>
    dataDir=/path/to/zookeeper/data <br>
    clientPort=2181 <br>
    initLimit=5 <br>
    syncLimit=2             <br>    
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

4. Test sample code for Kafka producer:
    https://dzone.com/articles/kafka-producer-and-consumer-example
    
```bin/kafka-server-start.sh config/server.properties
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka
bin/kafka-topics.sh --list --zookeeper localhost:2181
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Hello-Kafka
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Hello-Kafka --from-beginning
```

## How to run
	Run ./start-local-stream.sh

	mvn clean install

	generate events from TelecomEventGenerator

# Instructions for running

    This system needs the sub-systems to be running.
    * Apache Kafka
    * Apache Zookeeper
    * Flink
       * Install Flink
       * Start with ./bin/start-cluster.sh
       * To stop ./bin/stop-cluster.sh
  
# Details of implementation

    * There are 5 streams as shown in TelecomUsageWorkflow
    * For building eclipse project - run ```mvn eclipse:clean eclipse:eclipse
