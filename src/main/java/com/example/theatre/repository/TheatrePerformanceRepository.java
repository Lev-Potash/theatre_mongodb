package com.example.theatre.repository;

import com.example.theatre.entity.Performance;
import com.example.theatre.entity.Theatre;
import com.example.theatre.entity.TheatrePerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatrePerformanceRepository extends MongoRepository<TheatrePerformance, String>/*,
        PagingAndSortingRepository<TheatrePerformance, String>, JpaSpecificationExecutor<TheatrePerformance>*/ {

//    Optional<TheatrePerformance> findTheatrePerformanceByTheatreAndPerformance(Theatre theatre, Performance performance);
Optional<TheatrePerformance> findTheatrePerformanceByTheatreIdAndPerformanceId(Long theatre_id, Long performance_id);
    @Override
    <S extends TheatrePerformance> List<S> saveAll(Iterable<S> entities);
}
