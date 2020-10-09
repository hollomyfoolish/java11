package com.allen.gong.research;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;

public class MyConsumer {
    private Consumer<String, String> consumer;
    private volatile boolean stop;

    public MyConsumer(){

    }

    public MyConsumer init(){
        this.consumer = ConsumerCreator.createConsumer();
        return this;
    }

    public void stop(){
        this.stop = true;
    }

    public void start(){
        new Thread(() -> {
            while(!stop){
                System.out.println("poll message from broker");
                ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofSeconds(5));
                for(ConsumerRecord r : records){
                    System.out.println(String.format("[%s: %s]", r.key(), r.value()));
                }
            }
            System.out.println("consumer exits");
        }).start();
    }

}
