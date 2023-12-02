package com.example.theatre.repository;

import com.example.theatre.entity.Performance;
import com.example.theatre.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends MongoRepository<Performance, String>/*,
        PagingAndSortingRepository<Performance, String>*/ {
    List<Performance> findAll();

    @Override
    <S extends Performance> List<S> saveAll(Iterable<S> entities);
}
