package com.example.kinoxp.service;

import com.example.kinoxp.dto.TicketRequestDTO;
import com.example.kinoxp.model.Customer;
import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.Show;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.repositories.CustomerRepository;
import com.example.kinoxp.repositories.SeatRepository;
import com.example.kinoxp.repositories.ShowRepository;
import com.example.kinoxp.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceIssueTicketImpl implements ServiceIssueTicket {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Ticket assignCustomerToTicket(TicketRequestDTO request) {
        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new IllegalArgumentException("Show not found"));

        Seat seat = seatRepository.findById(request.getSeatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Find the ticket by show and seat
        Ticket ticket = ticketRepository.findByShowAndSeat(show, seat)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found for given show and seat"));

        ticket.setCustomer(customer);

        return ticketRepository.save(ticket);
    }
}
