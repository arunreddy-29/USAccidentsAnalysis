package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DriverAccident")
@IdClass(DriverAccidentId.class)
public class DriverAccidentEntity {

    @Id
    @Column(name = "DriverID")
    private String driver;

    @Id
    @Column(name = "AccidentID")
    private Long accident;

    @ManyToOne
    @MapsId("driver")
    @JoinColumn(name = "DriverID", referencedColumnName = "DriverLicense")
    private DriverEntity driverEntity;

    @ManyToOne
    @MapsId("accident")
    @JoinColumn(name = "AccidentID")
    private AccidentEntity accidentEntity;

}
