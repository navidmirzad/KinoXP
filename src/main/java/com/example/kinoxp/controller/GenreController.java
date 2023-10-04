package com.example.kinoxp.controller;

import com.example.kinoxp.model.Genre;
import com.example.kinoxp.repositories.GenreRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GenreController {

    @Autowired
    GenreRepository genreRepository;

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> listOfGenres() {
        List<Genre> findAllGenres = genreRepository.findAll();
        return new ResponseEntity<>(findAllGenres, HttpStatus.OK);
    }

}
