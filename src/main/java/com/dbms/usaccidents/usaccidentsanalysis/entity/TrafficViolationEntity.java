package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "traffic_violations")
public class TrafficViolationEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer month;
    private Integer year;
    private Integer day;
    private String time;
    private String accident;
    private String overspeeding;
    private String signaljump;
    private String alcohol;
    private String violationtype;
    private String driverstate;

}
