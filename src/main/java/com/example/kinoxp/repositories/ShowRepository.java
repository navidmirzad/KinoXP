package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    List<Show> findShowsByMovieId(int movieId);
    List<Show> findShowById(int showId);
    Optional<Show> findById(int id);
    List<Show> findByMovie(Movie movie);
}
