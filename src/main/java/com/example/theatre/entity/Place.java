package com.example.theatre.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Place {

    @Transient
    public static final String SEQUENCE_NAME = "places_sequence";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int row;
    @NotNull
    private int place;

    @Transient
    private boolean isOccupied;


    @ToString.Exclude
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL ,
            orphanRemoval = true)
    public List<PlaceTheatre> placeTheatreList = new ArrayList<>();

//    public Place(Long id) {
//        this.id = id;
//    }

//    public Place(int row, int place) {
//        this.row = row;
//        this.place = place;
//    }

    public Place(Long id, int row, int place) {
        this.id = id;
        this.row = row;
        this.place = place;
    }

    public Place(Long id, int row, int place, boolean isOccupied) {
        this.id = id;
        this.row = row;
        this.place = place;
        this.isOccupied = isOccupied;
    }
}
