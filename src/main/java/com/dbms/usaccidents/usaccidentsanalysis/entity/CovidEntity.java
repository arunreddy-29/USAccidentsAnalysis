package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "covid")
public class CovidEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;
    private Integer day;
    private Integer month;
    private Integer year;
    private Integer cases;
    private Integer deaths;
}
