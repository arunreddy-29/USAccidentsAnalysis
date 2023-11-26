package com.dbms.usaccidents.usaccidentsanalysis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverAccidentId implements Serializable {
    private String driver;
    private Long accident;

}
