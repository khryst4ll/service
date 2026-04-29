package org.example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMsgConsumer {

    @KafkaListener(topics = "default-topic", groupId = "main-group")
    public void listen(String message) {
        System.out.println("Кафка получила сообщение: " + message);
    }
}