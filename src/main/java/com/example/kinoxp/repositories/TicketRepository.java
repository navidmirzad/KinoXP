package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.Show;
import com.example.kinoxp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findByShowAndSeat(Show show, Seat seat);
}
