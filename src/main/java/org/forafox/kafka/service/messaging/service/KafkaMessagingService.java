package org.forafox.kafka.service.messaging.service;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.MethodData;
import org.forafox.kafka.service.messaging.event.SendMethodDataEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessagingService {

    @Value("${topic.send-methodData-event}")
    private String sendClientTopic;

    private final KafkaTemplate<String , Object> kafkaTemplate;

    public void sendMethodData(SendMethodDataEvent sendMethodDataEvent) {
        kafkaTemplate.send(sendClientTopic, sendMethodDataEvent.getMethodName(), sendMethodDataEvent);
    }

}
