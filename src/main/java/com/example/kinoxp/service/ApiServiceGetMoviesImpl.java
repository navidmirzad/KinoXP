package com.example.kinoxp.service;

import com.example.kinoxp.config.RestTemplateConfig;
import com.example.kinoxp.model.Movie;
import com.example.kinoxp.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private final RestTemplate restTemplate;

    public ApiServiceGetMoviesImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private void saveMovies(List<Movie> movies) {
        movies.forEach(movie -> movieRepository.save(movie));
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

            ResponseEntity<List<Movie>> movieResponse = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<List<Movie>>() {
                    });
            movies = movieResponse.getBody();
            saveMovies(movies);

        } catch (URISyntaxException e) {

        }
        return movies;
    }



}
