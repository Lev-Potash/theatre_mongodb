package com.example.theatre.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Document
public class Ticket {
    @Transient
    public static final String SEQUENCE_NAME = "tickets_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    private long client_id;
//    private String schedule_id;
//    private String place_theatre_id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_theatre_id", referencedColumnName = "id")
    private PlaceTheatre place_theatre;

    @NotNull
    private int cost;

    public Ticket(long id, Client client, Schedule schedule, PlaceTheatre place_theatre, int cost) {
        this.id = id;
        this.client = client;
        this.schedule = schedule;
        this.place_theatre = place_theatre;
        this.cost = cost;
    }

    //    public Ticket(Client client, Schedule schedule, PlaceTheatre place_theatre, int cost) {
//        this.client = client;
//        this.schedule = schedule;
//        this.place_theatre = place_theatre;
//        this.cost = cost;
//    }


//    public Ticket(long id, long client_id, String schedule_id, String place_theatre_id, int cost) {
//        this.id = id;
//        this.client_id = client_id;
//        this.schedule_id = schedule_id;
//        this.place_theatre_id = place_theatre_id;
//        this.cost = cost;
//    }

//    public Ticket(long client_id, String schedule_id, String place_theatre_id, int cost) {
//        this.client_id = client_id;
//        this.schedule_id = schedule_id;
//        this.place_theatre_id = place_theatre_id;
//        this.cost = cost;
//    }
}
