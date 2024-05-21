package org.forafox.web.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.forafox.domain.enums.AnnotationType;

@Data
@Schema(description = "DTO for method data statistics")
public class MethodDataStatDTO {
    @Schema(description = "Name of the method", example = "")
    private String methodName;
    @Schema(description = "Minimum method execution time (ms)", example = "1")
    private Long minExecuteTimeMilli;
    @Schema(description = "Maximum method execution time (ms)", example = "42")
    private Long maxExecuteTimeMilli;
    @Schema(description = "Execution time of the method (ms)", example = "49")
    private Long avgExecuteTimeMilli;
    @Schema(description = "Minimum method execution time (nano)", example = "112312")
    private Long minExecuteTimeNano;
    @Schema(description = "Maximum method execution time (nano)", example = "1903121")
    private Long maxExecuteTimeNano;
    @Schema(description = "Execution time of the method (nano)", example = "18156778")
    private Long avgExecuteTimeNano;

    @Schema(description = "Annotation type of the method", example = "ASYNC")
    private AnnotationType annotationType;
}
