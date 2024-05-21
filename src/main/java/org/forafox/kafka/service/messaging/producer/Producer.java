package org.forafox.kafka.service.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.forafox.domain.MethodData;
import org.forafox.kafka.service.messaging.service.KafkaMessagingService;
import org.forafox.web.mapper.SendMethodDataEventMapper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaMessagingService kafkaMessagingService;
    private final SendMethodDataEventMapper sendMethodDataEventMapper;
    public MethodData sendMethodData(MethodData methodData) {
        kafkaMessagingService.sendMethodData(sendMethodDataEventMapper.toDto(methodData));
        log.info("Send order from producer {}", methodData);
        return methodData;
    }
}
