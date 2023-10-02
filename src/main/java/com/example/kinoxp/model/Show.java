package com.example.kinoxp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shows")
public class Show {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int showID;
        private LocalDate date;
        private LocalTime time;

        @ManyToOne
        @JoinColumn(name = "movie", referencedColumnName = "ID") //
        private Movie movie;

        @ManyToOne
        @JoinColumn(name = "theaterHall", referencedColumnName = "theaterHallID")
        private TheaterHall theaterHall;

        @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
        @JsonBackReference
        private Set<Ticket> tickets = new HashSet<>();

        public Show(int showID, LocalDate date, LocalTime time, Movie movie, TheaterHall theaterHall, Set<Ticket> tickets) {
                this.showID = showID;
                this.date = date;
                this.time = time;
                this.movie = movie;
                this.theaterHall = theaterHall;
                this.tickets = tickets;
        }

        public Show() {

        }

        public int getShowID() {
                return showID;
        }

        public void setShowID(int showID) {
                this.showID = showID;
        }

        public LocalDate getDate() {
                return date;
        }

        public void setDate(LocalDate date) {
                this.date = date;
        }

        public LocalTime getTime() {
                return time;
        }

        public void setTime(LocalTime time) {
                this.time = time;
        }

        public Movie getMovie() {
                return movie;
        }

        public void setMovie(Movie movie) {
                this.movie = movie;
        }

        public TheaterHall getTheaterHall() {
                return theaterHall;
        }

        public void setTheaterHall(TheaterHall theaterHall) {
                this.theaterHall = theaterHall;
        }

        public Set<Ticket> getTickets() {
                return tickets;
        }

        public void setTickets(Set<Ticket> tickets) {
                this.tickets = tickets;
        }
}
