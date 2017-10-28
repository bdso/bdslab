/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.bolt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import static lab.bds.lib.FunctionLib.GetRawParse;
import static lab.bds.lib.LoggerLib.LOG;
import lab.bds.obj.ParseObj;

/**
 *
 * @author leo
 */
public class ParseBolt implements IBasicBolt {

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("message"));
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector boc) {
        LOG.info(input.getString(0));
        String dataInput = input.getString(0);
        Gson gson = new GsonBuilder().create();
        ParseObj obj = gson.fromJson(dataInput, ParseObj.class);

        String dataOut = GetRawParse(obj);
        if (dataOut != null) {
            boc.emit(new Values(dataOut));
        }
    }

    @Override
    public void prepare(Map map, TopologyContext tc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
