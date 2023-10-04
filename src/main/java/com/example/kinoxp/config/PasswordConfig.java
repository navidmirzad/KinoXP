package com.example.kinoxp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean // vi ser til spring at den skal behandle den her metode som en bean
    public PasswordEncoder passwordEncoder() { // vi implemeneterer PasswordEncoder interfacet
        return new BCryptPasswordEncoder(); // vi opretter en instans af BCryptPasswordEncoder interfacet,
        // som er en specifik implementation der sørger for vores værdi bliver krypteret ved en BCrypt hashing alrogitme.
    }


}