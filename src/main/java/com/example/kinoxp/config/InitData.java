package com.example.kinoxp.config;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.service.ApiServiceGetMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private ApiServiceGetMovies apiServiceGetMovies;

    @Override
    public void run(String... args) throws Exception {

    }
}
