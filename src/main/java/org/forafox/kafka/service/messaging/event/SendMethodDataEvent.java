package org.forafox.kafka.service.messaging.event;

import lombok.Data;
import org.forafox.domain.enums.AnnotationType;

import java.util.Date;

@Data
public class SendMethodDataEvent {
    private Date executeDate;
    private String methodName;
    private Long executeNanoTime;
    private Long executeMilliTime;
    private AnnotationType annotationType;
}
