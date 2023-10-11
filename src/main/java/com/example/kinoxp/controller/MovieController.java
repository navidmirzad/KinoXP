package com.example.kinoxp.controller;

import com.example.kinoxp.dto.PostMovieDTO;
import com.example.kinoxp.model.Admin;
import com.example.kinoxp.model.Genre;
import com.example.kinoxp.model.Movie;
import com.example.kinoxp.model.Show;
import com.example.kinoxp.repositories.GenreRepository;
import com.example.kinoxp.repositories.MovieRepository;
import com.example.kinoxp.repositories.ShowRepository;
import com.example.kinoxp.service.ApiServiceGetMovies;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
@CrossOrigin
public class MovieController {

    @Autowired
    private ApiServiceGetMovies apiServiceGetMovies;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;
    // get all movies from imdb api. shouldn't be called that often - 100 calls per day

    @GetMapping("/api/admin/imdb")
    public List<Movie> getImdbMovies() {
        List<Movie> movies = apiServiceGetMovies.getMovies();
        return movies;
    }

    @GetMapping("/kinoxp")
    public List<Movie> frontpageMovies() {
        return movieRepository.findAll();
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

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> findMovie(@PathVariable Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> editMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        Optional<Movie> findingMovie = movieRepository.findById(id);
        if (findingMovie.isPresent()) {
            movie.setId(id);
            movieRepository.save(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();

            // Get the associated shows for this movie
            List<Show> shows = showRepository.findByMovie(movie);

            // Delete the shows and their associated tickets
            for (Show show : shows) {
                showRepository.delete(show);
            }

            // Now you can safely delete the movie
            try {
                movieRepository.deleteById(id);
                movieRepository.delete(movie);
            } catch (Exception e) {
                System.out.println("Fejl i delete Movie" + e.getMessage());
            }

            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
