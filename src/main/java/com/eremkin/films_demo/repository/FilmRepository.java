package com.eremkin.films_demo.repository;

import com.eremkin.films_demo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
