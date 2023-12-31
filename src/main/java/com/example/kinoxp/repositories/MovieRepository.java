package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Customer;
import com.example.kinoxp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findMovieById(int id);
}
