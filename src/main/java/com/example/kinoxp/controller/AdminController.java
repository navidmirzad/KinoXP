package com.example.kinoxp.controller;

import com.example.kinoxp.model.Admin;
import com.example.kinoxp.repositories.AdminRepository;
import com.example.kinoxp.service.ApiServiceGetAdminImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private ApiServiceGetAdminImpl apiServiceGetAdminImpl;

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/kinoxp/login")
    public ResponseEntity<String> login(@RequestBody Admin admin){
        System.out.println("admin="+admin.getUsername());

        admin = apiServiceGetAdminImpl.authenticate(admin.getUsername(), admin.getPassword());
        if (admin != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed!");
        }
    }
}

