package com.example.kinoxp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shows")
public class Show {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private LocalDate date;
        private LocalTime time;

        @ManyToOne
        @JoinColumn(name = "movie", referencedColumnName = "id") //
        private Movie movie;

        @ManyToOne
        @JoinColumn(name = "theaterhall", referencedColumnName = "id")
        private TheaterHall theaterHall;

        @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonBackReference
        private Set<Ticket> tickets = new HashSet<>();

        public Show() {

        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
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
