/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.scm;

import java.util.Properties;

/**
 *
 * @author leo
 */
public class PropSchema {

    private String zkHosts;
    private String kafkaZkHosts;
    private String kafkaTopic;
    private String stormZkHosts;

    private int stormWorkersNumber;
    private int stormMaxTaskParallelism;
    private String stormExecutionMode;
    private String stormTopologyName;
    private int stormTopologyBatchIntervalMiliseconds;
    private int stormTopoloygyAckerExecutors;

    private int stormLocalExecutionTime;
    private String stormNimbusHost;
    private String stormNimbusSeeds;
    private int stormNimbusPort;

    private String esClusterName;
    private String esHost;
    private int esPort;
    private String esIndex;
    private String esType;

    private String redisMode;
    private String redisHost;
    private int redisPort;
    private String redisPrefix;

    private String apiUAHost;
    private int apiUAPort;
    private String apiUAURN;

    private String apiIPHost;
    private int apiIPPort;
    private String apiIPURN;

    public PropSchema(Properties prop) {

        /**
         * Config Kakfa.
         */
        this.zkHosts = prop.getProperty("zookeeper.hosts", "localhost:2181");
        this.kafkaZkHosts = prop.getProperty("kafka.zookeeper.hosts", "localhost:2181");
        this.kafkaTopic = prop.getProperty("kafka.topic", "bdslab");

        /**
         * Config Storm.
         */
        this.stormZkHosts = prop.getProperty("storm.zookeeper.hosts", "localhost:2181");
        this.stormWorkersNumber = Integer.parseInt(prop.getProperty("storm.workers.number", "2"));
        this.stormMaxTaskParallelism = Integer.parseInt(prop.getProperty("storm.max.task.parallelism", "2"));
        this.stormExecutionMode = prop.getProperty("storm.execution.mode", "local");
        this.stormTopologyName = prop.getProperty("storm.topology.name", "BdsLabTopo");
        this.stormTopologyBatchIntervalMiliseconds
                = Integer.parseInt(prop.getProperty("storm.topology.batch.interval.miliseconds", "2000"));
        this.stormTopoloygyAckerExecutors = Integer.parseInt(prop.getProperty("storm.topoloygy.acker.executors", "0"));
        this.stormLocalExecutionTime = Integer.parseInt(prop.getProperty("storm.local.execution.time", "20000"));
        this.stormNimbusHost = prop.getProperty("storm.nimbus.host", "localhost");
        this.stormNimbusSeeds = prop.getProperty("storm.nimbus.seeds", "localhost");
        this.stormNimbusPort = Integer.parseInt(prop.getProperty("storm.nimbus.port", "6627"));

        /**
         * Config ES.
         */
        this.esClusterName = prop.getProperty("elasticsearch.cluster.name", "elasticsearch");
        this.esHost = prop.getProperty("elasticsearch.host", "localhost");
        this.esPort = Integer.parseInt(prop.getProperty("elasticsearch.port", "9300"));
        this.esIndex = prop.getProperty("elasticsearch.index", "bds");
        this.esType = prop.getProperty("elasticsearch.type", "access");

        /**
         * Config Redis.
         */
        this.redisMode = prop.getProperty("redis.mode", "local");
        this.redisHost = prop.getProperty("redis.host", "localhost");
        this.redisPort = Integer.parseInt(prop.getProperty("redis.port", "6379"));
        this.redisPrefix = prop.getProperty("redis.prefix", "bdslab");

        /**
         * Config API.
         */
        this.apiUAHost = prop.getProperty("api.ua.host", "localhost");
        this.apiUAPort = Integer.parseInt(prop.getProperty("api.ua.port", "31337"));
        this.apiUAURN = prop.getProperty("api.ua.urn", "/v1/ua/info");

        this.apiIPHost = prop.getProperty("api.ip.host", "localhost");
        this.apiIPPort = Integer.parseInt(prop.getProperty("api.ip.port", "31337"));
        this.apiIPURN = prop.getProperty("api.ip.urn", "/v1/ip/info");

    }

    public String getZkHosts() {
        return zkHosts;
    }

    public void setZkHosts(String zkHosts) {
        this.zkHosts = zkHosts;
    }

    public String getKafkaZkHosts() {
        return kafkaZkHosts;
    }

    public void setKafkaZkHosts(String kafkaZkHosts) {
        this.kafkaZkHosts = kafkaZkHosts;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }

    public String getStormZkHosts() {
        return stormZkHosts;
    }

    public void setStormZkHosts(String stormZkHosts) {
        this.stormZkHosts = stormZkHosts;
    }

    public String getEsHost() {
        return esHost;
    }

    public void setEsHost(String esHost) {
        this.esHost = esHost;
    }

    public int getEsPort() {
        return esPort;
    }

    public void setEsPort(int esPort) {
        this.esPort = esPort;
    }

    public String getEsClusterName() {
        return esClusterName;
    }

    public void setEsClusterName(String esClusterName) {
        this.esClusterName = esClusterName;
    }

    public String getEsIndex() {
        return esIndex;
    }

    public void setEsIndex(String esIndex) {
        this.esIndex = esIndex;
    }

    public String getEsType() {
        return esType;
    }

    public void setEsType(String esType) {
        this.esType = esType;
    }

    public int getStormWorkersNumber() {
        return stormWorkersNumber;
    }

    public void setStormWorkersNumber(int stormWorkersNumber) {
        this.stormWorkersNumber = stormWorkersNumber;
    }

    public int getStormMaxTaskParallelism() {
        return stormMaxTaskParallelism;
    }

    public void setStormMaxTaskParallelism(int stormMaxTaskParallelism) {
        this.stormMaxTaskParallelism = stormMaxTaskParallelism;
    }

    public String getStormExecutionMode() {
        return stormExecutionMode;
    }

    public void setStormExecutionMode(String stormExecutionMode) {
        this.stormExecutionMode = stormExecutionMode;
    }

    public String getStormTopologyName() {
        return stormTopologyName;
    }

    public void setStormTopologyName(String stormTopologyName) {
        this.stormTopologyName = stormTopologyName;
    }

    public int getStormTopologyBatchIntervalMiliseconds() {
        return stormTopologyBatchIntervalMiliseconds;
    }

    public void setStormTopologyBatchIntervalMiliseconds(int stormTopologyBatchIntervalMiliseconds) {
        this.stormTopologyBatchIntervalMiliseconds = stormTopologyBatchIntervalMiliseconds;
    }

    public int getStormLocalExecutionTime() {
        return stormLocalExecutionTime;
    }

    public int getStormTopoloygyAckerExecutors() {
        return stormTopoloygyAckerExecutors;
    }

    public void setStormTopoloygyAckerExecutors(int stormTopoloygyAckerExecutors) {
        this.stormTopoloygyAckerExecutors = stormTopoloygyAckerExecutors;
    }

    public void setStormLocalExecutionTime(int stormLocalExecutionTime) {
        this.stormLocalExecutionTime = stormLocalExecutionTime;
    }

    public String getStormNimbusHost() {
        return stormNimbusHost;
    }

    public void setStormNimbusHost(String stormNimbusHost) {
        this.stormNimbusHost = stormNimbusHost;
    }

    public String getStormNimbusSeeds() {
        return stormNimbusSeeds;
    }

    public void setStormNimbusSeeds(String stormNimbusSeeds) {
        this.stormNimbusSeeds = stormNimbusSeeds;
    }

    public int getStormNimbusPort() {
        return stormNimbusPort;
    }

    public void setStormNimbusPort(int stormNimbusPort) {
        this.stormNimbusPort = stormNimbusPort;
    }

    public String getRedisMode() {
        return redisMode;
    }

    public void setRedisMode(String redisMode) {
        this.redisMode = redisMode;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public String getRedisPrefix() {
        return redisPrefix;
    }

    public void setRedisPrefix(String redisPrefix) {
        this.redisPrefix = redisPrefix;
    }

    public String getApiUAHost() {
        return apiUAHost;
    }

    public void setApiUAHost(String apiUAHost) {
        this.apiUAHost = apiUAHost;
    }

    public int getApiUAPort() {
        return apiUAPort;
    }

    public void setApiUAPort(int apiUAPort) {
        this.apiUAPort = apiUAPort;
    }

    public String getApiUAURN() {
        return apiUAURN;
    }

    public void setApiUAURN(String apiUAURN) {
        this.apiUAURN = apiUAURN;
    }

    public String getApiIPHost() {
        return apiIPHost;
    }

    public void setApiIPHost(String apiIPHost) {
        this.apiIPHost = apiIPHost;
    }

    public int getApiIPPort() {
        return apiIPPort;
    }

    public void setApiIPPort(int apiIPPort) {
        this.apiIPPort = apiIPPort;
    }

    public String getApiIPURN() {
        return apiIPURN;
    }

    public void setApiIPURN(String apiIPURN) {
        this.apiIPURN = apiIPURN;
    }

}
