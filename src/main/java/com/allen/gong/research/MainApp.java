package com.allen.gong.research;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class MainApp {
    static final int BUFFER_CAPACITY_LIMIT = 1 << 30;

    static final int roundCapacity(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n <= 0) ? 1 : // at least 1
                (n >= BUFFER_CAPACITY_LIMIT) ? BUFFER_CAPACITY_LIMIT : n + 1;
    }

    public static void main(String... args) {
//        httpClientTest();
        MyProducer producer = new MyProducer().init();
        MyConsumer consumer = new MyConsumer().init();
        consumer.start();
        Scanner scanner = new Scanner(System.in);
        int idx = 1;
        for(String line = scanner.nextLine(); line != null; line = scanner.nextLine()){
            if(!"done".equalsIgnoreCase(line)){
                producer.produce("key" + idx, line);
            }else{
                System.out.println("done!");
                break;
            }
            idx++;
        }
        consumer.stop();
    }

    private static void httpClientTest() {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create("https://maven.apache.org/plugins/maven-compiler-plugin/examples/compile-using-different-jdk.html"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        System.out.println(roundCapacity(1));
        System.out.println(roundCapacity(2));
        System.out.println(roundCapacity(3));
        System.out.println(roundCapacity(4));
        System.out.println(roundCapacity(5));
        System.out.println(roundCapacity(11));
    }

}

