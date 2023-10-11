package com.example.kinoxp.controller;

import com.example.kinoxp.dto.LoginDTO;
import com.example.kinoxp.dto.PostCustomerDTO;
import com.example.kinoxp.model.Customer;
import com.example.kinoxp.repositories.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/kinoxp/customerlogin")
    public ResponseEntity<Customer> login(@RequestBody LoginDTO loginDTO) {

        Optional<Customer> customerOptional = customerRepository.findCustomerByUserNameAndPassword(loginDTO.getUserName(),
                loginDTO.getPassword());

        if (customerOptional.isPresent()) {

            Customer customer = customerOptional.get();


            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/kinoxp/createaccount")
    public ResponseEntity<Customer> createAccount (@RequestBody PostCustomerDTO customerDTO) {
        System.out.println("kommer jeg herind??");
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setUserName(customerDTO.getUserName());
        customer.setPassword(customerDTO.getPassword());

        Optional<Customer> existingCustomerEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        Optional<Customer> existingCustomerUsername = customerRepository.findCustomerByUserName(customer.getEmail());
        if (existingCustomerEmail.isPresent() || existingCustomerUsername.isPresent()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            customerRepository.save(customer);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        }
    }

}
