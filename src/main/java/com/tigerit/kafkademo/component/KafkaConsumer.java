package com.tigerit.kafkademo.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(
            topics = "farhan-topic",
            groupId = "group_farhan"
    )
    public void listen(String data){
        System.out.println(data);
    }
}
