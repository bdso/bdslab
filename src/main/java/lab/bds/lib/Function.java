/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.lib;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import lab.bds.lib.FormatLib.ParseFormat;
import static lab.bds.lib.LoggerLib.LOG;
import lab.bds.obj.ParseObj;

/**
 *
 * @author leo
 */
public class Function {

    private static String decodeRequest;
    private static String decodeReferrer;

    public static String GetIndexNameES(String esIndex) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
        String formatDate = ft.format(dNow);
        return esIndex + "-" + formatDate;
    }

    public static String GetRawParse(ParseFormat obj) {

        Gson gson = new Gson();
        ParseObj jsObj = new ParseObj();

        try {
            decodeRequest = URLDecoder.decode(obj.request, "UTF-8");
            decodeReferrer = URLDecoder.decode(obj.referrer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.info("150_UnsupportedEncodingException_Excep: " + e
                    + " 151_UnsupportedEncodingException_Agent" + obj.request);
            return null;
        } catch (Exception ex) {
            LOG.info("160_Exception_Excep: " + ex
                    + " 161_Exception_Agent: " + obj.request);
            return null;
        }

        jsObj.setTimeshost(obj.timeshost);
        jsObj.setHost(obj.host);
        jsObj.setType(obj.type);
        jsObj.setVhost(obj.vhost);
        jsObj.setClientip(obj.clientip);
        jsObj.setTimestamp(obj.timestamp);
        jsObj.setVerb(obj.verb);
        jsObj.setRequest(decodeRequest);
        jsObj.setResponse(Integer.parseInt(obj.response));
        jsObj.setBytes(Long.parseLong(obj.bytes));
        jsObj.setReferrer(decodeReferrer);
        jsObj.setAgent(obj.agent);
        jsObj.setRequest_duration(Float.parseFloat(obj.request_duration));
        jsObj.setCache_status(obj.cache_status);

        /**
         * Json Object send ParserBolt.
         *
         */
        String sObj = gson.toJson(jsObj);
        return sObj;
    }
}
