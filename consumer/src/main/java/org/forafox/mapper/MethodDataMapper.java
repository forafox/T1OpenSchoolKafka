package org.forafox.mapper;

import org.forafox.domain.MethodData;
import org.forafox.web.controller.dto.MethodDataDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MethodDataMapper {
    MethodDataDTO toDto(MethodData methodData);

    MethodData toEntity(MethodDataDTO methodDataDTO);

    List<MethodDataDTO> toDtoList(List<MethodData> methodData);
}

