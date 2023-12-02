package com.example.theatre.repository;

import com.example.theatre.entity.Seating;
import com.example.theatre.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JpaRepository не работает для Mongo.
 * Работают CrudRepository or MongoRepository.
 */
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>/*MongoRepository<Ticket, String>*//*,
        PagingAndSortingRepository<Ticket, String>, JpaSpecificationExecutor<Ticket>*/ {
//    List<Ticket> getTicketsByClient_id(String id);

    @Override
    <S extends Ticket> List<S> saveAll(Iterable<S> entities);
}
