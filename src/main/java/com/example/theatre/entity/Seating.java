package com.example.theatre.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Document
public class Seating {

    @Transient
    public static final String SEQUENCE_NAME = "seatings_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "place_theatre_id")
    private PlaceTheatre placeTheatre;

    @NotNull
    @Column(name = "isoccupied")
    private boolean isOccupied;

//    public Seating(Schedule schedule, PlaceTheatre placeTheatre, boolean isOccupied) {
//        this.schedule = schedule;
//        this.placeTheatre = placeTheatre;
//        this.isOccupied = isOccupied;
//    }


    public Seating(Long id, Schedule schedule, PlaceTheatre placeTheatre, boolean isOccupied) {
        this.id = id;
        this.schedule = schedule;
        this.placeTheatre = placeTheatre;
        this.isOccupied = isOccupied;
    }
}
