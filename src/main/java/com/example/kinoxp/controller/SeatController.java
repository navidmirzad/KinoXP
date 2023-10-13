package com.example.kinoxp.controller;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class SeatController {

    @Autowired
    private SeatRepository seatRepository;

    @GetMapping("/kinoxp/seats")
    public List<Seat> seats() {
        return seatRepository.findAll();
    }

}
