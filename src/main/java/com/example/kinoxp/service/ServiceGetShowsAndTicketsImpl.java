package com.example.kinoxp.service;

import com.example.kinoxp.model.*;
import com.example.kinoxp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class ServiceGetShowsAndTicketsImpl implements ServiceGetShowsAndTickets {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterHallRepository theaterHallRepository;

    @Autowired
    private SeatRepository seatRepository;

    private Random random = new Random();

    public void createShowsAndTickets() {

        // GET SMALL THEATER
        Optional<TheaterHall> smallTheater = theaterHallRepository.findById(1);

        // GET SEATS FOR SMALL THEATER
        List<Seat> seatsSmallTheater = seatRepository.findAllByTheaterHallId(1);

        // Calculate the date range for the next 3 months
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusMonths(1);

        // Define the initial show time as 10:00 AM
        LocalTime initialTime = LocalTime.of(10, 0);

        // Iterate through each day in the date range
        LocalDate currentDate = startDate;
        while (currentDate.isBefore(endDate)) {
            LocalTime currentTime = initialTime;

            if (smallTheater.isPresent()) {
                TheaterHall theaterHall = smallTheater.get();

                while (currentTime.isBefore(LocalTime.of(22, 0))) { // Limit show times to 10:00 PM
                    // Ensure two shows can't be scheduled at the same time in the same theater
                    boolean isTimeSlotAvailable = true;
                    for (Show existingShow : theaterHall.getShows()) {
                        if (existingShow.getDate().isEqual(currentDate) && existingShow.getTime().equals(currentTime)) {
                            isTimeSlotAvailable = false;
                            break;
                        }
                    }

                    if (isTimeSlotAvailable) {
                        // CREATE RANDOM MOVIE
                        List<Movie> movies = movieRepository.findAll();
                        int randomIndexMovie = random.nextInt(1, movies.size());
                        Movie randomMovie = movies.get(randomIndexMovie);

                        // Create a Show object
                        Show newShow = new Show();
                        newShow.setDate(currentDate);
                        newShow.setTime(currentTime);
                        newShow.setMovie(randomMovie);
                        newShow.setTheaterHall(smallTheater.get());

                        // Create tickets for each seat
                        List<Ticket> tickets = new ArrayList<>();
                        for (Seat seat : seatsSmallTheater) {
                            Ticket ticket = new Ticket();
                            ticket.setPrice(100);
                            ticket.setSeat(seat);
                            ticket.setShow(newShow);
                            tickets.add(ticket);
                        }
                        newShow.setTickets(new HashSet<>(tickets));

                        // Save the new show to the database
                        showRepository.save(newShow);
                    }

                    // Increment the time by 3 hours for the next show
                    currentTime = currentTime.plusHours(3);
                }

                // Move to the next day
                currentDate = currentDate.plusDays(1);
            }
        }




        // GET BIG THEATER
        Optional<TheaterHall> bigTheater = theaterHallRepository.findById(2);

        // GET SEATS FOR SMALL THEATER
        List<Seat> seatsBigTheater = seatRepository.findAllByTheaterHallId(2);

        // Calculate the date range for the next 3 months
        LocalDate startDate2 = LocalDate.now();
        LocalDate endDate2 = LocalDate.now().plusMonths(1);

        // Define the initial show time as 10:00 AM
        LocalTime initialTime2 = LocalTime.of(10, 0);

        // Iterate through each day in the date range
        LocalDate currentDate2 = startDate2;
        while (currentDate2.isBefore(endDate2)) {
            LocalTime currentTime2 = initialTime2;

            if (smallTheater.isPresent()) {
                TheaterHall theaterHall = smallTheater.get();

                while (currentTime2.isBefore(LocalTime.of(22, 0))) { // Limit show times to 10:00 PM
                    // Ensure two shows can't be scheduled at the same time in the same theater
                    boolean isTimeSlotAvailable2 = true;
                    for (Show existingShow2 : theaterHall.getShows()) {
                        if (existingShow2.getDate().isEqual(currentDate) && existingShow2.getTime().equals(currentTime2)) {
                            isTimeSlotAvailable2 = false;
                            break;
                        }
                    }

                    if (isTimeSlotAvailable2) {
                        // CREATE RANDOM MOVIE
                        List<Movie> movies = movieRepository.findAll();
                        int randomIndexMovie = random.nextInt(1, movies.size());
                        Movie randomMovie = movies.get(randomIndexMovie);

                        // Create a Show object
                        Show newShow = new Show();
                        newShow.setDate(currentDate2);
                        newShow.setTime(currentTime2);
                        newShow.setMovie(randomMovie);
                        newShow.setTheaterHall(bigTheater.get());

                        // Create tickets for each seat
                        List<Ticket> tickets = new ArrayList<>();
                        for (Seat seat : seatsBigTheater) {
                            Ticket ticket = new Ticket();
                            ticket.setPrice(100);
                            ticket.setSeat(seat);
                            ticket.setShow(newShow);
                            tickets.add(ticket);
                        }
                        newShow.setTickets(new HashSet<>(tickets));

                        // Save the new show to the database
                        showRepository.save(newShow);
                    }

                    // Increment the time by 3 hours for the next show
                    currentTime2 = currentTime2.plusHours(3);
                }

                // Move to the next day
                currentDate2 = currentDate2.plusDays(1);
            }
        }

    }
}