package com.example.kinoxp.controller;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.Show;
import com.example.kinoxp.repositories.ShowRepository;
import com.example.kinoxp.service.ServiceGetSeatForShows;
import com.example.kinoxp.service.ServiceGetShowsAndTickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {

    @Autowired
    private ServiceGetShowsAndTickets serviceGetShowsAndTickets;

    @Autowired
    ServiceGetSeatForShows serviceGetSeatForShows;

    @Autowired
    private ShowRepository showRepository;

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
