package com.vitaly.rest_api_no_spring_app.model;
//  20-Jan-24
// gh crazym8nd


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;

    @Column(name = "event_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Event(User user, File file){
        this.user = user;
        this.file = file;
        this.status = Status.ACTIVE;
    }
}
