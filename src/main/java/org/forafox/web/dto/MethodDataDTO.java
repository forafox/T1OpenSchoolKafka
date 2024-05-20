package org.forafox.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.forafox.domain.enums.AnnotationType;

import java.util.Date;

@Data
@Schema(description = "DTO for methods data entity")
public class MethodDataDTO {
    @Schema(description = "Execution date of the method", example = "2024-05-04T23:29:18.525+00:00")
    private Date executeDate;
    @Schema(description = "Name of the method", example = "FilmServiceImpl.create(..)")
    private String methodName;
    @Schema(description = "Execution time of the method (nano)", example = "49")
    private Long executeNanoTime;
    @Schema(description = "Execution time of the method (ms)", example = "49")
    private Long executeMilliTime;
    @Schema(description = "Annotation type of the method", example = "ASYNC")
    private AnnotationType annotationType;
}
