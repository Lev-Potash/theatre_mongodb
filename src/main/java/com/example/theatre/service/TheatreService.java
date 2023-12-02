package com.example.theatre.service;

import com.example.theatre.entity.Theatre;
import com.example.theatre.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TheatreService {
    Theatre getTheatreById(Long id);
    public List<Theatre> getTheatres();
}
