package com.example.kinoxp.service;

import com.example.kinoxp.model.Seat;

import java.util.List;

public interface ServiceGetSeatForShows {
    List<Seat> getAvailableSeats(int showId);
}
