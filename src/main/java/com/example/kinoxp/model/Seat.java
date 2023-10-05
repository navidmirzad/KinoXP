package com.example.kinoxp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Seat {

    @Id
    private String id;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "theaterhall", referencedColumnName = "id")
    private TheaterHall theaterHall;

    public Seat() {

    }


    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public TheaterHall getTheaterHall() {
        return theaterHall;
    }

    public void setTheaterHall(TheaterHall theaterHall) {
        this.theaterHall = theaterHall;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
