package com.example.kinoxp.controller;

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
    public ResponseEntity<Customer> login(@RequestBody Customer requestCustomer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(requestCustomer.getEmail());
        if (customerOptional.isPresent()) {
            Customer storedCustomer = customerOptional.get();

            // Compare the stored password with the inputted password
            if (storedCustomer.getPassword().equals(requestCustomer.getPassword())) {
                return ResponseEntity.ok(storedCustomer);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
