package com.example.theatre.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Performance {

    @Transient
    public static final String SEQUENCE_NAME = "performances_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @NotBlank(message = "Поле Спектакль должно быть выбрано")
    @Size(min=1, message="Поле Спектакль должно быть выбрано")
    @Column(name = "performance_name")
    private String performanceName;

    @ToString.Exclude
    @OneToMany(mappedBy = "performance", cascade = CascadeType.ALL,
                orphanRemoval = true)
    List<TheatrePerformance> theatre_performanceList = new ArrayList<>();

//    public Performance(String performanceName) {
//        this.performanceName = performanceName;
//    }


    public Performance(Long id, String performanceName) {
        this.id = id;
        this.performanceName = performanceName;
    }
}
