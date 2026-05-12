package org.example.kafka;

import org.example.model.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMsgSender {
    private static final String DEFAULT_TOPIC = "default-topic";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        this.kafkaTemplate.send(KafkaMsgSender.DEFAULT_TOPIC, str);
    }
}
