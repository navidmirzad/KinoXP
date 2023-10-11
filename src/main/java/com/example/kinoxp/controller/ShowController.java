package com.example.kinoxp.controller;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.Show;
import com.example.kinoxp.repositories.ShowRepository;
import com.example.kinoxp.service.ServiceGetSeatForShows;
import com.example.kinoxp.service.ServiceGetShowsAndTickets;
import com.example.kinoxp.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ShowController {

    @Autowired
    private ServiceGetShowsAndTickets serviceGetShowsAndTickets;

    @Autowired
    ServiceGetSeatForShows serviceGetSeatForShows;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowService showService;

    @GetMapping("/kinoxp/shows1")
    public List<Show> getShows1() {
        serviceGetShowsAndTickets.createShowsAndTicketsForSmallTheater();
        return showRepository.findAll();
    }

    @GetMapping("/kinoxp/shows2")
    public List<Show> getShows2() {
        serviceGetShowsAndTickets.createShowsAndTicketsForBigTheater();
        return showRepository.findAll();
    }

    @GetMapping("/kinoxp/allshows")
    public ResponseEntity<List<Show>> getSortedShows() {
        List<Show> sortedShows = showService.getSortedShows();
        return new ResponseEntity<>(sortedShows, HttpStatus.OK);
    }

    @GetMapping("/kinoxp/allshows/{movieId}")
    public ResponseEntity<List<Show>> getShowsByMovieId(@PathVariable int movieId) {
        List<Show> showsByMovieId = showRepository.findShowsByMovieId(movieId);
        return new ResponseEntity<>(showsByMovieId, HttpStatus.OK);
    }

    @GetMapping("/kinoxp/{showId}/availableSeats")
    public ResponseEntity<?> getAvailableSeatsForShowtime(@PathVariable int showId) {
        try {
            List<Seat> availableSeats = serviceGetSeatForShows.getAvailableSeats(showId);
            return new ResponseEntity<>(availableSeats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
