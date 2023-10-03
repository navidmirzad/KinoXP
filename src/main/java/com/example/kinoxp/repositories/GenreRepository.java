package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Genre findByName(String name);

}
