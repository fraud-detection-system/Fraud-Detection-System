# Local stream

## How to setup

Follow tutorial for zookeeper and kafka installation
	https://www.tutorialspoint.com/apache_kafka/apache_kafka_installation_steps.htm

Commands used

cd ..
tar -zxf ~/Downloads/apache-zookeeper-3.6.2-bin.tar.gz 
cd apache-zookeeper-3.6.2-bin/
mkdir data
vi conf/zoo.cfg

tar -zxf  ~/Downloads/kafka_2.13-2.6.0.tgz 
cd kafka_2.13-2.6.0/
cd ../apache-zookeeper-3.6.2-bin/
./bin/zkServer.sh start
cd -
bin/kafka-server-start.sh config/server.properties
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka
bin/kafka-topics.sh --list --zookeeper localhost:2181
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Hello-Kafka
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Hello-Kafka --from-beginning


Code for Kafka producer:
	https://dzone.com/articles/kafka-producer-and-consumer-example

## How to run
	Run ./start-local-stream.sh

	mvn clean install

	generate events from TelecomEventGenerator

# Real Time Data Processing Pipeline

    Detail documentation including requirements and Design document is "docs/BDS Assignment 3 - Real-Time Data Processing Pipeline.pdf"

# Instructions for running

    This system needs 4 sub-systems to be running.

    * Flink
    * Postgres and Adminer
    * QBO source system
    * RTDPP (this particular system)


    * Postgres and Adminer
	* docker-compose -f docker.yaml up
      	* Reference - https://hub.docker.com/_/postgres
      	* Adminer is running here - http://localhost:8080/
      	* DB is postgres
	* Username and password for postgres are in docker.yaml
    * Flink
       * Install Flink
       * Start with ./bin/start-cluster.sh
       * To stop ./bin/stop-cluster.sh
    * QBO source system
       * There is a jks file not part of the zip that is needed to connect to QBO source system.
         This can be done only in Intuit network.
       * Input is also done through QBO's web application as a Customer. Like, create an Invoice.
    * RTDPP
       * mvn clean install to compile project
       * run App.java as java application from IDE (Eclipse). Currently fat jar building for flink submission is disabled.


    What to look for?

    * DB records are here - http://localhost:8080/?pgsql=db&username=postgres&db=postgres&ns=public&select=Entity
    * Assuming all output of eclipse IDE goes to eclipse.log (Can be done in Common tab or Run Configuration for App.java). Sample.log contains a sample log from run.
       * grep "Opened database successfully" eclipse.log
       * grep OperationalMetricSink eclipse.log 
       * grep BusinessMetricSink eclipse.log
       * grep AlertSink eclipse.log
       * grep "CompanyId fraud" eclipse.log

# Details of implementation

    * Currently we show fraud when,
       * CompanyIds match a criteria
       * Invoice amount in a window is more than a threshold
       * Count of transactions in a window for a company and transaction type is beyond threshold
    * There are 7 pipelines
       * These pipeline output alerts, business metrics, operational metrics and traces.
       * Some of them are windowed and some not
       * These workflows are in Workflow.java
    * QBO data is captured by CDC (Change Data Capture), and then put on Kafka. We read from there. In target state, we would capture CDC directly.
       * A sample QBO CDC record is provided in qboSampleEvent.json
       
