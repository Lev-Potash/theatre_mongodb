package com.example.theatre.repository;

import com.example.theatre.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * JpaRepository не работает для Mongo.
 * Работают CrudRepository or MongoRepository.
 */
//select c from boot.Client c
//        join boot.Ticket t on t.client_id=c.id
//        where t.id = 1;
@Repository
public interface ClientRepository extends CrudRepository<Client,Long>/*MongoRepository<Client, String>*//*,
        PagingAndSortingRepository<Client, String>*/ {
    /**
     *
     * @param id
     * @return
     */
//    @Query("select c from Client c " +
//            "join fetch Ticket t " +
//            "where t.id = :id")
//    Optional<Client> findClientByTicketId(String id);

    @Override
    <S extends Client> List<S> saveAll(Iterable<S> entities);
}
