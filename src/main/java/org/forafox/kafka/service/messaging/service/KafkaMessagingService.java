package org.forafox.kafka.service.messaging.service;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.MethodData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessagingService {

    @Value("${topic.send-order}")
    private String sendClientTopic;

    private final KafkaTemplate<String , Object> kafkaTemplate;

    public void sendOrder(MethodData methodData) {
        kafkaTemplate.send(sendClientTopic, methodData.getMethodName(), methodData);
    }

}
