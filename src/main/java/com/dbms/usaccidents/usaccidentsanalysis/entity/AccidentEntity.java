package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accident")
public class AccidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean noexit;
    private Boolean railway;
    private Boolean junction;
    private Boolean crossing;
    private Boolean bump;
    private Boolean stop;
    private Boolean trafficsignal;
    private Boolean roundabout;
    private Boolean turningloop;
    private LocalDateTime timestamp;
    private Integer month;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "LocationID")
    private LocationEntity location;

    @ManyToOne
    @JoinColumn(name = "WeatherID")
    private WeatherEntity weather;
}
