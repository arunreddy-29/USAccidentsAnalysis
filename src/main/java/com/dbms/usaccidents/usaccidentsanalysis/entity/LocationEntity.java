package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class LocationEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String county;
    private String state;
    private String zipcode;
}
