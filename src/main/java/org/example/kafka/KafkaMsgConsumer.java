package org.example.kafka;

import org.example.model.ResponseDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMsgConsumer {

    @KafkaListener(topics = "default-topic", groupId = "main-group")
    public void listen(String message) {
        System.out.println(message);
    }
}