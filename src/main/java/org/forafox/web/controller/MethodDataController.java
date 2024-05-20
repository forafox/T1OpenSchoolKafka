package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.forafox.kafka.service.messaging.producer.Producer;
import org.forafox.service.MethodDataService;
import org.forafox.web.dto.MethodDataDTO;
import org.forafox.web.dto.MethodDataStatDTO;
import org.forafox.web.mapper.MethodDataMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/methodData")
@Tag(name = "Methods Data API", description = "API for methods data")
public class MethodDataController {
    private final MethodDataService methodDataService;
    private final MethodDataMapper methodDataMapper;
    private final Producer producer;

    @GetMapping("")
    @Operation(summary = "Get all methods data", description = "Get a list of all  methods data.", operationId = "getAllMethodsData")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public List<MethodDataDTO> getAllMethodsData() {
        return methodDataMapper.toDtoList(methodDataService.getAll());
    }

    @GetMapping("/{methodName}")
    @Operation(summary = "Get all method data by method name", description = "Get all methods data by its name.", operationId = "getAllMethodsDataByMethodName")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public List<MethodDataDTO> getAllMethodsDataByMethodName(
            @Parameter(description = "Name of the method to be obtained", required = true) @PathVariable String methodName
    ) {
        return methodDataMapper.toDtoList(methodDataService.getAllMethodsByMethodName(methodName));
    }

    @GetMapping("/stat/{methodName}")
    @Operation(summary = "Get statistic from method data by method name", description = "Get statistic from all methods data by its name.", operationId = "getStatByMethodName")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public MethodDataStatDTO getStatByMethodName(
            @Parameter(description = "Name of the method to be obtained", required = true) @PathVariable String methodName
    ) {
        return methodDataService.getStat(methodName);
    }

    @DeleteMapping("")
    @Operation(summary = "Delete all methods data", description = "Delete all methods data ", operationId = "deleteAllMethodsData")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public ResponseEntity<String> deleteAllMethodsData(
    ) {
        methodDataService.clearData();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/metrics")
    public MethodDataDTO sendDataToKafka(
            @RequestBody MethodDataDTO methodDataDTO
    ) {
        return methodDataMapper.toDto(producer.sendMethodData(methodDataMapper.toEntity(methodDataDTO)));
    }


}
