package com.vitaly.rest_api_no_spring_app.model;
//  20-Jan-24
// gh crazym8nd


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name = "user_name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Event> events;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public User(Integer id, String name, List<Event> events){
        this.id = id;
        this.name = name;
        this.events = events;
        this.status = Status.ACTIVE;
    }
    public User(String name){
        this.name = name;
    }

}
