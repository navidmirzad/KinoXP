package com.example.kinoxp.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketID;
    private double price;

    @ManyToOne
    @JoinColumn(name = "show", referencedColumnName = "showID")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customerID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ceat", referencedColumnName = "seatID")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "TheaterHall", referencedColumnName = "theaterHallID")
    private TheaterHall theaterHall;

    public Ticket(int ticketID, double price, Show show, Customer customer, Seat seat, TheaterHall theaterHall) {
        this.ticketID = ticketID;
        this.price = price;
        this.show = show;
        this.customer = customer;
        this.seat = seat;
        this.theaterHall = theaterHall;
    }

    public Ticket() {

    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public TheaterHall getTheaterHall() {
        return theaterHall;
    }

    public void setTheaterHall(TheaterHall theaterHall) {
        this.theaterHall = theaterHall;
    }
}
