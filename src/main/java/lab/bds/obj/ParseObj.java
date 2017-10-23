/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.obj;

/**
 *
 * @author leo
 */
public class ParseObj {

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

}
