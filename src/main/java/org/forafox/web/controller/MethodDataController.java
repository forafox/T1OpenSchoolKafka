package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.forafox.service.messaging.event.SendMethodDataEvent;
import org.forafox.service.messaging.producer.Producer;
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
    @Operation(summary = "Create a new method data", description = "Creates a new method data", operationId = "createMethodData")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public SendMethodDataEvent sendDataToKafka(
            @RequestBody SendMethodDataEvent sendMethodDataEvent
    ) {
        return producer.sendMethodData(sendMethodDataEvent);
    }
}
