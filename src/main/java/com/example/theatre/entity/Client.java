package com.example.theatre.entity;




import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
@Table(name = "client")
@NoArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Client {
    @Transient
    public static final String SEQUENCE_NAME = "clients_sequence";

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=1, message="Поле Имя должно иметь хотя бы 1 символ")
    private String name;

    @NotNull
    @Size(min=1, message="Поле Фамилия должно иметь хотя бы 1 символ")
    private String surname;

    @NotNull
//    @Email(message = "Адрес электронной почты введен не корректно")
    @Size(min=5, message="Поле Email должно иметь хотя бы 5 символов")
    private String email;

//    List<String> ticket_id = new ArrayList<>();
//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<Ticket> tickets = new ArrayList<>();


    public Client(Long id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

//    public void addTicket(Ticket ticket) {
//        ticket.setClient(this);
//        this.tickets.add(ticket);
//    }

//    public void removeTicket(Ticket ticket) {
//        this.tickets.remove(ticket);
//    }

}
