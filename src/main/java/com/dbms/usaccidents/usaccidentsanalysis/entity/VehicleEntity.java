package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle")
public class VehicleEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String model;
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "driverlicense")
    private DriverEntity driver;
}
