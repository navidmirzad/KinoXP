package com.example.kinoxp.service;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.Show;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.repositories.SeatRepository;
import com.example.kinoxp.repositories.ShowRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceGetSeatForShowsImpl implements ServiceGetSeatForShows {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    SeatRepository seatRepository;

    @Transactional
    public List<Seat> getAvailableSeats(int showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        List<Seat> allSeats = seatRepository.findByTheaterHall(show.getTheaterHall());

        List<Seat> reservedSeats = show.getTickets()
                .stream()
                .map(Ticket::getSeat)
                .collect(Collectors.toList());

        allSeats.remove(reservedSeats);

        return allSeats;
    }
}
