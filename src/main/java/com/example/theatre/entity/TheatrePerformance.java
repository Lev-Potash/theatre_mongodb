package com.example.theatre.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@Document
@Table(name = "theatre_performance")
public class TheatrePerformance {
    @Transient
    public static final String SEQUENCE_NAME = "theatre_performance_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long performanceId;
    private Long theatreId;

//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "performance_id")
//    private Performance performance;
//
//
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "theatre_id")
//    private Theatre theatre;

//    @ToString.Exclude
//    @OneToMany(mappedBy = "theatrePerformance", cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<Schedule> schedules = new ArrayList<>();

//    public TheatrePerformance(Performance performance, Theatre theatre) {
//        this.performance = performance;
//        this.theatre = theatre;
//    }


//    public TheatrePerformance(Long id, Performance performance, Theatre theatre) {
//        this.id = id;
//        this.performance = performance;
//        this.theatre = theatre;
//    }


    public TheatrePerformance(Long id, Long performanceId, Long theatreId) {
        this.id = id;
        this.performanceId = performanceId;
        this.theatreId = theatreId;
    }
}
