package com.example.kinoxp.controller;

import com.example.kinoxp.model.Show;
import com.example.kinoxp.repositories.ShowRepository;
import com.example.kinoxp.service.ServiceGetShowsAndTickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowController {

    @Autowired
    private ServiceGetShowsAndTickets serviceGetShowsAndTickets;

    @Autowired
    private ShowRepository showRepository;

    @GetMapping("/kinoxp/shows")
    public List<Show> getShows() {
        serviceGetShowsAndTickets.createShowsAndTickets();
        return showRepository.findAll();
    }

}
