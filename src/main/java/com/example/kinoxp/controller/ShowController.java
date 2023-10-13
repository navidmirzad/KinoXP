package com.example.kinoxp.controller;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.Show;
import com.example.kinoxp.repositories.ShowRepository;
import com.example.kinoxp.service.ServiceGetSeatForShows;
import com.example.kinoxp.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class ShowController {

    @Autowired
    ServiceGetSeatForShows serviceGetSeatForShows;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowService showService;



    @GetMapping("/kinoxp/allshows")
    public ResponseEntity<List<Show>> getSortedShows() {
        List<Show> sortedShows = showService.getSortedShows();
        return new ResponseEntity<>(sortedShows, HttpStatus.OK);
    }

    @GetMapping("/kinoxp/allshows/{movieId}")
    public ResponseEntity<List<Show>> getShowsByMovieId(@PathVariable int movieId) {

        LocalDate today = LocalDate.now();
        List<Show> allShows = showRepository.findAll();

        for (Show show : allShows) {
            if (show.getDate().isBefore(today)) {
                showRepository.delete(show);
            }
        }

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
