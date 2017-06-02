/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.bolt;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 *
 * @author leo
 */
public class IndexBolt implements IBasicBolt {

    private static final Logger LOG = Logger.getLogger(LoggerBolt.class);
    TransportClient _client;

    @Override
    public void prepare(Map arg0, TopologyContext arg1) {
        try {
            _client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        } catch (UnknownHostException ex) {
            LOG.warn("310_UnknownHostException", ex);
        }
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String json = "{"
                + "\"user\":\"kimchy\","
                + "\"postDate\":\"2013-01-30\","
                + "\"message\":\"trying out Elasticsearch\""
                + "}";
        String dataIndex = json;

        String indexName = "test";
        if (!dataIndex.isEmpty()) {
            _client.prepareIndex(indexName, "access")
                    .setSource(dataIndex)
                    .execute()
                    .actionGet();
        }

    }

    @Override
    public void cleanup() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

}
