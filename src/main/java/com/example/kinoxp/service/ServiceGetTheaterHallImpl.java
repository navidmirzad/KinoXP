package com.example.kinoxp.service;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.TheaterHall;
import com.example.kinoxp.repositories.SeatRepository;
import com.example.kinoxp.repositories.TheaterHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceGetTheaterHallImpl implements ServiceGetTheaterHall {

    @Autowired
    private TheaterHallRepository theaterHallRepository;

    @Autowired
    private SeatRepository seatRepository;

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
                seatObj.setName(theaterName + " R" + rows + " S" + seat);
                seatObj.setTheaterHall(smallTheaterHall);
                seatRepository.save(seatObj);
            }
        }


        String theaterName2 = "C2";
        // 25 rows - 16 numbered seats
        for (int rows=1; rows<26; rows++) {
            for (int seat=1; seat<17; seat++) {
                Seat seatObj = new Seat();
                seatObj.setName(theaterName2 + " R" + rows + " S" + seat);
                seatObj.setTheaterHall(bigTheaterHall);
                seatRepository.save(seatObj);
            }
        }


    }
}
