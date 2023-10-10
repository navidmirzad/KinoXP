package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByCustomerId(int customerId);
    List<Ticket> findTicketsByShowId(int showId);

}
