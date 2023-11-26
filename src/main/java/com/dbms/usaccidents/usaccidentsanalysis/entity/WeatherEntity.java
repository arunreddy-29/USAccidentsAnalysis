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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double temperature;
    private Double humidity;
    private Double pressure;
    private String windDirection;
    private Double windSpeed;
    private String weatherCondition;
    private Double visibility;

}
