package com.example.theatre.service;

import com.example.theatre.entity.Client;
import com.example.theatre.entity.Ticket;
import com.example.theatre.repository.ClientRepository;
import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    MongoTemplate mongoTemplate;
    MongoOperations mongoOperations;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, MongoTemplate mongoTemplate) {
        this.clientRepository = clientRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    public void setMongoOperations(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<Client> findClientByTicket(long id) {
//        @Query("select c from Client c " +
//            "join fetch Ticket t " +
//            "where t.id = :id")
        log.info("ID: {}", id);
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("client").localField("client._id").foreignField("_id").as("clients_tickets");
//        LookupOperation lookupOperation = Aggregation.lookup("client", "client._id","_id", "tickets");

        LookupOperation lookupOperation2 = LookupOperation.newLookup()
                .from("ticket").localField("_id").foreignField("client_id").as("clients");
        UnwindOperation unwindOperation = Aggregation.unwind("clients_tickets", false);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("_id").is(id)),
                lookupOperation,
                unwindOperation,
                Aggregation.project("clients_tickets._id", "clients_tickets.name", "clients_tickets.surname", "clients_tickets.email" )
//                Aggregation.sort(Sort.Direction.ASC, "name")
        );
        log.info("LOOKUP OPERATION: {} {}", lookupOperation.getOperator(), lookupOperation.getFields());
      log.info("AGGREGATION: {}",aggregation.toString());
      List<Client> clientTicketsList = mongoTemplate.aggregate(aggregation, "ticket", Client.class).getMappedResults();
      log.info("CLIENT BY TICKET: {}", clientTicketsList);
      //return null;//clientRepository.findClientByTicketId(id);
        return clientTicketsList;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }


}
