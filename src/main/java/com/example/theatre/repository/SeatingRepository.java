package com.example.theatre.repository;

import com.example.theatre.entity.Place;
import com.example.theatre.entity.PlaceTheatre;
import com.example.theatre.entity.Schedule;
import com.example.theatre.entity.Seating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatingRepository extends MongoRepository<Seating, Long>/*,
        PagingAndSortingRepository<Seating, String>, JpaSpecificationExecutor<Seating>*/ {

    Optional<Seating> findSeatingByPlaceTheatreAndSchedule(PlaceTheatre placeTheatre, Schedule schedule);

    @Override
    <S extends Seating> List<S> saveAll(Iterable<S> entities);
}
