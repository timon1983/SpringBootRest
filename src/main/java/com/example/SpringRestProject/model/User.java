package com.example.SpringRestProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id" , unique = true , nullable = false)
    private long id;

    @Column(name = "first_name" , length = 45 , nullable = false)
    private String firstName;

    @Column(name = "last_name" , length = 45 , nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "userList", cascade = CascadeType.ALL)
    private List<Event> events;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private List<Event> events;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Event event;

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
