package com.eremkin.films_demo.repository;

import com.eremkin.films_demo.model.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, Long> {
}
