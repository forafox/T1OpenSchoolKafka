package org.forafox.web.controller;

import org.forafox.web.mapper.FilmMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.forafox.service.FilmService;
import org.forafox.web.dto.FilmDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/film")
@Tag(name = "Film API", description = "API for films")
public class FilmController {
    private final FilmService filmService;
    private final FilmMapper filmMapper;


    @PostMapping("")
    @Operation(summary = "Create a new film", description = "Creates a new film", operationId = "createFilm")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public FilmDTO createFilm(
            @RequestBody FilmDTO filmDTO
    ) {
        return filmMapper.toDto(filmService.create(filmDTO));
    }

    @GetMapping("")
    @Operation(summary = "Get all films", description = "Get a list of all films.", operationId = "getAllFilms")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public List<FilmDTO> getAllFilms() throws ExecutionException, InterruptedException {
        return filmMapper.toDtoList(filmService.getAll().get().get());
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get film by ID", description = "Get a film by its ID.", operationId = "getFilmById")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public FilmDTO getFilmById(
            @Parameter(description = "ID of the film to be obtained", required = true) @PathVariable Long id
    ) {
        return filmMapper.toDto(filmService.getById(id));
    }

    @GetMapping("/titleName/{titleName}")
    @Operation(summary = "Get film by title", description = "Get a film by its title name.", operationId = "getFilmByTitleName")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public List<FilmDTO> getFilmByTitleName(
            @Parameter(description = "Title name of the film to be obtained", required = true) @PathVariable String titleName
    ) throws ExecutionException, InterruptedException {
        return filmMapper.toDtoList(filmService.getByTitleName(titleName).get().get());
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Get film by genre", description = "Get a film by its genre.", operationId = "getFilmByGenre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public List<FilmDTO> getFilmByGenre(
            @Parameter(description = "Genre of the film to be obtained", required = true) @PathVariable String genre
    ) {
        return filmMapper.toDtoList(filmService.getByGenre(genre));
    }

    @GetMapping("/country/{country}")
    @Operation(summary = "Get film by country", description = "Get a film by its country.", operationId = "getFilmByCountry")
    @ApiResponses({
           @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public List<FilmDTO> getFilmByCountry(
            @Parameter(description = "Country of the film to be obtained", required = true) @PathVariable String country
    ) {
        return filmMapper.toDtoList(filmService.getByCountry(country));
    }

    @GetMapping("/producer/{producer}")
    @Operation(summary = "Get film by producer", description = "Get a film by its producer.", operationId = "getFilmByProducer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public List<FilmDTO> getFilmByProducer(
            @Parameter(description = "Producer of the film to be obtained", required = true) @PathVariable String producer
    ) throws ExecutionException, InterruptedException {
        return filmMapper.toDtoList(filmService.getByProducer(producer).get().get());
    }
}
