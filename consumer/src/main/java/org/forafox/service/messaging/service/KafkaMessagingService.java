package org.forafox.service.messaging.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.forafox.mapper.MethodDataMapper;
import org.forafox.service.MethodDataService;
import org.forafox.web.controller.dto.MethodDataDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaMessagingService {
    private static final String topicCreateMethodData = "${topic.send-methodData-event}";
    private static final String topicCreateAsyncMethodData = "${topic.send-asyncMethodData-event}";

    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";
    private final MethodDataService methodDataService;
    private final MethodDataMapper methodDataMapper;

    @Transactional
    @KafkaListener(topics = topicCreateMethodData, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=org.forafox.web.controller.dto.MethodDataDTO"})
    public MethodDataDTO createMethodData(MethodDataDTO methodData) {
        log.info("Message consumed {}", methodData);
        methodDataService.save(methodDataMapper.toEntity(methodData));
        return methodData;
    }

    @Transactional
    @KafkaListener(topics = topicCreateAsyncMethodData, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=org.forafox.web.controller.dto.MethodDataDTO"})
    public MethodDataDTO createAsyncMethodData(MethodDataDTO methodData) {
        log.info("Message consumed {}", methodData);
        methodDataService.save(methodDataMapper.toEntity(methodData));
        return methodData;
    }


}