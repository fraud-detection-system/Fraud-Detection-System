#!/usr/bin/env bash

ROOTDIR="${BASH_SOURCE-$0}"
ROOTDIR="$(dirname "${ZOOBIN}")"
ROOTDIR="$(cd "${ZOOBIN}"; pwd)"
echo $ROOTDIR

ZOOKEEPER_HOME=$ROOTDIR/../apache-zookeeper-3.6.2-bin
KAFKA_HOME=$ROOTDIR/../kafka_2.13-2.6.0/
$ZOOKEEPER_HOME/bin/zkServer.sh start

OLDKAFKA_OPTS="$KAFKA_OPTS"
#export KAFKA_OPTS="$KAFKA_OPTS -javaagent:/Users/bdutt/.m2/repository/io/prometheus/jmx/jmx_prometheus_javaagent/0.15.0/jmx_prometheus_javaagent-0.15.0.jar=7072:$PWD/kafka-2_0_0.yml"

$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties &
export KAFKA_OPTS="$OLDKAFKA_OPTS"

sleep 10

$KAFKA_HOME/bin/kafka-topics.sh --list --zookeeper localhost:2181


$ROOTDIR/../pushgateway-1.4.0.darwin-amd64/pushgateway &
$ROOTDIR/../prometheus-2.26.0.darwin-amd64/prometheus &

#../kafka_2.13-2.6.0/bin/kafka-server-stop.sh
#../kafka_2.13-2.6.0/bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic txnevents --config retention.ms=600000
#../kafka_2.13-2.6.0/bin/zookeeper-shell.sh localhost:2181 get /brokers/ids
#../kafka_2.13-2.6.0/bin/zookeeper-shell.sh localhost:2181 ls /brokers/topics
#../kafka_2.13-2.6.0/bin/zookeeper-shell.sh localhost:2181 get /brokers/ids/0
#../kafka_2.13-2.6.0/bin/kafka-run-class.sh kafka.tools.GetOffsetShell --topic txnevents --time -1 --offsets 1  --broker-list localhost:9092 | awk -F  ":" '{sum += $3} END {print sum}'

#kafkacat -b localhost:9092 -t txnevents -p 0 -o -10 -e
#kafkacat -b localhost:9092 -t txnevents -o -100

#kafkacat -b localhost:9092 -t accessEvents -o -100
#kafkacat -b localhost:9092 -t fraudAccessEvents -o -100
