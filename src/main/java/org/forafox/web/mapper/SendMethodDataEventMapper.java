package org.forafox.web.mapper;

import org.forafox.domain.MethodData;
import org.forafox.kafka.service.messaging.event.SendMethodDataEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SendMethodDataEventMapper {
    SendMethodDataEvent toDto(MethodData methodData);

    MethodData toEntity(SendMethodDataEvent sendMethodDataEvent);

    List<SendMethodDataEvent> toDtoList(List<MethodData> list);
}
