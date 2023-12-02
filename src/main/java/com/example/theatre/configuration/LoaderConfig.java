package com.example.theatre.configuration;

import com.example.theatre.entity.*;
import com.example.theatre.repository.*;
import com.example.theatre.service.ClientService;
import com.example.theatre.service.PlaceService;
import com.example.theatre.service.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
@Configuration
public class LoaderConfig {

    @Autowired
    ClientService clientService;

    @Autowired
    PlaceService placeService;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Bean
    public ApplicationRunner dataLoader(TheatreRepository theatreRepository,
                                        DatabaseSequenceRepository databaseSequenceRepository,
                                        PerformanceRepository performanceRepository, PlaceRepository placeRepository,
                                        ClientRepository clientRepository, SeatingRepository seatingRepository,
                                        PlaceTheatreRepository placeTheatreRepository,
                                        ScheduleRepository scheduleRepository,
                                        TheatrePerformanceRepository theatrePerformanceRepository,
                                        TicketRepository ticketRepository) {
        return args -> {
            databaseSequenceRepository.deleteAll();
            theatreRepository.deleteAll();
            performanceRepository.deleteAll();
            placeRepository.deleteAll();
            clientRepository.deleteAll();
            seatingRepository.deleteAll();
            placeTheatreRepository.deleteAll();
            scheduleRepository.deleteAll();
            theatrePerformanceRepository.deleteAll();
            ticketRepository.deleteAll();

//            Client client = new Client();
//            client.setId(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME));
//            client.setName("bob");
//            client.setSurname("fly");
//            client.setEmail("john.doe@example.com");
//            clientRepository.save(client);
//            log.info("CLIENT : {}", client);
//            log.info("CLIENT REPOSITORY: {}", clientRepository.findAll());
//
//            Ticket ticket = new Ticket();
//            ticket.setId(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME));
//            ticket.setClient_id(client.getId());
//            ticket.setSchedule_id("rgrgrg34455432");
//            ticket.setPlace_theatre_id("64kreog554343r");
//            ticketRepository.save(ticket);
//            log.info("TICKET : {}", ticket);
//            log.info("TICKET REPOSITORY: {}", ticketRepository.findAll());





            List<Client> clients = new ArrayList<>();
            clients.add(clientRepository.save(new Client(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME),"Alex", "Frolov", "lex@ru.ru")));
            clients.add(clientRepository.save(new Client(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME),"Rex", "Stepanov", "rex@ru.ru")));
            clients.add(clientRepository.save(new Client(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME),"Max", "Gorbunov", "gorb@ru.ru")));
            clients.add(clientRepository.save(new Client(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME),"Oleg", "Gorin", "ol@ru.ru")));
            log.info("Loader Class.");
            log.info("Loader Clients: {}", clientRepository.findAll());
            List<Theatre> theatres = new ArrayList<>();
            theatres.add(theatreRepository.save(new Theatre(sequenceGeneratorService.generateSequence(Theatre.SEQUENCE_NAME),
                    "Вестник")));
            theatres.add(theatreRepository.save(new Theatre(sequenceGeneratorService.generateSequence(Theatre.SEQUENCE_NAME),
                    "Сатира")));
            theatres.add(theatreRepository.save(new Theatre(sequenceGeneratorService.generateSequence(Theatre.SEQUENCE_NAME),
                    "Малый театр")));
            theatres.add(theatreRepository.save(new Theatre(sequenceGeneratorService.generateSequence(Theatre.SEQUENCE_NAME),
                    "Большой театр")));
            log.info("Loader Theatres: {}", theatreRepository.findAll());
            List<Performance> performances = new ArrayList<>();
            performances.add(performanceRepository.save(new Performance(sequenceGeneratorService.generateSequence(Performance.SEQUENCE_NAME),
                    "Горе от ума")));
            performances.add(performanceRepository.save(new Performance(sequenceGeneratorService.generateSequence(Performance.SEQUENCE_NAME),
                    "Гамлет")));
            performances.add(performanceRepository.save(new Performance(sequenceGeneratorService.generateSequence(Performance.SEQUENCE_NAME),
                    "Вишневый сад")));
            performances.add(performanceRepository.save(new Performance(sequenceGeneratorService.generateSequence(Performance.SEQUENCE_NAME),
                    "Не все коту масленница")));
            performances.add(performanceRepository.save(new Performance(sequenceGeneratorService.generateSequence(Performance.SEQUENCE_NAME),
                    "Ромео и Джулиета")));
            performances.add(performanceRepository.save(new Performance(sequenceGeneratorService.generateSequence(Performance.SEQUENCE_NAME),
                    "Большой перполох")));
            log.info("Loader Performances: {}", performanceRepository.findAll());
            List<Place> placeList = new ArrayList<>();
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),1, 1)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),1, 2)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),1, 3)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),1, 4)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),1, 5)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),2, 1)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),2, 2)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),2, 3)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),2, 4)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),2, 5)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),3, 1)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),3, 2)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),3, 3)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),3, 4)));
            placeList.add(placeRepository.save(new Place(sequenceGeneratorService.generateSequence(Place.SEQUENCE_NAME),3, 5)));
            log.info("Loader Performances: {}", placeRepository.findAll());
            List<PlaceTheatre> placeTheatres = new ArrayList<>();
            for (Theatre theatre : theatres) {
                for (Place place : placeList) {
                    placeTheatres.add(placeTheatreRepository.save(new PlaceTheatre(
                            sequenceGeneratorService.generateSequence(PlaceTheatre.SEQUENCE_NAME),
                            place, theatre
                    )));
                }
            }
            log.info("Loader PlaceTheatres: {}", placeTheatreRepository.findAll());
            List<TheatrePerformance> theatrePerformances = new ArrayList<>();
            for (Theatre theatre : theatres) {
                for (Performance performance : performances) {
                    theatrePerformances.add(
                            theatrePerformanceRepository.save(new TheatrePerformance(
                                    sequenceGeneratorService.generateSequence(TheatrePerformance.SEQUENCE_NAME),
                                    performance.getId(), theatre.getId()
                            ))
                    );
                }
            }
            log.info("Loader TheatrePerformances: {}", theatrePerformanceRepository.findAll());
            List<Schedule> schedules = new ArrayList<>();
            schedules.add(scheduleRepository.save(new Schedule(sequenceGeneratorService.generateSequence(Schedule.SEQUENCE_NAME),
                    theatrePerformances.get(0), new Date(new GregorianCalendar(2022, 6, 1).getTime().getTime()))));
            schedules.add(scheduleRepository.save(new Schedule(sequenceGeneratorService.generateSequence(Schedule.SEQUENCE_NAME),
                    theatrePerformances.get(1), new Date(new GregorianCalendar(2022, 6, 3).getTime().getTime()))));
            schedules.add(scheduleRepository.save(new Schedule(sequenceGeneratorService.generateSequence(Schedule.SEQUENCE_NAME),
                    theatrePerformances.get(1), new Date(new GregorianCalendar(2023, 5, 10).getTime().getTime()))));
            schedules.add(scheduleRepository.save(new Schedule(sequenceGeneratorService.generateSequence(Schedule.SEQUENCE_NAME),
                    theatrePerformances.get(0), new Date(new GregorianCalendar(2023, 5, 10).getTime().getTime()))));
            schedules.add(scheduleRepository.save(new Schedule(sequenceGeneratorService.generateSequence(Schedule.SEQUENCE_NAME),
                    theatrePerformances.get(1), new Date(new GregorianCalendar(2023, 5, 29).getTime().getTime()))));
            log.info("Loader Schedules: {}", scheduleRepository.findAll());
            List<Seating> seatings = new ArrayList<>();
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(0), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(1), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(2), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(3), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(4), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(5), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(6), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(7), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(8), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(9), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(10), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(11), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(12), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(13), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(0), placeTheatres.get(14), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(0), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(1), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(2), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(3), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(4), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(5), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(6), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(7), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(8), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(9), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(10), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(11), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(12), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(13), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(1), placeTheatres.get(14), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(0), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(1), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(2), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(3), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(4), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(5), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(6), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(7), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(8), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(9), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(10), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(11), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(12), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(13), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(2), placeTheatres.get(14), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(0), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(1), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(2), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(3), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(4), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(5), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(6), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(7), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(8), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(9), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(10), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(11), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(12), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(13), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(3), placeTheatres.get(14), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(0), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(1), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(2), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(3), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(4), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(5), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(6), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(7), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(8), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(9), true)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(10), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(11), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(12), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(13), false)));
            seatings.add(seatingRepository.save(new Seating(sequenceGeneratorService.generateSequence(Seating.SEQUENCE_NAME), schedules.get(4), placeTheatres.get(14), false)));
            log.info("Loader Seatings: {}", seatingRepository.findAll());
            List<Ticket> tickets = new ArrayList<>();
            //
//            tickets.add(
//                    ticketRepository.save(new Ticket(clients.get(0), schedules.get(0), placeTheatres.get(0), 350))
//            );
//            tickets.add(
//                    ticketRepository.save(new Ticket(clients.get(1), schedules.get(1), placeTheatres.get(1), 350))
//            );
//            tickets.add(
//                    ticketRepository.save(new Ticket(clients.get(2), schedules.get(2), placeTheatres.get(2), 350))
//            );
//            tickets.add(
//                    ticketRepository.save(new Ticket(clients.get(3), schedules.get(2), placeTheatres.get(3), 350))
//            );

            //
//            tickets.add(
//                    ticketRepository.save(new Ticket(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME),
//                            clients.get(0).getId(), schedules.get(0).getId(), placeTheatres.get(0).getId(), 350))
//            );
//            tickets.add(
//                    ticketRepository.save(new Ticket(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME),
//                            clients.get(1).getId(), schedules.get(1).getId(), placeTheatres.get(1).getId(), 350))
//            );
//            tickets.add(
//                    ticketRepository.save(new Ticket(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME),
//                            clients.get(2).getId(), schedules.get(2).getId(), placeTheatres.get(2).getId(), 350))
//            );
//            tickets.add(
//                    ticketRepository.save(new Ticket(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME),
//                            clients.get(3).getId(), schedules.get(2).getId(), placeTheatres.get(3).getId(), 350))
//            );

            tickets.add(
                    ticketRepository.save(new Ticket(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME),
                            clients.get(0), schedules.get(0), placeTheatres.get(0), 350))
            );
            tickets.add(
                    ticketRepository.save(new Ticket(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME),
                            clients.get(1), schedules.get(1), placeTheatres.get(1), 350))
            );
            tickets.add(
                    ticketRepository.save(new Ticket(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME),
                            clients.get(2), schedules.get(2), placeTheatres.get(2), 350))
            );
            tickets.add(
                    ticketRepository.save(new Ticket(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME),
                            clients.get(3), schedules.get(2), placeTheatres.get(3), 350))
            );
            log.info("Loader Tickets: {}", ticketRepository.findAll());
//            List<String> ticketId1 = new ArrayList<>();
//            List<String> ticketId2 = new ArrayList<>();
//            List<String> ticketId3 = new ArrayList<>();
//            List<String> ticketId4 = new ArrayList<>();
//            ticketId1.add(tickets.get(0).getId());
//            ticketId2.add(tickets.get(1).getId());
//            ticketId3.add(tickets.get(2).getId());
//            ticketId4.add(tickets.get(3).getId());
//            clients.get(0).setTicket_id(ticketId1);
//            clients.get(1).setTicket_id(ticketId2);
//            clients.get(2).setTicket_id(ticketId3);
//            clients.get(3).setTicket_id(ticketId4);
//            clientRepository.save(clients.get(0));
//            clientRepository.save(clients.get(1));
//            clientRepository.save(clients.get(2));
//            clientRepository.save(clients.get(3));

            log.info("CLIENTS: {}", clients);

            List<Client> clientResult = clientService.findClientByTicket(tickets.get(1).getId());
            log.info("CLIENT RESULT: {}", clientResult);

            List<Place> placesResult = placeService.getAllPlacesByTheatrePerformanceAndSchedule(
                    theatrePerformances.get(0), schedules.get(3));
            log.info("Places RESULT: {}", placesResult);
        };
    }
}
