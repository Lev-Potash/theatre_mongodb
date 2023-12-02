package com.example.theatre.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Theatre {
    @Transient
    public static final String SEQUENCE_NAME = "theatres_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @NotBlank(message = "Поле Театр должно быть выбрано")
    @Size(min=1, message="Поле Театр должно быть выбрано")
    @Column(name = "theatre_name")
    private String theatreName;

    @ToString.Exclude
    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL ,
            orphanRemoval = true)
    private List<TheatrePerformance> theatre_performanceList = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PlaceTheatre> placeTheatres = new ArrayList<>();

//    public Theatre(String theatreName) {
//        this.theatreName = theatreName;
//    }


    public Theatre(Long id, String theatreName) {
        this.id = id;
        this.theatreName = theatreName;
    }
}
