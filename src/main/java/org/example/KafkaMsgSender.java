package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMsgSender {
    private static final String DEFAULT_TOPIC = "default-topic";
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMsg(String message) {
        this.kafkaTemplate.send(KafkaMsgSender.DEFAULT_TOPIC, message);
    }
}
