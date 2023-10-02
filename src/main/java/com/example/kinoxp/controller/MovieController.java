package com.example.kinoxp.controller;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.service.ApiServiceGetMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private ApiServiceGetMovies apiServiceGetMovies;

    // get all movies from imdb api. shouldnt be called that often - 100 calls per day

    @GetMapping("/api/admin/imdb")
    public List<Movie> getImdbMovies() {
        List<Movie> movies = apiServiceGetMovies.getMovies();
        return movies;
    }

}
