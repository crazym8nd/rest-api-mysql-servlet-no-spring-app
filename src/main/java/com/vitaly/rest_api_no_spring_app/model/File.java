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
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name ="file_name")
    private String name;

    @Column(name ="file_path")
    private String filePath;

    @Column(name ="file_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public File(String name, String filePath){
        this.name = name;
        this.filePath = filePath;
        this.status = Status.ACTIVE;
    }

}

