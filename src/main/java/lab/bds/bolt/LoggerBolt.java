/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.bolt;

import org.apache.log4j.Logger;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 *
 * @author leo
 */
public class LoggerBolt extends BaseBasicBolt {

    private static final Logger LOG = Logger.getLogger(LoggerBolt.class);

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("message"));
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector boc) {
        LOG.info(input.getString(0));
        String dataIn=input.getString(0);
           if (dataIn != null) {
                boc.emit(new Values(dataIn));
            }
    }

}
