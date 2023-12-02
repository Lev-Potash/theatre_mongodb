package com.example.theatre.repository;

import com.example.theatre.entity.Client;
import com.example.theatre.entity.DatabaseSequence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseSequenceRepository extends CrudRepository<DatabaseSequence,Long> {
}
