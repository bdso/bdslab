/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;
import static lab.bds.lib.LoggerLib.LOG;
import static lab.bds.lib.TimeLib.INDEX_FORMAT_TIME;
import static lab.bds.lib.TimeLib.ISO_FORMAT_TIME;
import static lab.bds.lib.TimeLib.ISO_NGX_TIME;
import lab.bds.obj.EnrichObj;
import lab.bds.obj.ParseObj;
import lab.bds.obj.JWTObj;
import lab.bds.scm.EnrichSchema;
import lab.bds.scm.JWTVal;
import lab.bds.scm.ParseSchema;

/**
 *
 * @author leo
 */
public class FunctionLib {

    private static String decodeRequest;
    private static String decodeReferrer;

    public static String GetIndexES(String esIndex) {
        Date dateNow = new Date();
        String indexFormat = INDEX_FORMAT_TIME.format(dateNow);
        return esIndex + "-" + indexFormat;
    }

    public static String GetIndexESTimesHost(String esIndex, String timeshost, String timestamp) {

        try {
            Date dateTimeshost = ISO_FORMAT_TIME.parse(timeshost);
            String indexFormat = INDEX_FORMAT_TIME.format(dateTimeshost);
            return esIndex + "-" + indexFormat;
        } catch (ParseException ex) {
            LOG.error(ex);
            Date dateNow = new Date();
            String indexFormat = INDEX_FORMAT_TIME.format(dateNow);
            return esIndex + "-" + indexFormat;
        }
    }

    public static String GetIndexESTimesNgx(String esIndex, String timeshost, String timestamp) {

        try {
            Date dateTimeshost = ISO_NGX_TIME.parse(timestamp);
            String indexFormat = INDEX_FORMAT_TIME.format(dateTimeshost);
            return esIndex + "-" + indexFormat;
        } catch (ParseException ex) {
            LOG.error(ex);
            Date dateNow = new Date();
            String indexFormat = INDEX_FORMAT_TIME.format(dateNow);
            return esIndex + "-" + indexFormat;
        }
    }

    public static HashMap<String, String> JWTData(String data) {

        HashMap<String, String> HMJWT = new HashMap<>();
        try {
            String[] dataTmp = data.split("/");
            String[] dataPoint = dataTmp[1].split(Pattern.quote("."));
            String dataDecode = new String(DatatypeConverter.parseBase64Binary(dataPoint[1])) + "}";

            Gson gson = new GsonBuilder().create();
            JWTObj obj = gson.fromJson(dataDecode, JWTObj.class);
            HMJWT.put(JWTVal.USERID, obj.userId);
            HMJWT.put(JWTVal.ENTITYID, obj.entityId);
            HMJWT.put(JWTVal.TITLE, obj.title);
            HMJWT.put(JWTVal.CONTENTTYPE, obj.contentType);
            HMJWT.put(JWTVal.STREAMTYPE, obj.streamType);
            HMJWT.put(JWTVal.PUBLISHERID, obj.publisherId);
            HMJWT.put(JWTVal.SESSION, dataPoint[1]);
        } catch (JsonSyntaxException js) {
            LOG.info("250_JsonSyntaxException_Excep: " + js
                    + " 251_JsonSyntaxException_Data: " + data);
        } catch (Exception ex) {
            LOG.info("260_Exception_Excep: " + ex
                    + " 261_Exception_Data: " + data);
        }
        return HMJWT;

    }

    public static String GetRawParse(ParseObj obj) {

        Gson gson = new Gson();
        ParseSchema jsObj = new ParseSchema();

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

    public static String GetRawEnrich(EnrichObj obj) {

        Gson gson = new Gson();
        EnrichSchema jsObj = new EnrichSchema();

        jsObj.setTimeshost(obj.timeshost);
        jsObj.setHost(obj.host);
        jsObj.setType(obj.type);
        jsObj.setVhost(obj.vhost);
        jsObj.setClientip(obj.clientip);
        jsObj.setTimestamp(obj.timestamp);
        jsObj.setVerb(obj.verb);
        jsObj.setRequest(obj.request);
        jsObj.setResponse(obj.response);
        jsObj.setBytes(obj.bytes);
        jsObj.setReferrer(obj.referrer);
        jsObj.setAgent(obj.agent);
        jsObj.setRequest_duration(obj.request_duration);
        jsObj.setCache_status(obj.cache_status);

        HashMap objHM = JWTData(obj.request);
        jsObj.setUser_id((String) objHM.get(JWTVal.USERID));
        jsObj.setEntity_id((String) objHM.get(JWTVal.ENTITYID));
        jsObj.setEntity_id((String) objHM.get(JWTVal.TITLE));
        jsObj.setEntity_id((String) objHM.get(JWTVal.CONTENTTYPE));
        jsObj.setEntity_id((String) objHM.get(JWTVal.STREAMTYPE));
        jsObj.setEntity_id((String) objHM.get(JWTVal.PUBLISHERID));
        jsObj.setEntity_id((String) objHM.get(JWTVal.SESSION));

        /**
         * Json Object send ParserBolt.
         *
         */
        String sObj = gson.toJson(jsObj);
        return sObj;
    }
}
