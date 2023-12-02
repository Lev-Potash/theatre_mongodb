package com.example.theatre.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Schedule {
    @Transient
    public static final String SEQUENCE_NAME = "schedules_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theatre_performance_id")
    private TheatrePerformance theatrePerformance;

    @NotNull(message = "Дата должна быть выбрана")
    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "performance_date")
    private Date performanceDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Seating> seatings = new ArrayList<>();

    public Schedule(Long id, TheatrePerformance theatrePerformance, Date performanceDate) {
        this.id = id;
        this.theatrePerformance = theatrePerformance;
        this.performanceDate = performanceDate;
    }
}
