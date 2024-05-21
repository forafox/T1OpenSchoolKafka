package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.forafox.kafka.service.messaging.event.SendMethodDataEvent;
import org.forafox.kafka.service.messaging.producer.Producer;
import org.forafox.web.dto.MethodDataDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/methodData")
@Tag(name = "Methods Data API", description = "API for methods data")
public class MethodDataController {
    private final Producer producer;

    @PostMapping("")
    public SendMethodDataEvent sendDataToKafka(
            @RequestBody SendMethodDataEvent sendMethodDataEvent
    ) {
        return producer.sendMethodData(sendMethodDataEvent);
    }
}
