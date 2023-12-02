package com.example.theatre.service;

import com.example.theatre.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    Ticket save(Ticket ticket);
    List<Ticket> ticketsByClientId(String id);
}
