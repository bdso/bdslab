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
    private String title;
    private String content_type;
    private String stream_type;
    private String publisher_id;
    private String session;

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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getStream_type() {
        return stream_type;
    }

    public void setStream_type(String stream_type) {
        this.stream_type = stream_type;
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

}
