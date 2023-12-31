package com.example.kinoxp.controller;

import com.example.kinoxp.dto.TicketRequestDTO;
import com.example.kinoxp.model.Customer;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.repositories.CustomerRepository;
import com.example.kinoxp.repositories.TicketRepository;
import com.example.kinoxp.service.ServiceGetShowsAndTickets;
//import com.example.kinoxp.service.ServiceIssueTicket;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class TicketController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    /*@Autowired
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
    }*/

    @GetMapping("/kinoxp/tickets")
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @PutMapping("/kinoxp/posticket")
    public ResponseEntity<Ticket> postTicket(@RequestBody TicketRequestDTO ticketRequestDTO) {
        Optional<Ticket> findByIdTicket = ticketRepository.findById(ticketRequestDTO.getTicketId());
        Optional<Customer> findByUserNameCustomer = customerRepository.findCustomerByUserName(ticketRequestDTO.getUserName());

        if (findByIdTicket.isPresent() && findByUserNameCustomer.isPresent()) {
            Ticket ticket = findByIdTicket.get();
            Customer customer = findByUserNameCustomer.get();
            ticket.setCustomer(customer);
            ticketRepository.save(ticket);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

    @GetMapping("/kinoxp/tickets/byshow/{showId}")
    public ResponseEntity<List<Ticket>> getTicketsByMovieId(@PathVariable int showId) {
        List<Ticket> ticketsByMovieId = ticketRepository.findTicketsByShowId(showId)
                .stream()
                .sorted(Comparator.comparing(ticket -> ticket.getSeat().getSeatNumber()))
                .sorted(Comparator.comparing(ticket -> ticket.getSeat().getRowNumber()))
                .toList();
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


}
