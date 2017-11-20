/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.scm;

/**
 *
 * @author leo
 */
public class EnrichSchema {

    private String timeshost;
    private String host;
    private String type;
    private String vhost;
    private String clientip;
    private String timestamp;
    private String verb;
    private String request;
    private int response;
    private long bytes;
    private String referrer;
    private String agent;
    private float request_duration;
    private String cache_status;

    /**
     * JWT.
     */
    private String user_id;
    private String entity_id;
    private String entity_title;
    private String entity_content_type;
    private String entity_stream_type;
    private String publisher_id;
    private String session;

    /**
     * Request.
     */
    private String tran_out;
    private String name_entity;
    private String protocol;
    private String rq_type;
    private String language;
    private String codec;
    private String profile;
    private String bitrate;
    private String chunk;

    public String getTimeshost() {
        return timeshost;
    }

    public void setTimeshost(String timeshost) {
        this.timeshost = timeshost;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVhost() {
        return vhost;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public float getRequest_duration() {
        return request_duration;
    }

    public void setRequest_duration(float request_duration) {
        this.request_duration = request_duration;
    }

    public String getCache_status() {
        return cache_status;
    }

    public void setCache_status(String cache_status) {
        this.cache_status = cache_status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getTitle() {
        return entity_title;
    }

    public void setTitle(String title) {
        this.entity_title = title;
    }

    public String getContent_type() {
        return entity_content_type;
    }

    public void setContent_type(String content_type) {
        this.entity_content_type = content_type;
    }

    public String getStream_type() {
        return entity_stream_type;
    }

    public void setStream_type(String stream_type) {
        this.entity_stream_type = stream_type;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTran_out() {
        return tran_out;
    }

    public void setTran_out(String tran_out) {
        this.tran_out = tran_out;
    }

    public String getName_entity() {
        return name_entity;
    }

    public void setName_entity(String name_entity) {
        this.name_entity = name_entity;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRq_type() {
        return rq_type;
    }

    public void setRq_type(String rq_type) {
        this.rq_type = rq_type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getChunk() {
        return chunk;
    }

    public void setChunk(String chunk) {
        this.chunk = chunk;
    }

}
