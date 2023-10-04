package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Genre findByName(String name);
    Optional<Genre> findById(Integer id);

}
