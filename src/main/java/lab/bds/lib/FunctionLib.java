/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.lambdaworks.redis.api.sync.RedisCommands;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;
import static lab.bds.lib.FunctionLib.DivStream;
import static lab.bds.lib.LoggerLib.LOG;
import static lab.bds.lib.RegexLib.RGX_JWT_STREAM;
import static lab.bds.lib.RegexLib.RGX_STREAM_AUDIO;
import static lab.bds.lib.RegexLib.RGX_STREAM_VIDEO;
import static lab.bds.lib.TimeLib.INDEX_FORMAT_TIME;
import static lab.bds.lib.TimeLib.ISO_FORMAT_TIME;
import static lab.bds.lib.TimeLib.ISO_NGX_TIME;
import lab.bds.obj.EnrichObj;
import lab.bds.obj.ParseObj;
import lab.bds.obj.JWTObj;
import lab.bds.scm.EnrichSchema;
import lab.bds.scm.JWTVal;
import lab.bds.scm.ParseSchema;
import lab.bds.scm.ReqVal;

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
            String[] dataPoint = data.split(Pattern.quote("."));
            String dataDecode = new String(DatatypeConverter.parseBase64Binary(dataPoint[1]));
            Gson gson = new GsonBuilder().create();
            JWTObj obj = gson.fromJson(dataDecode, JWTObj.class);
            HMJWT.put(JWTVal.USERID, obj.user_id);
            HMJWT.put(JWTVal.ENTITYID, obj.entity_id);
            HMJWT.put(JWTVal.ENTITY_TITLE, obj.entity_title);
            HMJWT.put(JWTVal.ENTITY_STREAMTYPE, obj.entity_stream_type);

//            HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, obj.entity_content_type);
//            HMJWT.put(JWTVal.PUBLISHERID, obj.entity_publisher_id);
            HMJWT.put(JWTVal.SESSION, dataPoint[1]);
        } catch (JsonSyntaxException js) {
            LOG.info("250_JsonSyntaxException_Excep: " + js
                    + " 251_JsonSyntaxException_Data: " + data);

            HMJWT.put(JWTVal.USERID, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITYID, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITY_TITLE, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITY_STREAMTYPE, JWTVal.OTHER);

//            HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, JWTVal.OTHER);
//            HMJWT.put(JWTVal.PUBLISHERID, JWTVal.OTHER);
            HMJWT.put(JWTVal.SESSION, JWTVal.OTHER);
        } catch (Exception ex) {
            LOG.info("260_Exception_Excep: " + ex
                    + " 261_Exception_Data: " + data);

            HMJWT.put(JWTVal.USERID, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITYID, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITY_TITLE, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITY_STREAMTYPE, JWTVal.OTHER);

//            HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, JWTVal.OTHER);
//            HMJWT.put(JWTVal.PUBLISHERID, JWTVal.OTHER);
            HMJWT.put(JWTVal.SESSION, JWTVal.OTHER);
        }
        return HMJWT;
    }

    public static HashMap<String, String> JWTData(String data,
            RedisCommands<String, String> syncCmds) {

        HashMap<String, String> HMJWT = new HashMap<>();
        try {
            String[] dataPoint = data.split(Pattern.quote("."));
            String dataDecode = new String(DatatypeConverter.parseBase64Binary(dataPoint[1]));

            Gson gson = new GsonBuilder().create();
            JWTObj obj = gson.fromJson(dataDecode, JWTObj.class);
            HMJWT.put(JWTVal.USERID, obj.user_id);
            HMJWT.put(JWTVal.ENTITYID, obj.entity_id);
            HMJWT.put(JWTVal.ENTITY_TITLE, obj.entity_title);
            HMJWT.put(JWTVal.ENTITY_STREAMTYPE, obj.entity_stream_type);

//            HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, obj.entity_content_type);
//            HMJWT.put(JWTVal.PUBLISHERID, obj.entity_publisher_id);
            HMJWT.put(JWTVal.SESSION, dataPoint[1]);
        } catch (JsonSyntaxException js) {
            LOG.info("250_JsonSyntaxException_Excep: " + js
                    + " 251_JsonSyntaxException_Data: " + data);

            HMJWT.put(JWTVal.USERID, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITYID, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITY_TITLE, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITY_STREAMTYPE, JWTVal.OTHER);

//            HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, JWTVal.OTHER);
//            HMJWT.put(JWTVal.PUBLISHERID, JWTVal.OTHER);
            HMJWT.put(JWTVal.SESSION, JWTVal.OTHER);
        } catch (Exception ex) {
            LOG.info("260_Exception_Excep: " + ex
                    + " 261_Exception_Data: " + data);

            HMJWT.put(JWTVal.USERID, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITYID, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITY_TITLE, JWTVal.OTHER);
            HMJWT.put(JWTVal.ENTITY_STREAMTYPE, JWTVal.OTHER);

//            HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, JWTVal.OTHER);
//            HMJWT.put(JWTVal.PUBLISHERID, JWTVal.OTHER);
            HMJWT.put(JWTVal.SESSION, JWTVal.OTHER);
        }
        return HMJWT;
    }

    public static HashMap<String, String> JWTData(String data,
            RedisCommands<String, String> syncCmds,
            String redisPrefix) {

        HashMap<String, String> HMJWT = new HashMap<>();
        String keyJWT = redisPrefix + ":" + data;
        if (syncCmds.exists(keyJWT)) {

            /**
             * Key exist on redis.
             */
            HMJWT.put(JWTVal.USERID, syncCmds.hget(keyJWT, JWTVal.USERID));
            HMJWT.put(JWTVal.ENTITYID, syncCmds.hget(keyJWT, JWTVal.ENTITYID));
            HMJWT.put(JWTVal.ENTITY_TITLE, syncCmds.hget(keyJWT, JWTVal.ENTITY_TITLE));
            HMJWT.put(JWTVal.ENTITY_STREAMTYPE, syncCmds.hget(keyJWT, JWTVal.ENTITY_STREAMTYPE));

//            HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, syncCmds.hget(keyJWT, JWTVal.ENTITY_CONTENTTYPE));
//            HMJWT.put(JWTVal.PUBLISHERID, syncCmds.hget(keyJWT, JWTVal.PUBLISHERID));
            HMJWT.put(JWTVal.SESSION, syncCmds.hget(keyJWT, JWTVal.SESSION));
        } else {

            try {
                String[] dataPoint = data.split(Pattern.quote("."));
                String dataDecode = new String(DatatypeConverter.parseBase64Binary(dataPoint[1]));

                Gson gson = new GsonBuilder().create();
                JWTObj obj = gson.fromJson(dataDecode, JWTObj.class);
                HMJWT.put(JWTVal.USERID, obj.user_id);
                HMJWT.put(JWTVal.ENTITYID, obj.entity_id);
                HMJWT.put(JWTVal.ENTITY_TITLE, obj.entity_title);
                HMJWT.put(JWTVal.ENTITY_STREAMTYPE, obj.entity_stream_type);

//                HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, obj.entity_content_type);
//                HMJWT.put(JWTVal.PUBLISHERID, obj.entity_publisher_id);
                HMJWT.put(JWTVal.SESSION, dataPoint[1]);

                /**
                 * Store data to redis.
                 */
                syncCmds.hset(keyJWT, JWTVal.USERID, obj.user_id);
                syncCmds.hset(keyJWT, JWTVal.ENTITYID, obj.entity_id);
                syncCmds.hset(keyJWT, JWTVal.ENTITY_TITLE, obj.entity_title);
                syncCmds.hset(keyJWT, JWTVal.ENTITY_STREAMTYPE, obj.entity_stream_type);

//                syncCmds.hset(keyJWT, JWTVal.ENTITY_CONTENTTYPE, obj.entity_content_type);
//                syncCmds.hset(keyJWT, JWTVal.PUBLISHERID, obj.entity_publisher_id);
                syncCmds.hset(keyJWT, JWTVal.SESSION, dataPoint[1]);
            } catch (JsonSyntaxException js) {
                LOG.info("250_JsonSyntaxException_Excep: " + js
                        + " 251_JsonSyntaxException_Data: " + data);

                HMJWT.put(JWTVal.USERID, JWTVal.OTHER);
                HMJWT.put(JWTVal.ENTITYID, JWTVal.OTHER);
                HMJWT.put(JWTVal.ENTITY_TITLE, JWTVal.OTHER);
                HMJWT.put(JWTVal.ENTITY_STREAMTYPE, JWTVal.OTHER);

//            HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, JWTVal.OTHER);
//            HMJWT.put(JWTVal.PUBLISHERID, JWTVal.OTHER);
                HMJWT.put(JWTVal.SESSION, JWTVal.OTHER);

                /**
                 * Store data to redis in case 1.
                 */
                syncCmds.hset(keyJWT, JWTVal.USERID, JWTVal.OTHER);
                syncCmds.hset(keyJWT, JWTVal.ENTITYID, JWTVal.OTHER);
                syncCmds.hset(keyJWT, JWTVal.ENTITY_TITLE, JWTVal.OTHER);
                syncCmds.hset(keyJWT, JWTVal.ENTITY_STREAMTYPE, JWTVal.OTHER);

//                syncCmds.hset(keyJWT, JWTVal.ENTITY_CONTENTTYPE, JWTVal.OTHER);
//                syncCmds.hset(keyJWT, JWTVal.PUBLISHERID, JWTVal.OTHER);
                syncCmds.hset(keyJWT, JWTVal.SESSION, JWTVal.OTHER);
            } catch (Exception ex) {
                LOG.info("260_Exception_Excep: " + ex
                        + " 261_Exception_Data: " + data);

                HMJWT.put(JWTVal.USERID, JWTVal.OTHER);
                HMJWT.put(JWTVal.ENTITYID, JWTVal.OTHER);
                HMJWT.put(JWTVal.ENTITY_TITLE, JWTVal.OTHER);
                HMJWT.put(JWTVal.ENTITY_STREAMTYPE, JWTVal.OTHER);

//            HMJWT.put(JWTVal.ENTITY_CONTENTTYPE, JWTVal.OTHER);
//            HMJWT.put(JWTVal.PUBLISHERID, JWTVal.OTHER);
                HMJWT.put(JWTVal.SESSION, JWTVal.OTHER);

                /**
                 * Store data to redis in case 2.
                 */
                syncCmds.hset(keyJWT, JWTVal.USERID, JWTVal.OTHER);
                syncCmds.hset(keyJWT, JWTVal.ENTITYID, JWTVal.OTHER);
                syncCmds.hset(keyJWT, JWTVal.ENTITY_TITLE, JWTVal.OTHER);
                syncCmds.hset(keyJWT, JWTVal.ENTITY_STREAMTYPE, JWTVal.OTHER);

//                syncCmds.hset(keyJWT, JWTVal.ENTITY_CONTENTTYPE, JWTVal.OTHER);
//                syncCmds.hset(keyJWT, JWTVal.PUBLISHERID, JWTVal.OTHER);
                syncCmds.hset(keyJWT, JWTVal.SESSION, JWTVal.OTHER);
            }
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
//        jsObj.setType(obj.type);
//        jsObj.setVhost(obj.vhost);
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

    public static String GetRawEnrich(EnrichObj obj,
            RedisCommands<String, String> syncCmds) {

        Gson gson = new Gson();
        EnrichSchema jsObj = new EnrichSchema();

        jsObj.setTimeshost(obj.timeshost);
        jsObj.setHost(obj.host);
//        jsObj.setType(obj.type);
//        jsObj.setVhost(obj.vhost);
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

        /**
         * Divide request to 2 strings: JWT vs STREAM(Format streaming).
         */
        HashMap objRQ = DivRequest(obj.request);

        /**
         * Parse JWT.
         */
        HashMap objHM = JWTData((String) objRQ.get(ReqVal.JWT));
        jsObj.setUser_id((String) objHM.get(JWTVal.USERID));
        jsObj.setEntity_id((String) objHM.get(JWTVal.ENTITYID));
        jsObj.setTitle((String) objHM.get(JWTVal.ENTITY_TITLE));
        jsObj.setContent_type((String) objHM.get(JWTVal.ENTITY_CONTENTTYPE));
        jsObj.setStream_type((String) objHM.get(JWTVal.ENTITY_STREAMTYPE));
        jsObj.setPublisher_id((String) objHM.get(JWTVal.PUBLISHERID));
        jsObj.setSession((String) objHM.get(JWTVal.SESSION));

        /**
         * Parse Stream.
         */
        HashMap objST = DivStream((String) objRQ.get(ReqVal.STREAM));
        jsObj.setTran_out((String) objST.get(ReqVal.TRAN_OUT));
        jsObj.setName_entity((String) objST.get(ReqVal.NAME_ENTITY));
        jsObj.setProtocol((String) objST.get(ReqVal.RQ_TYPE));
        jsObj.setLanguage((String) objST.get(ReqVal.LANGUAGE));
        jsObj.setCodec((String) objST.get(ReqVal.CODEC));
        jsObj.setProfile((String) objST.get(ReqVal.PROFILE));
        jsObj.setBitrate((String) objST.get(ReqVal.BITRATE));
        jsObj.setChunk((String) objST.get(ReqVal.CHUNK));

        /**
         * Json Object send ParserBolt.
         *
         */
        String sObj = gson.toJson(jsObj);
        return sObj;
    }

    public static String GetRawEnrich(EnrichObj obj,
            RedisCommands<String, String> syncCmds,
            String redisPrefix) {

        Gson gson = new Gson();
        EnrichSchema jsObj = new EnrichSchema();

        jsObj.setTimeshost(obj.timeshost);
        jsObj.setHost(obj.host);
//        jsObj.setType(obj.type);
//        jsObj.setVhost(obj.vhost);
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

        /**
         * Divide request to 2 strings: JWT vs STREAM(Format streaming).
         */
        HashMap objRQ = DivRequest(obj.request);

        /**
         * Parse JWT.
         */
        HashMap objHM = JWTData((String) objRQ.get(ReqVal.JWT),
                syncCmds,
                redisPrefix);
        jsObj.setUser_id((String) objHM.get(JWTVal.USERID));
        jsObj.setEntity_id((String) objHM.get(JWTVal.ENTITYID));
        jsObj.setTitle((String) objHM.get(JWTVal.ENTITY_TITLE));
        jsObj.setStream_type((String) objHM.get(JWTVal.ENTITY_STREAMTYPE));

        /**
         * Update v2: entity_content_type, publisher_id
         */
//        jsObj.setContent_type((String) objHM.get(JWTVal.ENTITY_CONTENTTYPE));
//        jsObj.setPublisher_id((String) objHM.get(JWTVal.PUBLISHERID));
        jsObj.setSession((String) objHM.get(JWTVal.SESSION));

        /**
         * Parse Stream.
         */
        HashMap objST = DivStream((String) objRQ.get(ReqVal.STREAM),
                syncCmds,
                redisPrefix);
        jsObj.setTran_out((String) objST.get(ReqVal.TRAN_OUT));
        jsObj.setName_entity((String) objST.get(ReqVal.NAME_ENTITY));
        jsObj.setProtocol((String) objST.get(ReqVal.PROTOCOL));
        jsObj.setRq_type((String) objST.get(ReqVal.RQ_TYPE));
//        jsObj.setLanguage((String) objST.get(ReqVal.LANGUAGE));
        jsObj.setCodec((String) objST.get(ReqVal.CODEC));
//        jsObj.setProfile((String) objST.get(ReqVal.PROFILE));
        jsObj.setBitrate((String) objST.get(ReqVal.BITRATE));
        jsObj.setChunk((String) objST.get(ReqVal.CHUNK));

        /**
         * Json Object send ParserBolt.
         *
         */
        String sObj = gson.toJson(jsObj);
        return sObj;
    }

    public static HashMap<String, String> DivRequest(String request) {
        Pattern r = Pattern.compile(RGX_JWT_STREAM);
        Matcher m = r.matcher(request);
        HashMap<String, String> HMRQ = new HashMap<>();
        if (m.find()) {
            HMRQ.put(ReqVal.JWT, m.group(1));
            HMRQ.put(ReqVal.STREAM, m.group(2));
        } else {
            HMRQ.put(ReqVal.JWT, ReqVal.OTHER);
            HMRQ.put(ReqVal.STREAM, ReqVal.OTHER);
        }
        return HMRQ;
    }

    public static HashMap<String, String> DivStream(String stream) {
        HashMap<String, String> HMRQ = new HashMap<>();
        if (stream.contains("/video/")) {

            /**
             * Stream video.
             */
            Pattern r = Pattern.compile(RGX_STREAM_VIDEO);
            Matcher m = r.matcher(stream);
            if (m.find()) {
                HMRQ.put(ReqVal.TRAN_OUT, m.group(1));
                HMRQ.put(ReqVal.NAME_ENTITY, m.group(2));
                HMRQ.put(ReqVal.PROTOCOL, m.group(3));
                HMRQ.put(ReqVal.RQ_TYPE, m.group(4));
                HMRQ.put(ReqVal.CODEC, m.group(5));
                HMRQ.put(ReqVal.PROFILE, m.group(6));
                HMRQ.put(ReqVal.BITRATE, m.group(7));
                HMRQ.put(ReqVal.CHUNK, m.group(8));
            } else {
                HMRQ.put(ReqVal.TRAN_OUT, ReqVal.OTHER);
                HMRQ.put(ReqVal.NAME_ENTITY, ReqVal.OTHER);
                HMRQ.put(ReqVal.PROTOCOL, ReqVal.OTHER);
                HMRQ.put(ReqVal.RQ_TYPE, ReqVal.OTHER);
                HMRQ.put(ReqVal.CODEC, ReqVal.OTHER);
                HMRQ.put(ReqVal.PROFILE, ReqVal.OTHER);
                HMRQ.put(ReqVal.BITRATE, ReqVal.OTHER);
                HMRQ.put(ReqVal.CHUNK, ReqVal.OTHER);
            }
        } else {

            /**
             * Stream audio.
             */
            Pattern r = Pattern.compile(RGX_STREAM_AUDIO);
            Matcher m = r.matcher(stream);
            if (m.find()) {
                HMRQ.put(ReqVal.TRAN_OUT, m.group(1));
                HMRQ.put(ReqVal.NAME_ENTITY, m.group(2));
                HMRQ.put(ReqVal.PROTOCOL, m.group(3));
                HMRQ.put(ReqVal.RQ_TYPE, m.group(4));
                HMRQ.put(ReqVal.LANGUAGE, m.group(5));
                HMRQ.put(ReqVal.CODEC, m.group(6));
                HMRQ.put(ReqVal.BITRATE, m.group(7));
                HMRQ.put(ReqVal.CHUNK, m.group(9));
            } else {
                HMRQ.put(ReqVal.TRAN_OUT, ReqVal.OTHER);
                HMRQ.put(ReqVal.NAME_ENTITY, ReqVal.OTHER);
                HMRQ.put(ReqVal.PROTOCOL, ReqVal.OTHER);
                HMRQ.put(ReqVal.RQ_TYPE, ReqVal.OTHER);
                HMRQ.put(ReqVal.LANGUAGE, ReqVal.OTHER);
                HMRQ.put(ReqVal.CODEC, ReqVal.OTHER);
                HMRQ.put(ReqVal.BITRATE, ReqVal.OTHER);
                HMRQ.put(ReqVal.CHUNK, ReqVal.OTHER);
            }
        }
        return HMRQ;
    }

    public static HashMap<String, String> DivStream(String stream,
            RedisCommands<String, String> syncCmds) {
        HashMap<String, String> HMRQ = new HashMap<>();
        if (stream.contains("/video/")) {

            /**
             * Stream video.
             */
            Pattern r = Pattern.compile(RGX_STREAM_VIDEO);
            Matcher m = r.matcher(stream);
            if (m.find()) {
                HMRQ.put(ReqVal.TRAN_OUT, m.group(1));
                HMRQ.put(ReqVal.NAME_ENTITY, m.group(2));
                HMRQ.put(ReqVal.PROTOCOL, m.group(3));
                HMRQ.put(ReqVal.RQ_TYPE, m.group(4));
                HMRQ.put(ReqVal.CODEC, m.group(5));
                HMRQ.put(ReqVal.PROFILE, m.group(6));
                HMRQ.put(ReqVal.BITRATE, m.group(7));
                HMRQ.put(ReqVal.CHUNK, m.group(8));
            } else {
                HMRQ.put(ReqVal.TRAN_OUT, ReqVal.OTHER);
                HMRQ.put(ReqVal.NAME_ENTITY, ReqVal.OTHER);
                HMRQ.put(ReqVal.PROTOCOL, ReqVal.OTHER);
                HMRQ.put(ReqVal.RQ_TYPE, ReqVal.OTHER);
                HMRQ.put(ReqVal.CODEC, ReqVal.OTHER);
                HMRQ.put(ReqVal.PROFILE, ReqVal.OTHER);
                HMRQ.put(ReqVal.BITRATE, ReqVal.OTHER);
                HMRQ.put(ReqVal.CHUNK, ReqVal.OTHER);
            }
        } else {

            /**
             * Stream audio.
             */
            Pattern r = Pattern.compile(RGX_STREAM_AUDIO);
            Matcher m = r.matcher(stream);
            if (m.find()) {
                HMRQ.put(ReqVal.TRAN_OUT, m.group(1));
                HMRQ.put(ReqVal.NAME_ENTITY, m.group(2));
                HMRQ.put(ReqVal.PROTOCOL, m.group(3));
                HMRQ.put(ReqVal.RQ_TYPE, m.group(4));
                HMRQ.put(ReqVal.LANGUAGE, m.group(5));
                HMRQ.put(ReqVal.CODEC, m.group(6));
                HMRQ.put(ReqVal.BITRATE, m.group(7));
                HMRQ.put(ReqVal.CHUNK, m.group(9));
            } else {
                HMRQ.put(ReqVal.TRAN_OUT, ReqVal.OTHER);
                HMRQ.put(ReqVal.NAME_ENTITY, ReqVal.OTHER);
                HMRQ.put(ReqVal.PROTOCOL, ReqVal.OTHER);
                HMRQ.put(ReqVal.RQ_TYPE, ReqVal.OTHER);
                HMRQ.put(ReqVal.LANGUAGE, ReqVal.OTHER);
                HMRQ.put(ReqVal.CODEC, ReqVal.OTHER);
                HMRQ.put(ReqVal.BITRATE, ReqVal.OTHER);
                HMRQ.put(ReqVal.CHUNK, ReqVal.OTHER);
            }
        }
        return HMRQ;
    }

    public static HashMap<String, String> DivStream(String stream,
            RedisCommands<String, String> syncCmds,
            String redisPrefix) {
        HashMap<String, String> HMRQ = new HashMap<>();
        String keyStream = redisPrefix + ":" + stream;

        /**
         * TODO: Optimize code to reduce function.
         */
        if (stream.contains("/video/")) {
            if (syncCmds.exists(keyStream)) {

                /**
                 * Key exist on redis.
                 */
                HMRQ.put(ReqVal.TRAN_OUT, syncCmds.hget(keyStream, ReqVal.TRAN_OUT));
                HMRQ.put(ReqVal.NAME_ENTITY, syncCmds.hget(keyStream, ReqVal.NAME_ENTITY));
                HMRQ.put(ReqVal.PROTOCOL, syncCmds.hget(keyStream, ReqVal.PROTOCOL));
                HMRQ.put(ReqVal.RQ_TYPE, syncCmds.hget(keyStream, ReqVal.RQ_TYPE));
                HMRQ.put(ReqVal.CODEC, syncCmds.hget(keyStream, ReqVal.CODEC));
                HMRQ.put(ReqVal.PROFILE, syncCmds.hget(keyStream, ReqVal.PROFILE));
                HMRQ.put(ReqVal.BITRATE, syncCmds.hget(keyStream, ReqVal.BITRATE));
                HMRQ.put(ReqVal.CHUNK, syncCmds.hget(keyStream, ReqVal.CHUNK));
            } else {

                /**
                 * Stream video.
                 */
                Pattern r = Pattern.compile(RGX_STREAM_VIDEO);
                Matcher m = r.matcher(stream);
                if (m.find()) {
                    HMRQ.put(ReqVal.TRAN_OUT, m.group(1));
                    HMRQ.put(ReqVal.NAME_ENTITY, m.group(2));
                    HMRQ.put(ReqVal.PROTOCOL, m.group(3));
                    HMRQ.put(ReqVal.RQ_TYPE, m.group(4));
                    HMRQ.put(ReqVal.CODEC, m.group(5));
                    HMRQ.put(ReqVal.PROFILE, m.group(6));
                    HMRQ.put(ReqVal.BITRATE, m.group(7));
                    HMRQ.put(ReqVal.CHUNK, m.group(8));

                    /**
                     * Store data to redis.
                     */
                    syncCmds.hset(keyStream, ReqVal.TRAN_OUT, m.group(1));
                    syncCmds.hset(keyStream, ReqVal.NAME_ENTITY, m.group(2));
                    syncCmds.hset(keyStream, ReqVal.PROTOCOL, m.group(3));
                    syncCmds.hset(keyStream, ReqVal.RQ_TYPE, m.group(4));
                    syncCmds.hset(keyStream, ReqVal.CODEC, m.group(5));
                    syncCmds.hset(keyStream, ReqVal.PROFILE, m.group(6));
                    syncCmds.hset(keyStream, ReqVal.BITRATE, m.group(7));
                    syncCmds.hset(keyStream, ReqVal.CHUNK, m.group(8));
                } else {
                    HMRQ.put(ReqVal.TRAN_OUT, ReqVal.OTHER);
                    HMRQ.put(ReqVal.NAME_ENTITY, ReqVal.OTHER);
                    HMRQ.put(ReqVal.PROTOCOL, ReqVal.OTHER);
                    HMRQ.put(ReqVal.RQ_TYPE, ReqVal.OTHER);
                    HMRQ.put(ReqVal.CODEC, ReqVal.OTHER);
                    HMRQ.put(ReqVal.PROFILE, ReqVal.OTHER);
                    HMRQ.put(ReqVal.BITRATE, ReqVal.OTHER);
                    HMRQ.put(ReqVal.CHUNK, ReqVal.OTHER);

                    /**
                     * Store data(other) to redis.
                     */
                    syncCmds.hset(keyStream, ReqVal.TRAN_OUT, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.NAME_ENTITY, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.PROTOCOL, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.RQ_TYPE, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.CODEC, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.PROFILE, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.BITRATE, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.CHUNK, ReqVal.OTHER);
                }
            }
        } else {
            if (syncCmds.exists(keyStream)) {

                /**
                 * Key exist on redis.
                 */
                HMRQ.put(ReqVal.TRAN_OUT, syncCmds.hget(keyStream, ReqVal.TRAN_OUT));
                HMRQ.put(ReqVal.NAME_ENTITY, syncCmds.hget(keyStream, ReqVal.NAME_ENTITY));
                HMRQ.put(ReqVal.PROTOCOL, syncCmds.hget(keyStream, ReqVal.PROTOCOL));
                HMRQ.put(ReqVal.RQ_TYPE, syncCmds.hget(keyStream, ReqVal.RQ_TYPE));
                HMRQ.put(ReqVal.LANGUAGE, syncCmds.hget(keyStream, ReqVal.LANGUAGE));
                HMRQ.put(ReqVal.CODEC, syncCmds.hget(keyStream, ReqVal.CODEC));
                HMRQ.put(ReqVal.BITRATE, syncCmds.hget(keyStream, ReqVal.BITRATE));
                HMRQ.put(ReqVal.CHUNK, syncCmds.hget(keyStream, ReqVal.CHUNK));
            } else {

                /**
                 * Stream audio.
                 */
                Pattern r = Pattern.compile(RGX_STREAM_AUDIO);
                Matcher m = r.matcher(stream);
                if (m.find()) {
                    HMRQ.put(ReqVal.TRAN_OUT, m.group(1));
                    HMRQ.put(ReqVal.NAME_ENTITY, m.group(2));
                    HMRQ.put(ReqVal.PROTOCOL, m.group(3));
                    HMRQ.put(ReqVal.RQ_TYPE, m.group(4));
                    HMRQ.put(ReqVal.LANGUAGE, m.group(5));
                    HMRQ.put(ReqVal.CODEC, m.group(6));
                    HMRQ.put(ReqVal.BITRATE, m.group(7));
                    HMRQ.put(ReqVal.CHUNK, m.group(9));

                    /**
                     * Store data to redis.
                     */
                    syncCmds.hset(keyStream, ReqVal.TRAN_OUT, m.group(1));
                    syncCmds.hset(keyStream, ReqVal.NAME_ENTITY, m.group(2));
                    syncCmds.hset(keyStream, ReqVal.PROTOCOL, m.group(3));
                    syncCmds.hset(keyStream, ReqVal.RQ_TYPE, m.group(4));
                    syncCmds.hset(keyStream, ReqVal.LANGUAGE, m.group(5));
                    syncCmds.hset(keyStream, ReqVal.CODEC, m.group(6));
                    syncCmds.hset(keyStream, ReqVal.BITRATE, m.group(7));
                    syncCmds.hset(keyStream, ReqVal.CHUNK, m.group(9));
                } else {
                    HMRQ.put(ReqVal.TRAN_OUT, ReqVal.OTHER);
                    HMRQ.put(ReqVal.NAME_ENTITY, ReqVal.OTHER);
                    HMRQ.put(ReqVal.PROTOCOL, ReqVal.OTHER);
                    HMRQ.put(ReqVal.RQ_TYPE, ReqVal.OTHER);
                    HMRQ.put(ReqVal.LANGUAGE, ReqVal.OTHER);
                    HMRQ.put(ReqVal.CODEC, ReqVal.OTHER);
                    HMRQ.put(ReqVal.BITRATE, ReqVal.OTHER);
                    HMRQ.put(ReqVal.CHUNK, ReqVal.OTHER);

                    /**
                     * Store data(other) to redis.
                     */
                    syncCmds.hset(keyStream, ReqVal.TRAN_OUT, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.NAME_ENTITY, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.PROTOCOL, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.RQ_TYPE, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.LANGUAGE, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.CODEC, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.BITRATE, ReqVal.OTHER);
                    syncCmds.hset(keyStream, ReqVal.CHUNK, ReqVal.OTHER);
                }
            }
        }
        return HMRQ;
    }
}
