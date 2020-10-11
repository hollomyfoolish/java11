package com.allen.gong.research;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;

public class KafkaAdminClient {

    public static void main(String[] args) {
        Properties prop = new Properties();

        prop.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        try(
                AdminClient adminClient = AdminClient.create(prop);
        ){
            NewTopic topic = new NewTopic(IKafkaConstants.TOPIC_NAME, 2, (short) 1);
            adminClient.createTopics(Collections.singletonList(topic));
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
