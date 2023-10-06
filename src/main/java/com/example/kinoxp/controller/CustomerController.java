package com.example.kinoxp.controller;

import ch.qos.logback.core.model.Model;
import com.example.kinoxp.exception.ResourceNotFoundException;
import com.example.kinoxp.model.Customer;
import com.example.kinoxp.repositories.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/kinoxp/login")
    public ResponseEntity<Customer> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        // Authenticate user
        Optional<Customer> authenticatedUser = customerRepository.findCustomerByEmailAndPassword(email, password);
        if (authenticatedUser.isPresent()) {
            // Create session for user and set session timeout to 15min (container default: 15 min)
            session.setAttribute("user", authenticatedUser.get());
            session.setMaxInactiveInterval(900);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        // Wrong login info
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
