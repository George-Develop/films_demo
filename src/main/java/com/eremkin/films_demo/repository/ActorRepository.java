package com.eremkin.films_demo.repository;

import com.eremkin.films_demo.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
