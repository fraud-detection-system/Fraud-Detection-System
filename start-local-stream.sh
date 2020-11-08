#!/usr/bin/env bash

ROOTDIR="${BASH_SOURCE-$0}"
ROOTDIR="$(dirname "${ZOOBIN}")"
ROOTDIR="$(cd "${ZOOBIN}"; pwd)"
echo $ROOTDIR

ZOOKEEPER_HOME=$ROOTDIR/../apache-zookeeper-3.6.2-bin
KAFKA_HOME=$ROOTDIR/../kafka_2.13-2.6.0/
$ZOOKEEPER_HOME/bin/zkServer.sh start

$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties &

sleep 10

$KAFKA_HOME/bin/kafka-topics.sh --list --zookeeper localhost:2181
