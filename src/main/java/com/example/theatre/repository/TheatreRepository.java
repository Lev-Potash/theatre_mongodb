package com.example.theatre.repository;

import com.example.theatre.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends MongoRepository<Theatre, Long>/*, PagingAndSortingRepository<Theatre, String>*/ {
    @Override
    Optional<Theatre> findById(Long s);

    @Override
    List<Theatre> findAll();

    @Override
    <S extends Theatre> List<S> saveAll(Iterable<S> entities);
}
