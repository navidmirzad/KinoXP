package com.example.kinoxp.controller;

import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.repositories.TicketRepository;
import com.example.kinoxp.service.ServiceGetShowsAndTickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/kinoxp/tickets")
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

}
