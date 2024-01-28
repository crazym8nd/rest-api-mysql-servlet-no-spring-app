package com.vitaly.rest_api_no_spring_app.model;
//  20-Jan-24
// gh crazym8nd


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToOne
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
