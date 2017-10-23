/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import lab.bds.bolt.IndexBolt;
import lab.bds.bolt.ParseBolt;
import lab.bds.conf.ESConfig;
import lab.bds.conf.REConfig;
import static lab.bds.lib.LoggerLib.LOG;
import lab.bds.obj.PropObj;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

/**
 *
 * @author leo
 */
public class TopoLib {

    final private static String SOURCE_STREAM = "KAFKA_SOURCE";
    final private static String BOLT_ONE = "A_PARSE";
    final private static String BOLT_TWO = "B_ENRICH";
    final private static String BOLT_THREE = "C_INDEX";
    final private static String BOLT_FOUR = "D_AGGRE";
    final private static String BOLT_FIVE = "E_CLEAN";
    final private static String BOLT_SIX = "F_STORE";

    private static StormTopology buildTopo(PropObj propObj) {

        String kafkaZkHosts = propObj.getKafkaZkHosts();
        String kafkaTopic = propObj.getKafkaTopic();
        String zkRoot = "";
        String clientId = "storm";

        BrokerHosts brokerHosts = new ZkHosts(kafkaZkHosts);
        SpoutConfig kafkaConf = new SpoutConfig(brokerHosts, kafkaTopic, zkRoot, clientId);
        kafkaConf.scheme = new SchemeAsMultiScheme(new StringScheme());

        /**
         * Build Topology: Load data -> Basic Parse -> Index elastic 5.4.1.
         */
        TopologyBuilder topoBuilder = new TopologyBuilder();
        topoBuilder.setSpout(SOURCE_STREAM, new KafkaSpout(kafkaConf), 1);
        topoBuilder.setBolt(BOLT_ONE, new ParseBolt()).globalGrouping(SOURCE_STREAM);
        topoBuilder.setBolt(BOLT_THREE, new IndexBolt()).shuffleGrouping(BOLT_ONE);

        return topoBuilder.createTopology();
    }

    /**
     * Load properties and Submit Topology.
     *
     * @param prop
     * @throws Exception
     */
    public static void submitTopo(Properties prop) throws Exception {

        PropObj propObj = new PropObj(prop);
        StormTopology stormTopo = buildTopo(propObj);
        Config config = new Config();

        config.put(Config.TOPOLOGY_TRIDENT_BATCH_EMIT_INTERVAL_MILLIS, propObj.getStormTopologyBatchIntervalMiliseconds());
        config.setNumWorkers(propObj.getStormWorkersNumber());
        config.setMaxTaskParallelism(propObj.getStormMaxTaskParallelism());

        /**
         * Put config for Elasticsearch.
         */
        config.put(ESConfig.ES_CLUSTER_NAME, propObj.getEsClusterName());
        config.put(ESConfig.ES_HOST, propObj.getEsHost());
        config.put(ESConfig.ES_PORT, propObj.getEsPort());
        config.put(ESConfig.ES_INDEX, propObj.getEsIndex());
        config.put(ESConfig.ES_TYPE, propObj.getEsType());

        /**
         * Put config for Redis.
         */
        config.put(REConfig.RE_MODE, propObj.getRedisMode());
        config.put(REConfig.RE_HOST, propObj.getRedisHost());
        config.put(REConfig.RE_PORT, propObj.getRedisPort());
        config.put(REConfig.RE_PREFIX, propObj.getRedisPrefix());

        if (propObj.getStormExecutionMode().equals("cluster")) {

            config.put(Config.NIMBUS_HOST, propObj.getStormNimbusHost());
//            config.put(Config.NIMBUS_SEEDS, nimbusHost);
            config.put(Config.NIMBUS_THRIFT_PORT, propObj.getStormNimbusPort());
            config.put(Config.STORM_ZOOKEEPER_PORT, parseZkPort(propObj.getStormZkHosts()));
            config.put(Config.STORM_ZOOKEEPER_SERVERS, parseZkHosts(propObj.getStormZkHosts()));
            config.put(Config.TOPOLOGY_ACKER_EXECUTORS, propObj.getStormTopoloygyAckerExecutors());

            StormSubmitter.submitTopology(propObj.getStormTopologyName(), config, stormTopo);
            LOG.info("Submit Topology Cluster is completed.");
        } else {
            int localTimeExecution = propObj.getStormLocalExecutionTime();
            String topoName = propObj.getStormTopologyName();

            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology(topoName, config, stormTopo);
//            Thread.sleep(localTimeExecution);
//            localCluster.killTopology(topoName);
//            localCluster.shutdown();
//            System.exit(0);

            LOG.info("Submit Topology Local is completed.");
        }
    }

    public static List<String> parseZkHosts(String zkNodes) {
        String[] hostsAndPorts = zkNodes.split(",");
        List<String> hosts = new ArrayList<>(hostsAndPorts.length);
        for (int i = 0; i < hostsAndPorts.length; i++) {
            hosts.add(i, hostsAndPorts[i].split(":")[0]);
        }
        return hosts;
    }

    public static int parseZkPort(String zkNodes) {
        String[] hostsAndPorts = zkNodes.split(",");
        int port = Integer.parseInt(hostsAndPorts[0].split(":")[1]);
        return port;
    }

}
