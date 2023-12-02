package com.example.theatre.controller;

import com.example.theatre.entity.*;
import com.example.theatre.service.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Data
@Controller
@SessionAttributes({"ticket", "theatreModel", "theatrePerformanceModel"})
@RequestMapping("/registration")
public class RegistrationController {

    private TheatreServiceImpl theatreService;
    private PerformanceService performanceService;

    private PlaceService placeService;

    private ClientService clientService;

    private TheatrePerformanceService theatrePerformanceService;

    private ScheduleService scheduleService;

    private PlaceTheatreService placeTheatreService;

    private SeatingService seatingService;

    private TicketService ticketService;

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public void setSequenceGeneratorService(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setPlaceTheatreService(PlaceTheatreService placeTheatreService) {
        this.placeTheatreService = placeTheatreService;
    }

    @Autowired
    public void setSeatingService(SeatingService seatingService) {
        this.seatingService = seatingService;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Autowired
    public void setTheatrePerformanceService(TheatrePerformanceService theatrePerformanceService) {
        this.theatrePerformanceService = theatrePerformanceService;
    }

    @Autowired
    public void setPlaceService(PlaceService placeService) {
        this.placeService = placeService;
    }

    @Autowired
    public void setTheatreService(TheatreServiceImpl theatreService) {
        this.theatreService = theatreService;
    }

    @Autowired
    public void setPerformanceService(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(produces = "text/html")
    public String showRegistrationForm(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "registration-client";
    }

    @PostMapping
    public String processRegistration(@Valid Client client, Errors errors,
                                      @ModelAttribute Ticket ticket) {
        if (errors.hasErrors()) {
            log.error("has errors");
            return "registration-client";
        }
        log.info("Registration client: {}", client);
        client.setId(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME));
        clientService.save(client);
        ticket.setClient(client);
        return "redirect:/registration/theatre-performance";
    }

    @ModelAttribute("theatres")
    public List<Theatre> theatres()
    {
        return theatreService.getTheatres();
    }

    @ModelAttribute("performances")
    public List<Performance> performances() {
        return performanceService.getPerformanceList();
    }

    @GetMapping(value = "/theatre-performance")
    public String showTheatrePerformanceForm(Model model, Client client,
                                             @ModelAttribute Theatre theatre,
                                             @ModelAttribute Ticket ticket) {

        if (ticket.getClient() == null) {
            log.info("session is complete");
            return "redirect:/registration";
        }


        TheatrePerformance theatre_performance = new TheatrePerformance();
        Client client_ = ticket.getClient();
        log.info("---Client is:" + client_);
        log.info("---theatres is:" + theatreService.getTheatres());
        model.addAttribute("client", client_);
//        model.addAttribute("theatres", theatreService.getTheatres());
//        model.addAttribute("performances", performanceService.getPerformanceList());
        model.addAttribute("theatrePerformance", theatre_performance);
        model.addAttribute("theatre", new Theatre());
        model.addAttribute("performance", new Performance());


        return "theatre_performance";
    }

    @PostMapping(value = "theatre-performance")
    public String processTheatrePerformanceRegistration(@Valid @ModelAttribute("theatrePerformance") TheatrePerformance theatre_performance,
                                                        Errors errors,
                                                        @ModelAttribute("theatrePerformanceModel") TheatrePerformance theatrePerformanceModel,
                                                        @ModelAttribute("theatre") Theatre theatre,
                                                        Performance performance,
                                                        @ModelAttribute("theatreModel") Theatre theatreModel,
                                                        @ModelAttribute Ticket ticket) {
        log.info("THEATRE: {}", theatre);
        log.info("Performance: {}", performance);
        log.info("THEATRE PERFORMANCE: {}", theatre_performance);

        if (errors.hasErrors()) {
            log.error("Theatre Performance form has errors");
            return "theatre_performance";
        }

        log.info("Registration theatre: {}", theatre);
//        theatreModel.setId(theatre.getId());
//        theatreModel.setTheatreName(theatre.getTheatreName());
        theatreModel.setId(theatre_performance.getTheatreId());

//        theatreModel.setTheatreName(theatre.getTheatreName());
        theatreModel.setTheatreName(theatreService.getTheatreById(theatre_performance.getTheatreId()).getTheatreName());
        log.info("@ModelAttribute theatre_: {}", theatreModel);
//        log.info("Registration performance: {}", performance);
        log.info("Registration performance ID: {}", theatre_performance.getPerformanceId());
//        TheatrePerformance theatrePerformance = theatrePerformanceService.findTheatrePerformance(theatre, performance);
        TheatrePerformance theatrePerformance = theatrePerformanceService.findTheatrePerformance(theatre_performance.getTheatreId(),
                theatre_performance.getPerformanceId());
        log.info("registration TheatrePerformance: {}", theatrePerformance);
        theatrePerformanceModel.setId(theatrePerformance.getId());
//        theatrePerformanceModel.setTheatre(theatrePerformance.getTheatre());
//        theatrePerformanceModel.setPerformance(theatrePerformance.getPerformance());
        theatrePerformanceModel.setTheatreId(theatrePerformance.getTheatreId());
        theatrePerformanceModel.setPerformanceId(theatrePerformance.getPerformanceId());
        log.info("TheatrePerformanceModel: {}", theatrePerformanceModel);



        return "redirect:/registration/performance-date";
    }

//    @ModelAttribute("performanceDates")
//    public List<Date> performanceDates(
//            @ModelAttribute("theatrePerformanceModel") TheatrePerformance theatrePerformance) throws ParseException {
//        return scheduleService.getPerformanceDatesByTheatrePerformance(theatrePerformance);
//    }

    @ModelAttribute("performanceDates")
    public List<String> performanceDates(
            @ModelAttribute("theatrePerformanceModel") TheatrePerformance theatrePerformance) throws ParseException {
        return scheduleService.getPerformanceDatesByTheatrePerformance(theatrePerformance);
    }

    @GetMapping(value = "/performance-date")
    public String showPerformanceDateForm(Model model, @ModelAttribute("theatreModel") Theatre theatre,
                                          @ModelAttribute("theatrePerformanceModel") TheatrePerformance theatrePerformance,
                                          @ModelAttribute Ticket ticket) {


        log.info("---");
        log.info("--- theatrePerformance: {}", theatrePerformance);
        if (theatrePerformance.getId() == null) {
            log.info("session is complete");
            return "redirect:/registration";
        }
        log.info("--- performanceDates: {}", scheduleService.getPerformanceDatesByTheatrePerformance(theatrePerformance));

//        model.addAttribute("performanceDates",
//                scheduleService.getPerformanceDatesByTheatrePerformance(theatrePerformance));
//        model.addAttribute("schedule", new Schedule());
        model.addAttribute("dateStringObj", new DateStringObj());

        return "performance-date";
    }

    @PostMapping(value = "/performance-date")
    public String processPerformanceDateRegistration(/*@Valid Schedule receivedWithPerformanceDateSchedule,*/
                                                    @Valid DateStringObj dateStringObj,
                                                     Errors errors,
                                                     @ModelAttribute("theatrePerformanceModel") TheatrePerformance theatrePerformanceModel,
                                                     @ModelAttribute("theatreModel") Theatre theatre,
                                                     @ModelAttribute Ticket ticket) throws ParseException {
//        log.info("Schedule: {}", receivedWithPerformanceDateSchedule);
//        log.info("Schedule performance date: {}", receivedWithPerformanceDateSchedule.getPerformanceDate());

        log.info("DateStringObj: {}", dateStringObj);

        if (errors.hasErrors()) {
            log.error("performanceDate has error");
            return "performance-date";
        }

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStringObj.getDate());
        Schedule schedule = scheduleService.getScheduleByTheatrePerformanceAndPerformanceDate(theatrePerformanceModel,
               date);


//        Schedule schedule = scheduleService.getScheduleByTheatrePerformanceAndPerformanceDate(theatrePerformanceModel,
//                receivedWithPerformanceDateSchedule.getPerformanceDate());

        log.info("registration Schedule: {}", schedule);

        ticket.setSchedule(schedule);
        log.info("Ticket with Schedule: {}", ticket);

        return "redirect:/registration/places";
    }


    @GetMapping(value = "/places")
    public String showPlacesForm(Model model, @ModelAttribute("theatreModel") Theatre theatre,
                                 @ModelAttribute("theatrePerformanceModel") TheatrePerformance theatrePerformanceModel,
                                 @ModelAttribute Ticket ticket) {

        log.info("---");
        log.info("--- theatre: {}", theatre);
        if (theatre.getId() == null) {
            log.info("session is complete");
            return "redirect:/registration";
        }
            log.info("---");
            log.info("--- theatre: {}", theatre);
            log.info("--- Sorted places: " + getAndSortedPlaceById(theatrePerformanceModel,
                    ticket.getSchedule()));

            model.addAttribute("rows", createRowsList(theatrePerformanceModel,
                    ticket.getSchedule()));
            model.addAttribute("allPlaces", getAndSortedPlaceById(theatrePerformanceModel,
                    ticket.getSchedule()));
//        model.addAttribute("occupiedPlaces", placeService.getOccupiedPlacesByTheatreNameAndPerformanceDate("Вестник", new Date(new GregorianCalendar(2023, 5, 10).getTime().getTime()))); /*String.valueOf(new GregorianCalendar(2023,5,11))*/
            model.addAttribute("simplePlaceObj", new SimplePlaceObj());

        return "places";
    }

    private List<Place> getAndSortedPlaceById(TheatrePerformance theatrePerformance, Schedule schedule) {
        List<Place> result = placeService.getAllPlacesByTheatrePerformanceAndSchedule(theatrePerformance, schedule)
                .stream().sorted((id1, id2) ->
                        id1.getId().compareTo(id2.getId())
                ).collect(Collectors.toList());
        return result;
    }

    @PostMapping(value = "/places")
    public String processPlacesRegistration(SimplePlaceObj simplePlaceObj,
                                            @ModelAttribute("theatreModel") Theatre theatre,
                                            @ModelAttribute Ticket ticket,
                                            SessionStatus sessionStatus) {
        if (!(simplePlaceObj.getPlaceList() == null)) {
            log.info("place ids: {}", simplePlaceObj);
            for (Place place :
                    simplePlaceObj.getPlaceList()) {
                log.info("Place: {}", place);
                log.info("Theatre: {}", theatre);
                PlaceTheatre placeTheatre = placeTheatreService.getPlaceTheatreByPlaceAndTheatre(place, theatre);
                log.info("PlaceTheatre: {}", placeTheatre);
                Schedule schedule = ticket.getSchedule();
                log.info("Schedule by ticket: {}", schedule);
                Seating seating = seatingService.getSeatingByPlaceTheatreAndSchedule(placeTheatre, schedule);
                if (!(seating == null)) {
                    Ticket ticketForSaving = new Ticket();
                    log.info("Seating: {}", seating);
                    seating.setOccupied(true);
                    log.info("Seating occupied set true: {}", seating);
                    seatingService.saveSeating(seating);
                    ticketForSaving.setId(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME));
                    ticketForSaving.setSchedule(ticket.getSchedule());
                    ticketForSaving.setClient(ticket.getClient());
                    ticketForSaving.setPlace_theatre(seating.getPlaceTheatre());
                    ticketForSaving.setCost(350);
                    log.info("Ticket for saving: {}", ticketForSaving);
                    Ticket savedTicket = ticketService.save(ticketForSaving);
                    log.info("Saved ticket: {}", savedTicket);
                    sessionStatus.setComplete(); // сеанс будет очищен
                } else {
                    log.error("Seating is not found in database. " +
                            "Add information about Schedule and PlaceTheatre.");
                    break;
                }
            }

        } else {
            log.error("Don't choose places by place list.");
        }

        return "redirect:/registration";
    }


    private List<Integer> createRowsList(TheatrePerformance theatrePerformance, Schedule schedule) {
        Set<Integer> rows = new HashSet<>();

        for (Place placeRow :
                placeService.getAllPlacesByTheatrePerformanceAndSchedule(theatrePerformance, schedule)
        ) {
            rows.add(placeRow.getRow());
        }

        log.info("rows: {}", rows.stream().sorted().collect(Collectors.toList()));

        return rows.stream().sorted().collect(Collectors.toList());
    }

    @ModelAttribute("ticket")
    public Ticket ticket() {
        return new Ticket();
    }

    @ModelAttribute("theatreModel")
    public Theatre theatre() {
        return new Theatre();
    }

    //    @ModelAttribute("performanceDateModel")
//    public Date performanceDate() {
//        return new Date();
//    }
//
    @ModelAttribute("theatrePerformanceModel")
    public TheatrePerformance theatrePerformance() {
        return new TheatrePerformance();
    }

}
