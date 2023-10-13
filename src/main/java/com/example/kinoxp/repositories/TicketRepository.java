package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.Show;
import com.example.kinoxp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByCustomerId(int customerId);
    List<Ticket> findTicketsByShowId(int showId);

    Optional<Ticket> findByShowAndSeat(Show show, Seat seat);
}
