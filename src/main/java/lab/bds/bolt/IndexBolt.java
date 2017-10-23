/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.bolt;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import static lab.bds.lib.Function.GetIndexNameES;
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
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        } catch (UnknownHostException ex) {
            LOG.warn("310_UnknownHostException", ex);
        }
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector boc) {
//        String json = "{" + "\"message\":\"" + input.getValue(0) + "\"" + "}";
//        String json = "{" + "\"message\":\"" + "Teo Di Hoc" + "\"" + "}";
//        String dataIndex = json;

        String dataIndex = input.getValue(0).toString();
        String name = "bdslab";
        String indexName = GetIndexNameES(name);
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
