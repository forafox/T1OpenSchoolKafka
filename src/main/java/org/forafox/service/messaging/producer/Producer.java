package org.forafox.service.messaging.producer;

import org.forafox.service.messaging.event.SendMethodDataEvent;
import org.forafox.service.messaging.service.KafkaMessagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaMessagingService kafkaMessagingService;
    public SendMethodDataEvent sendMethodData(SendMethodDataEvent methodData) {
        kafkaMessagingService.sendMethodData(methodData);
        log.info("Send method data from producer {}", methodData);
        return methodData;
    }
    public SendMethodDataEvent sendAsyncMethodData(SendMethodDataEvent methodData) {
        kafkaMessagingService.sendAsyncMethodData(methodData);
        log.info("Send async method data from producer {}", methodData);
        return methodData;
    }

}
