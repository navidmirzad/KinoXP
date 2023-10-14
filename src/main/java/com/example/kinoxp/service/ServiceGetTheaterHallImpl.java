package com.example.kinoxp.service;

import com.example.kinoxp.model.Customer;
import com.example.kinoxp.model.Role;
import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.TheaterHall;
import com.example.kinoxp.repositories.CustomerRepository;
import com.example.kinoxp.repositories.SeatRepository;
import com.example.kinoxp.repositories.TheaterHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServiceGetTheaterHallImpl implements ServiceGetTheaterHall {

    @Autowired
    private TheaterHallRepository theaterHallRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void createTheaterHalls() {

        TheaterHall smallTheaterHall = new TheaterHall();
        smallTheaterHall.setName("Small hall");
        theaterHallRepository.save(smallTheaterHall);

        TheaterHall bigTheaterHall = new TheaterHall();
        bigTheaterHall.setName("Big hall");
        theaterHallRepository.save(bigTheaterHall);


        String theaterName = "C1";
        // 20 rows - 12 numbered seats
        for (int rows=1; rows<21; rows++) {
            for (int seat=1; seat<13; seat++) {
                Seat seatObj = new Seat();
                seatObj.setId(theaterName + " R" + rows + " S" + seat);
                seatObj.setSeatNumber(seat);
                seatObj.setRowNumber(rows);
                seatObj.setTheaterHall(smallTheaterHall);
                seatRepository.save(seatObj);
            }
        }


        String theaterName2 = "C2";
        // 25 rows - 16 numbered seats
        for (int rows=1; rows<26; rows++) {
            for (int seat=1; seat<17; seat++) {
                Seat seatObj = new Seat();
                seatObj.setId(theaterName2 + " R" + rows + " S" + seat);
                seatObj.setSeatNumber(seat);
                seatObj.setRowNumber(rows);
                seatObj.setTheaterHall(bigTheaterHall);
                seatRepository.save(seatObj);
            }
        }

        Customer customer1 = new Customer();
        customer1.setFirstName("admin1");
        customer1.setLastName("admin1");
        customer1.setEmail("admin1@admin1.com");
        customer1.setUserName("admin1");
        customer1.setPassword("admin1");
        customer1.setRole(Role.ADMIN);
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("admin2");
        customer2.setLastName("admin2");
        customer2.setEmail("admin2@admin2.com");
        customer2.setUserName("admin2");
        customer2.setPassword("admin2");
        customer2.setRole(Role.ADMIN);
        customerRepository.save(customer2);

    }
}
