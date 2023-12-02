package com.example.theatre.repository;

import com.example.theatre.entity.Place;
import com.example.theatre.entity.PlaceTheatre;
import com.example.theatre.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceTheatreRepository extends MongoRepository<PlaceTheatre, String>/*,
        PagingAndSortingRepository<PlaceTheatre, String>, JpaSpecificationExecutor<PlaceTheatre>*/ {

    Optional<PlaceTheatre> findPlaceTheatreByPlaceAndTheatre(Place place, Theatre theatre);

    @Override
    <S extends PlaceTheatre> List<S> saveAll(Iterable<S> entities);
}
