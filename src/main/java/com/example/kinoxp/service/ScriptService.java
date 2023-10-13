package com.example.kinoxp.service;

import com.example.kinoxp.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScriptService {

    @Autowired
    private ApiServiceGetMovies apiServiceGetMovies;

    @Autowired
    private ServiceGetTheaterHall serviceGetTheaterHall;

    @Autowired
    private ServiceGetShowsAndTickets serviceGetShowsAndTickets;


    public void programStart() {
        // gets movies
        List<Movie> movies = apiServiceGetMovies.getMovies();

        // creates small + big theaterhall and admin 1 and 2
        serviceGetTheaterHall.createTheaterHalls();

        // creates shows, and tickets for small theater
        serviceGetShowsAndTickets.createShowsAndTicketsForSmallTheater();

        // creates shows, and tickets for big theater
        serviceGetShowsAndTickets.createShowsAndTicketsForBigTheater();

    }





}
