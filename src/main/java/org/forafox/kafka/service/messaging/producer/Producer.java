package org.forafox.kafka.service.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.forafox.domain.MethodData;
import org.forafox.kafka.service.messaging.service.KafkaMessagingService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaMessagingService kafkaMessagingService;

    public MethodData sendMethodData(MethodData methodData) {
        kafkaMessagingService.sendOrder(methodData);
        log.info("Send order from producer {}", methodData);
        return methodData;
    }
}
