package com.example.theatre.service;

import com.example.theatre.entity.Performance;
import com.example.theatre.entity.Theatre;
import com.example.theatre.entity.TheatrePerformance;
import com.example.theatre.repository.TheatrePerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheatrePerformanceServiceImpl implements TheatrePerformanceService {
    TheatrePerformanceRepository theatrePerformanceRepository;

    @Autowired
    public TheatrePerformanceServiceImpl(TheatrePerformanceRepository theatrePerformanceRepository) {
        this.theatrePerformanceRepository = theatrePerformanceRepository;
    }

    @Override
    public TheatrePerformance findTheatrePerformance(Long theatre_id, Long performance_id) {
        if (theatrePerformanceRepository.findTheatrePerformanceByTheatreIdAndPerformanceId(theatre_id, performance_id).isPresent()) {
            return theatrePerformanceRepository.findTheatrePerformanceByTheatreIdAndPerformanceId(theatre_id, performance_id).get();
        }
        return null;
    }
}
