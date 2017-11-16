package lab.bds.obj;

import com.google.gson.annotations.SerializedName;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leo
 */
public class ParseObj {

    /*
        Example data receive:
        
        {
           "@timestamp":"2017-02-09T15:00:51.067Z",
           "host":"Streaming02",
//           "type":"vod",
//           "vhost":"cdn-v1.mobitv.io",
           "clientip":"1.54.19.38",
           "timestamp":"09/Feb/2017:22:00:50 +0700",
           "verb":"GET",
           "request":"/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtb3ZpZUlkIjoiODQ1MDViNTktYTMzNC00ZGY2LWI1ZmEtNjIzMTkzMDM2Yjc0IiwiZXhwIjoxNDg2NjY2NDQxNDg5fQ.eMTMapCKpMGK1HjuDgaF_nImWiMYxnYbsScNMVkDK7o/vod/content/chu_cho_tinh_nghich_pup_2_no_good1/hls/1280x720-1488/chunk_72.ts",
           "response":"200",
           "bytes":"1189104",
           "referrer":"\"http://localhost:1343/detail/chu-cho-tinh-nghich\"",
           "agent":"\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/60.4.122 Chrome/54.4.2840.122 Safari/537.36\"",
           "xforwardedfor":"\"-\"",
           "request_duration":"1.031",
           "cache_status":"MISS"
        }
     */
    @SerializedName("@timestamp")
    public String timeshost;
    public String host;
//    public String type;
//    public String vhost;
    public String clientip;
    public String timestamp;
    public String verb;
    public String request;
    public String response;
    public String bytes;
    public String referrer;
    public String agent;
    public String xforwardedfor;
    public String request_duration;
    public String cache_status;
}
