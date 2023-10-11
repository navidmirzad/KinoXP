package com.example.kinoxp.controller;

import com.example.kinoxp.model.Customer;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.repositories.CustomerRepository;
import com.example.kinoxp.repositories.TicketRepository;
import com.example.kinoxp.service.ServiceGetShowsAndTickets;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TicketController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/kinoxp/tickets")
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/kinoxp/tickets/byshow/{showId}")
    public ResponseEntity<List<Ticket>> getTicketsByMovieId(@PathVariable int showId) {
        List<Ticket> ticketsByMovieId = ticketRepository.findTicketsByShowId(showId);
        return new ResponseEntity<>(ticketsByMovieId, HttpStatus.OK);
    }

    @GetMapping("/kinoxp/tickets/{userName}")
    public ResponseEntity<List<Ticket>> getTicketsByUserName(@PathVariable String userName) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByUserName(userName);
        System.out.println(userName);

        if (optionalCustomer.isPresent()) {
            System.out.println("er jeg present");
            Customer customer = optionalCustomer.get();
            List<Ticket> listOfTickets = ticketRepository.findByCustomerId(customer.getId());
            return new ResponseEntity<>(listOfTickets, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //localhost:8080/kinoxp/tickets/customer

    @PostMapping("/kinoxp/tickets/add")
    public ResponseEntity<Ticket> orderTicket(@RequestParam int ticketId, HttpSession session) {

        Integer customerId = (Integer) session.getAttribute("customerId");
        Optional<Customer> customerById = customerRepository.findById(customerId);

        if (customerById.isPresent()) {
            // Now you have the customer's ID, which you can use to associate the ticket with the customer.
            Customer customer = customerById.get();

            // Find the ticket in your repository by its ID
            Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

            if (ticketOptional.isPresent()) {
                Ticket ticket = ticketOptional.get();

                // Set the customer for the ticket
                ticket.setCustomer(customer);

                // Save the ticket back to the database to associate it with the customer
                ticketRepository.save(ticket);

                return ResponseEntity.ok(ticket);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PostMapping("/kinoxp/cancel/{ticketId}")
    public ResponseEntity<Ticket> cancelTicket(@PathVariable int ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setCustomer(null);
            ticketRepository.save(ticket);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
