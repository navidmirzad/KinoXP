package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByEmailAndPassword(String email, String password );
}
