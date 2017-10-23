package test;

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
        String msg = "{\"@timestamp\":\"2017-10-24T15:00:51.067Z\",\"host\":\"Streaming02\",\"type\":\"vod\",\"vhost\":\"cdn-v1.mob.io\",\"clientip\":\"1.58.19.68\",\"timestamp\":\"09/Feb/2017:22:00:50 +0700\",\"verb\":\"GET\",\"request\":\"/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtb3ZpZUlkIjoiODQ1MDViNTktYTMzNC00ZGY2LWI1ZmEtNjIzMTkzMDM2Yjc0IiwiZXhwIjoxNDg2NjY2NDQxNDg5fQ.eMTMapCKpMGK1HjuDgaF_nImWiMYxnYbsScNMVkDK7o/vod/content/chu_cho_tinh_nghich_pup_2_no_good1/hls/1280x720-1488/chunk_72.ts\",\"response\":\"200\",\"bytes\":\"1189104\",\"referrer\":\"\\\"http://localhost:1343/detail/chu-cho-tinh-nghich\\\"\",\"agent\":\"\\\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/60.4.122 Chrome/54.4.2840.122 Safari/537.36\\\"\",\"xforwardedfor\":\"\\\"-\\\"\",\"request_duration\":\"1.031\",\"cache_status\":\"MISS\"}";
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
