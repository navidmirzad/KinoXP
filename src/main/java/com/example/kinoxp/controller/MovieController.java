package com.example.kinoxp.controller;

import com.example.kinoxp.dto.MovieDTO;
import com.example.kinoxp.dto.PostMovieDTO;
import com.example.kinoxp.model.Genre;
import com.example.kinoxp.model.Movie;
import com.example.kinoxp.repositories.GenreRepository;
import com.example.kinoxp.repositories.MovieRepository;
import com.example.kinoxp.service.ApiServiceGetMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class MovieController {

    @Autowired
    private ApiServiceGetMovies apiServiceGetMovies;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieRepository movieRepository;
    // get all movies from imdb api. shouldnt be called that often - 100 calls per day

    @GetMapping("/api/admin/imdb")
    public List<Movie> getImdbMovies() {
        List<Movie> movies = apiServiceGetMovies.getMovies();
        return movies;
    }

    @PostMapping("/addmovie")
    public ResponseEntity<Movie> addMovie(@RequestBody PostMovieDTO postMovieDTO) {
        Movie movie = new Movie();
        movie.setTitle(postMovieDTO.getTitle());
        movie.setDescription(postMovieDTO.getDescription());
        movie.setImage(postMovieDTO.getImage());
        movie.setYear(postMovieDTO.getYear());
        List<Genre> genres = genreRepository.findAllById(postMovieDTO.getGenres());
        Set<Genre> setGenre = new HashSet<>();
        genres.forEach(a -> setGenre.add(a));
        movie.setGenres(setGenre);
        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
