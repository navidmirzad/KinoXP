package com.example.kinoxp.controller;

import com.example.kinoxp.exception.ResourceNotFoundException;
import com.example.kinoxp.model.Customer;
import com.example.kinoxp.repositories.CustomerRepository;
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
    public ResponseEntity<Customer> login(@RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {

            Customer customer1 = customerOptional.get();

            // Compare the stored password with the inputted password
            if (customer.getPassword().equals(customer1.getPassword())) {
                return ResponseEntity.ok(customer1);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


//    @PostMapping("/kinoxp")
//    public ResponseEntity<Customer> postKommune(@RequestBody Customer customer) {
//        System.out.println("Indsætter ny Customer");
//        System.out.println(customer);
//        Customer savedKommune = customerRepository.save(customer);
//        if (savedKommune == null) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        } else {
//            return new ResponseEntity<>(savedKommune, HttpStatus.CREATED);
//        }
//    }
}
