package com.example.kinoxp.service;

import com.example.kinoxp.comparator.ShowComparator;
import com.example.kinoxp.model.Show;
import com.example.kinoxp.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    public List<Show> getSortedShows() {
        List<Show> allShows = showRepository.findAll();
        Collections.sort(allShows, new ShowComparator());
        return allShows;
    }

}
