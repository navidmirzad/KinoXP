package com.example.kinoxp.service;

import com.example.kinoxp.dto.TicketRequestDTO;
import com.example.kinoxp.model.Ticket;

public interface ServiceIssueTicket {
    Ticket assignCustomerToTicket(TicketRequestDTO request);
}

