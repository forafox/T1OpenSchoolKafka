package org.forafox.service;

import org.forafox.domain.Film;
import org.forafox.web.dto.FilmDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FilmService {
    Film create(FilmDTO filmDTO);

    Film getById(Long id);

    CompletableFuture<CompletableFuture<List<Film>>> getAll();

    CompletableFuture<CompletableFuture<List<Film>>> getByTitleName(String titleName);

    List<Film> getByGenre(String genre);

    List<Film> getByCountry(String country);

    CompletableFuture<CompletableFuture<List<Film>>> getByProducer(String producer);
}
