/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.bolt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;
import com.lambdaworks.redis.cluster.RedisClusterClient;
import com.lambdaworks.redis.cluster.api.StatefulRedisClusterConnection;
import java.util.Map;
import lab.bds.conf.REConfig;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import lab.bds.obj.EnrichObj;
import static lab.bds.lib.FunctionLib.GetRawEnrich;
import static lab.bds.lib.LoggerLib.LOG;

/**
 *
 * @author leo
 */
public class EnrichBolt implements IBasicBolt {

    private static RedisClient redisClient;
    private static StatefulRedisConnection<String, String> connection;
    private static RedisCommands<String, String> syncCmds;//syncCommands;
    private static RedisClusterClient redisClusterClient;
    private static StatefulRedisClusterConnection<String, String> clusterConnection;

    private String redisHost;
    private Integer redisPort;
    private String redisMode;
    private String redisPrefix;
    private String redisURI;

    @Override
    public void prepare(Map conf, TopologyContext tc) {

        redisHost = (String) conf.get(REConfig.RE_HOST);
        redisPort = ((Integer) conf.get(REConfig.RE_PORT));
        redisMode = (String) conf.get(REConfig.RE_MODE);
        redisPrefix = (String) conf.get(REConfig.RE_PREFIX);
        redisURI = "redis://" + redisHost + ":" + redisPort;

        if (redisMode.equals("local")) {
            redisClient = RedisClient.create(redisURI);
            connection = redisClient.connect();
            syncCmds = connection.sync();
        } else {
            redisClusterClient = RedisClusterClient.create(redisURI);
            clusterConnection = redisClusterClient.connect();
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("message"));
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector boc) {
        LOG.debug(input.getString(0));
        String dataInput = input.getString(0);
        Gson gson = new GsonBuilder().create();
        EnrichObj obj = gson.fromJson(dataInput, EnrichObj.class);

        String dataOut = GetRawEnrich(obj);
        if (dataOut != null) {
            boc.emit(new Values(dataOut));
        }
    }

    @Override
    public void cleanup() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

}
