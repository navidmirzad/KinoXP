package com.example.kinoxp.controller;

import com.example.kinoxp.model.TheaterHall;
import com.example.kinoxp.repositories.TheaterHallRepository;
import com.example.kinoxp.service.ServiceGetTheaterHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TheaterHallController {

    @Autowired
    private ServiceGetTheaterHall serviceGetTheaterHall;

    @Autowired
    private TheaterHallRepository theaterHallRepository;

    @GetMapping("/kinoxp/theater")
    public List<TheaterHall> getTheaters() {
        serviceGetTheaterHall.createTheaterHalls();
        return theaterHallRepository.findAll();
    }

}
