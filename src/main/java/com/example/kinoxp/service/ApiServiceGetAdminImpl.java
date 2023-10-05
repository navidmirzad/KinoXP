package com.example.kinoxp.service;


import com.example.kinoxp.model.Admin;
import com.example.kinoxp.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ApiServiceGetAdminImpl {


    @Autowired
    private AdminRepository adminRepository;

    public Admin authenticate(String username, String password) {
        Admin admin = adminRepository.findById(username).orElse(null);

        if (admin != null) {
            if (admin.getPassword().equals(password)) {
                return admin;
            }
        }

        return null;
    }

}