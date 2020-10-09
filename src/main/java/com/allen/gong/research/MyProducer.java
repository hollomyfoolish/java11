package com.allen.gong.research;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyProducer {
    private Producer<String, String> producer;

    public MyProducer(){
//        init();
//        for (int i = 0; i < 100; i++)
//            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
//        producer.close();
    }

    public MyProducer init() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(props);
        return this;
    }

    public void produce(String key, String value){
        producer.send(new ProducerRecord<>(IKafkaConstants.TOPIC_NAME, key, value));
    }

    public void stop(){
        if(this.producer != null){
            this.producer.close();
        }
    }

//    public void start(){
//        new Thread(() -> {
//            while (!stop){
//
//            }
//        }).start();
//    }

}
