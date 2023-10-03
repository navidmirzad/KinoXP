package com.example.kinoxp.service;

import com.example.kinoxp.dto.MovieDTO;
import com.example.kinoxp.model.Genre;
import com.example.kinoxp.model.Movie;
import com.example.kinoxp.repositories.GenreRepository;
import com.example.kinoxp.repositories.MovieRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ApiServiceGetMoviesImpl implements ApiServiceGetMovies {

    private final String requestUrl = "https://imdb-top-100-movies.p.rapidapi.com/";
    private final String apiName = "X-RapidAPI-Key";
    private final String apiKey = "c72c895b0cmsh0dc387c464f5dffp1047cdjsn0a9a6d43c9fe";
    private final String hostName = "X-RapidAPI-Host";
    private final String hostKey = "imdb-top-100-movies.p.rapidapi.com";

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;


    private final RestTemplate restTemplate;

    public ApiServiceGetMoviesImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Transactional
    private void saveMovies(List<Movie> movies) {
        movies.forEach(movie -> movieRepository.save(movie));
    }

    private void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Movie> getMovies() {

        List<Movie> movies = new ArrayList<>();

        try {
            URI uri = new URI(requestUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.set(apiName, apiKey);
            headers.set(hostName, hostKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> request = new HttpEntity<>(headers);

            ResponseEntity<String> movieResponse = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    request,
                    String.class);
            System.out.println(movieResponse);

            String jsonData = movieResponse.getBody();
            System.out.println(jsonData);
            ObjectMapper objectMapper = new ObjectMapper();
            List<MovieDTO> movieDTOS = objectMapper.readValue(jsonData, new TypeReference<List<MovieDTO>>(){});

            for (MovieDTO movieData : movieDTOS) {
                Movie movie = new Movie();
                movie.setId(movieData.getMovieId());
                movie.setTitle(movieData.getTitle());
                movie.setDescription(movieData.getDescription());
                movie.setYear(movieData.getYear());
                movie.setImage(movieData.getImage());

                for (String genreName : movieData.getGenres()) {
                    Genre genre = genreRepository.findByName(genreName);
                    if (genre == null) {
                        genre = new Genre();
                        genre.setName(genreName);
                        genreRepository.save(genre);
                    }
                    movie.getGenres().add(genre);
                }

                // Save the movie entity
                saveMovie(movie);
                movies.add(movie);
            }



        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return movies;
    }



}
