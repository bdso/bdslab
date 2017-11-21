package test;

import java.time.LocalDateTime;
import static java.time.ZoneOffset.UTC;
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
        LocalDateTime now = LocalDateTime.now(UTC);
        String timeNow = now + "Z";
        System.out.println(timeNow);
        String msg = "{\"@timestamp\":\"" + timeNow + "\","
                + "\"host\":\"Streaming02\","
                //                + "\"type\":\"vod\","
                //                + "\"vhost\":\"cdn-v1.mob.io\","
                + "\"clientip\":\"1.58.19.68\","
                + "\"timestamp\":\"15/Nov/2017:22:00:50 +0700\","
                + "\"verb\":\"GET\","
                /**
                 * Audio
                 */
                + "\"request\":\"/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxMDk5Mzg1MCwiZXhwIjoxNTExNTk4NjUwLCJlbnRpdHlfaWQiOiIxIiwiZW50aXR5X3RpdGxlIjoiSGFyZGNvZGUgdGl0bGUiLCJlbnRpdHlfc3RyZWFtX3R5cGUiOiJ2b2QiLCJ1c2VyX2lkIjoiNTdhYTNhMGViNTU1NDIwYTk0NWEyN2I0N2NlOWVmMmYiLCJzdWIiOiI1N2FhM2EwZWI1NTU0MjBhOTQ1YTI3YjQ3Y2U5ZWYyZiJ9.GRyg7m8LEJB7G4fCLKTpJF8k111L5x1Ex452IleAKWw/57aa3a0eb555420a945a27b47ce9ef2f-transcode-output/7f042f55-ee4a-4bd0-b043-95ed88233546/other/audio/unk-1/mp4a/64000/64000-16.m4s\","
                /**
                 * Video
                 */
                //                + "\"request\":\"/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxMDk5Mzg1MCwiZXhwIjoxNTExNTk4NjUwLCJlbnRpdHlfaWQiOiIxIiwiZW50aXR5X3RpdGxlIjoiSGFyZGNvZGUgdGl0bGUiLCJlbnRpdHlfc3RyZWFtX3R5cGUiOiJ2b2QiLCJ1c2VyX2lkIjoiNTdhYTNhMGViNTU1NDIwYTk0NWEyN2I0N2NlOWVmMmYiLCJzdWIiOiI1N2FhM2EwZWI1NTU0MjBhOTQ1YTI3YjQ3Y2U5ZWYyZiJ9.GRyg7m8LEJB7G4fCLKTpJF8k111L5x1Ex452IleAKWw/57aa3a0eb555420a945a27b47ce9ef2f-transcode-output/7f042f55-ee4a-4bd0-b043-95ed88233546/other/video/avc1/1280x720/2000000-16.m4s\","
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
