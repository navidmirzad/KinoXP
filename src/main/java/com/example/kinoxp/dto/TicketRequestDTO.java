package com.example.kinoxp.dto;

import com.example.kinoxp.model.Ticket;

public class TicketRequestDTO {

    private int ticketId;
    private String seatId;
    private String userName;

    public TicketRequestDTO() {}

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
