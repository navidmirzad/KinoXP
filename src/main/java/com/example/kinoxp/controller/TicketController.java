package com.example.kinoxp.controller;

import com.example.kinoxp.dto.TicketRequestDTO;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.repositories.TicketRepository;
import com.example.kinoxp.service.ServiceGetShowsAndTickets;
import com.example.kinoxp.service.ServiceIssueTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ServiceIssueTicket serviceIssueTicket;

    @PostMapping("/kinoxp/ticket")
    public ResponseEntity<Ticket> assignCustomerToTicket(@RequestBody TicketRequestDTO request) {
        try {
            Ticket ticket = serviceIssueTicket.assignCustomerToTicket(request);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/kinoxp/tickets")
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

}
