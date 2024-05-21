package org.forafox.kafka.service.messaging.service;

import org.forafox.kafka.service.messaging.event.SendMethodDataEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessagingService {

    @Value("${topic.send-methodData-event}")
    private String sendMethodDataTopic;

    @Value("${topic.send-asyncMethodData-event}")
    private String sendAsyncMethodDataTopic;

    private final KafkaTemplate<String , Object> kafkaTemplate;

    public void sendMethodData(SendMethodDataEvent sendMethodDataEvent) {
        kafkaTemplate.send(sendMethodDataTopic, sendMethodDataEvent.getMethodName(), sendMethodDataEvent);
    }
    public void sendAsyncMethodData(SendMethodDataEvent sendMethodDataEvent) {
        kafkaTemplate.send(sendAsyncMethodDataTopic, sendMethodDataEvent.getMethodName(), sendMethodDataEvent);
    }

}
