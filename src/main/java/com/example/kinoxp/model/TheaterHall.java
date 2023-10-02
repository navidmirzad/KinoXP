package com.example.kinoxp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class TheaterHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "theaterHall")
    @JsonBackReference
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy = "theaterHall")
    @JsonBackReference
    private Set<Show> shows = new HashSet<>();

    public TheaterHall(int id, String name, Set<Seat> seats, Set<Show> shows) {
        this.id = id;
        this.name = name;
        this.seats = seats;
        this.shows = shows;
    }

    public TheaterHall() {

    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }
}
