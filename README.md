# BDSLab
docker build -f env/local/bdslocal/bdscentos/Dockerfile -t bdscentos:latest env/local/bdslocal/bdscentos

# cd src/bdslab/env/local/bdslocal/bdscentos
docker-compose up

# Config before run
bin/kafka-topics.sh --create --topic bdslab --zookeeper localhost:2181 --replication-factor 1 --partitions 1
bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic bdslab
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic bdslab
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic bdslab
bin/kafka-topics.sh --list --zookeeper localhost:2181