package com.example.theatre.service;

import com.example.theatre.entity.Place;
import com.example.theatre.entity.PlaceTheatre;
import com.example.theatre.entity.Theatre;
import org.springframework.stereotype.Service;

@Service
public interface PlaceTheatreService {
    PlaceTheatre getPlaceTheatreByPlaceAndTheatre(Place place, Theatre theatre);
}
