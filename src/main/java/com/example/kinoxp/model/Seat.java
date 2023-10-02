package com.example.kinoxp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Seat {

    @Id
    private String seatID;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "theaterHall", referencedColumnName = "theaterHallID")
    private TheaterHall theaterHall;

    public Seat(String seatID, Set<Ticket> tickets, TheaterHall theaterHall) {
        this.seatID = seatID;
        this.tickets = tickets;
        this.theaterHall = theaterHall;
    }

    public Seat() {

    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
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
}
