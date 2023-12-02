package com.example.theatre.service;

import com.example.theatre.entity.Theatre;
import com.example.theatre.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreServiceImpl implements TheatreService {

    TheatreRepository theatreRepository;

    @Autowired
    public TheatreServiceImpl(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @Override
    public Theatre getTheatreById(Long id) {
        if (theatreRepository.findById(id).isPresent()) {
            return theatreRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Theatre> getTheatres() {
        return theatreRepository.findAll();
    }
}
