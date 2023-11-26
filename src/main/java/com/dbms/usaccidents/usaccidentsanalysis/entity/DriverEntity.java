package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "driver")
public class DriverEntity {

    @Id
    @Column(name = "driverLicense")
    private String driverLicense;
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "driver")
    private Set<VehicleEntity> vehicles;
}

