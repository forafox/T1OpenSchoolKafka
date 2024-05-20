package org.forafox.web.mapper;

import org.forafox.domain.Film;
import org.forafox.web.dto.FilmDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FilmMapper {

    FilmDTO toDto(Film film);

    Film toEntity(FilmDTO filmDTO);

    List<FilmDTO> toDtoList(List<Film> list);
}
