/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.bolt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import static org.elasticsearch.common.xcontent.XContentType.JSON;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import lab.bds.obj.IndexObj;
import lab.bds.conf.ESConfig;
import static lab.bds.lib.LoggerLib.LOG;
import static lab.bds.lib.FunctionLib.GetIndexESTimesHost;

/**
 *
 * @author leo
 */
public class IndexBolt implements IBasicBolt {

    TransportClient _client;
    private String esClusterName;
    private String esHost;
    private int esPort;
    private String esIndex;
    private String esType;

    @Override
    public void prepare(Map conf, TopologyContext toc) {
        try {

            esClusterName = (String) conf.get(ESConfig.ES_CLUSTER_NAME);
            esHost = (String) conf.get(ESConfig.ES_HOST);
            esPort = ((Long) conf.get(ESConfig.ES_PORT)).intValue();
            esIndex = (String) conf.get(ESConfig.ES_INDEX);
            esType = (String) conf.get(ESConfig.ES_TYPE);

            Settings settings = Settings.builder()
                    .put("cluster.name", esClusterName).build();

            _client = new PreBuiltTransportClient(settings);

            for (String host : esHost.split(",")) {
                _client.addTransportAddress(
                        new InetSocketTransportAddress(
                                InetAddress.getByName(host),
                                esPort));
            }

        } catch (UnknownHostException ex) {
            LOG.warn("310_UnknownHostException", ex);
        }
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector boc) {

        String dataIndex = input.getValue(0).toString();
        Gson gson = new GsonBuilder().create();
        IndexObj obj = gson.fromJson(dataIndex, IndexObj.class);

        String indexName = GetIndexESTimesHost(esIndex, obj.timeshost, obj.timestamp);
        if (!dataIndex.isEmpty()) {
            _client.prepareIndex(indexName, esType)
                    .setSource(dataIndex, JSON)
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
