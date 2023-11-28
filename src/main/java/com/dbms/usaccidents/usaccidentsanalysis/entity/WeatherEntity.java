package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather")
public class WeatherEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float temperature;
    private Float humidity;
    private Float pressure;
    private String winddirection;
    private Float windspeed;
    private String weathercondition;
    private Float visibility;

}
