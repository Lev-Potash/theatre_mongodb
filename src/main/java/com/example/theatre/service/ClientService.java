package com.example.theatre.service;

import com.example.theatre.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {
    List<Client> findClientByTicket(long id);
    Client save(Client client);
}
