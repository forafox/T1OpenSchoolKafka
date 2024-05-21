package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.annotation.Throw;
import org.forafox.annotation.TrackAsyncTime;
import org.forafox.annotation.TrackTime;
import org.forafox.domain.Film;
import org.forafox.exception.ResourceNotFoundException;
import org.forafox.repository.FilmRepository;
import org.forafox.service.FilmService;
import org.forafox.web.dto.FilmDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service

public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    @Override
    @TrackTime
    public Film create(FilmDTO filmDTO) {
        var film = new Film();
        film.setTitleName(filmDTO.getTitleName());
        film.setGenre(filmDTO.getGenre());
        film.setCountry(filmDTO.getCountry());
        film.setProducer(filmDTO.getProducer());
        return filmRepository.save(film);
    }

    @Override
    @Throw
    @TrackTime
    public Film getById(Long id){
        return filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
    }

    @Override
    @Async
    @TrackAsyncTime
    public CompletableFuture<CompletableFuture<List<Film>>> getAll() {
        return CompletableFuture.completedFuture(CompletableFuture.completedFuture(filmRepository.findAll()));
    }

    @Override
    @Throw
    @Async
    @TrackAsyncTime
    public CompletableFuture<CompletableFuture<List<Film>>> getByTitleName(String titleName){
        return CompletableFuture.completedFuture(CompletableFuture.completedFuture(filmRepository.findFilmByTitleName(titleName).orElseThrow(() -> new ResourceNotFoundException("Film not found"))));
    }

    @Override
    @Throw
    @TrackTime
    public List<Film> getByGenre(String genre) {
        return filmRepository.findAllByGenre(genre).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
    }

    @Override
    @Throw
    @TrackTime
    public List<Film> getByCountry(String country) {
        return filmRepository.findFilmByCountry(country).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
    }

    @Override
    @Throw
    @Async
    @TrackAsyncTime
    public CompletableFuture<CompletableFuture<List<Film>>> getByProducer(String producer) {
        return CompletableFuture.completedFuture(CompletableFuture.completedFuture(filmRepository.findFilmByProducer(producer).orElseThrow(() -> new ResourceNotFoundException("Film not found"))));
    }
}
