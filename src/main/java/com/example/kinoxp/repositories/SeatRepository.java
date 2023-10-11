package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.TheaterHall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findAllByTheaterHallId(int id);
    List<Seat> findByTheaterHall(TheaterHall theaterHall);

}
