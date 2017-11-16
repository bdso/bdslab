package test;

import java.time.LocalDateTime;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class SimpleProducer {

    public static Producer<Integer, String> producer;
    public final Properties properties = new Properties();

    public SimpleProducer() {
        properties.put("metadata.broker.list", "localhost:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("request.required.acks", "1");
        producer = new Producer<>(new ProducerConfig(properties));
    }

    public static void main(String[] args) throws InterruptedException {
        new SimpleProducer();
        String topic = "bdslab";
        LocalDateTime now = LocalDateTime.now();
        String timeNow = now + "Z";
        String msg = "{\"@timestamp\":\"" + timeNow + "\","
                + "\"host\":\"Streaming02\","
                //                + "\"type\":\"vod\","
                //                + "\"vhost\":\"cdn-v1.mob.io\","
                + "\"clientip\":\"1.58.19.68\","
                + "\"timestamp\":\"15/Nov/2017:22:00:50 +0700\","
                + "\"verb\":\"GET\","
                + "\"request\":\"/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJlNWU2NGMxMi0zYzRhLTRmNjUtYWM0MC0xZTdhMzdkYjllZmIiLCJlbnRpdHlJZCI6MSwidGl0bGUiOiJUaG9yOiBSYWduYXJvayIsImNvbnRlbnRUeXBlIjoibW92aWUiLCJzdHJlYW1UeXBlIjoidm9kIiwicHVibGlzaGVySWQiOiIxMDUyNzZhNi1jMzdhLTQ4M2UtOGNiZS0yODA0ZDJmMzdhZDcifQ.eGJL4VBUbk8zr8Vb-1oGRRQGHpYi5uonsYAUx8jOiso/57aa3a0eb555420a945a27b47ce9ef2f-transcode-output/229e4c9c-9c14-4da5-9801-4d357ec44d9c/other/video/avc1/640x360/500000-93.m4s\","
                + "\"response\":\"200\","
                + "\"bytes\":\"1189104\","
                + "\"referrer\":\"\\\"http://localhost:1343/detail/chu-cho-tinh-nghich\\\"\","
                + "\"agent\":\"\\\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/60.4.122 Chrome/54.4.2840.122 Safari/537.36\\\"\","
                + "\"xforwardedfor\":\"\\\"-\\\"\","
                + "\"request_duration\":\"1.031\","
                + "\"cache_status\":\"MISS\"}";
        KeyedMessage<Integer, String> data = new KeyedMessage<>(topic, msg);
//        int i = 0;
//        while (true) {
//            System.out.println("Msg: " + i++);
        producer.send(data);
//            Thread.sleep(5000);
//        }
        producer.close();
    }
}
