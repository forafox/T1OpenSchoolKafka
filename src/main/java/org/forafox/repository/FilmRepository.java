package org.forafox.repository;

import org.forafox.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<List<Film>> findFilmByTitleName(String titleName);

    Optional<List<Film>> findAllByGenre(String genre);

    Optional<List<Film>> findFilmByCountry(String country);

    Optional<List<Film>> findFilmByProducer(String producer);
}
