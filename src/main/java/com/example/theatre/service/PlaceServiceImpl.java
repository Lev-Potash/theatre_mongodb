package com.example.theatre.service;

import com.example.theatre.entity.*;
import com.example.theatre.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PlaceServiceImpl implements PlaceService {
    private PlaceRepository placeRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    @Override
    public List<Place> getAllPlacesByTheatrePerformanceAndSchedule(TheatrePerformance theatrePerformance, Schedule schedule) {
//        @Query("select new Place(p.id, p.row, p.place, s.isOccupied) from Place p " +
//            "join  p.placeTheatreList pt " +
//            "join  pt.seatings s " +
//            "join  s.schedule sch " +
//            "join  pt.theatre t " +
//            "where sch.theatrePerformance = :theatrePerformance and s.schedule = :schedule")
//    List<Place> getAllPlacesByTheatrePerformanceAndSchedule(
//            @Param("theatrePerformance") TheatrePerformance theatrePerformance,
//            @Param("schedule") Schedule schedule
//    );
        //db.place.aggregate([{$lookup: {from: "placeTheatre", localField: "_id", foreignField: "place._id", as: "place_theatre"}},
        // {$match: {"place_theatre": {$ne:[]}}},
        // {$lookup: {from: "seating", localField: "place_theatre._id", foreignField: "placeTheatre._id", as: "seatings"}},
        // {$match: {"seatings": {$ne:[]}, "seatings.schedule._id": 24}},
        // {$lookup: {from: "schedule", localField: "seatings.schedule._id", foreignField: "_id", as: "schedules"}},
        // {$match: {"schedules": {$ne: []}, "schedules.theatrePerformance._id": 97}},
        // {$lookup: {from: "theatre", localField: "place_theatre.theatre._id", foreignField: "_id", as: "theatres"}},
        // {$match: {"theatres": {$ne: []}}}])
        log.info("THEATRE PERFORMANCE ID: {}", theatrePerformance.getId());
        log.info("SCHEDULE ID: {}", schedule.getId());
        LookupOperation lookupOperationByPlaceTheatre = LookupOperation.newLookup()
                .from("placeTheatre").localField("_id").foreignField("place._id").as("place_theatre");
        UnwindOperation unwindOperationByPlaceTheatre = Aggregation.unwind("place_theatre", false);
        LookupOperation lookupOperationBySeating = LookupOperation.newLookup()
                .from("seating").localField("place_theatre._id").foreignField("placeTheatre._id").as("seatings");
        UnwindOperation unwindOperationBySeatings = Aggregation.unwind("seatings", false);
        LookupOperation lookupOperationBySchedule = LookupOperation.newLookup()
                .from("schedule").localField("seatings.schedule._id").foreignField("_id").as("schedules");
        UnwindOperation unwindOperationBySchedule= Aggregation.unwind("schedules", false);
        LookupOperation lookupOperationByTheatres = LookupOperation.newLookup()
                .from("theatre").localField("place_theatre.theatre._id").foreignField("_id").as("theatres");
        UnwindOperation unwindOperationByTheatres = Aggregation.unwind("theatres", false);


        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.match(Criteria.where("_id").is(id)),
                lookupOperationByPlaceTheatre,
                unwindOperationByPlaceTheatre,
                lookupOperationBySeating,
                unwindOperationBySeatings,
                Aggregation.match(Criteria.where("seatings.schedule._id").is(schedule.getId())),
                lookupOperationBySchedule,
                unwindOperationBySchedule,
                Aggregation.match(Criteria.where("schedules.theatrePerformance._id").is(theatrePerformance.getId())),
                lookupOperationByTheatres,
                unwindOperationByTheatres,
                Aggregation.project("_id", "row", "place", "seatings.isOccupied" )
//
//                Aggregation.project("place_theatre.place._id", "place_theatre.place.row", "place_theatre.place.place", "place_theatre.place.isOccupied" )
//                Aggregation.sort(Sort.Direction.ASC, "name")
        );
        List<Place> placeList = mongoTemplate.aggregate(aggregation, "place", Place.class).getMappedResults();
        log.info("BY PLACE THEATRE: {}", placeList);

        return placeList; //placeRepository.getAllPlacesByTheatrePerformanceAndSchedule(theatrePerformance, schedule);
    }

    @Override
    public List<Place> getOccupiedPlacesByCurrentDate(String theatre) {
        return null; //placeRepository.getIsOccupiedPlacesByTheatreNameAndCurrentDate(theatre);
    }

    @Override
    public List<Place> getOccupiedPlacesByTheatreNameAndPerformanceDate(String theatre, Date performanceDate) {
        return null;
//                placeRepository.getIsOccupiedPlacesByPerformanceDateAndTheatreName(performanceDate, theatre);
    }

    @Override
    public List<Place> getPlaceByClientNameAndSurname(String name, String surname) {
        return null;//placeRepository.findPlaceByClientNameAndSurname(name, surname);
    }
}
