package com.dbms.usaccidents.usaccidentsanalysis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleAccidentId implements Serializable {
    private Long vehicleId;
    private Long accidentId;
}
