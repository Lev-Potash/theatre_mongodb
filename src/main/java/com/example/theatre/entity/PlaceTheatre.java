package com.example.theatre.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
@Table(name = "place_theatre")
public class PlaceTheatre {

    @Transient
    public static final String SEQUENCE_NAME = "place_theatre_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @ToString.Exclude
    @OneToOne(mappedBy = "place_theatre", cascade = CascadeType.ALL)
    private Ticket ticket;

    @ToString.Exclude
    @OneToMany(mappedBy = "placeTheatre", cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<Seating> seatings = new ArrayList<>();

//    public PlaceTheatre(Place place, Theatre theatre) {
//        this.place = place;
//        this.theatre = theatre;
//    }

    public PlaceTheatre(Long id, Place place, Theatre theatre) {
        this.id = id;
        this.place = place;
        this.theatre = theatre;
    }
}
