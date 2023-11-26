package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VehicleAccident")
@IdClass(VehicleAccidentId.class)
public class VehicleAccidentEntity {

    @Id
    @Column(name = "VehicleID")
    private Long vehicleId;

    @Id
    @Column(name = "AccidentID")
    private Long accidentId;

    @ManyToOne
    @MapsId("vehicleId")
    @JoinColumn(name = "VehicleID")
    private VehicleEntity vehicle;

    @ManyToOne
    @MapsId("accidentId")
    @JoinColumn(name = "AccidentID")
    private AccidentEntity accident;

}
