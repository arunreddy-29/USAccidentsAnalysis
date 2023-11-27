package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicleaccident")
@IdClass(VehicleAccidentId.class)
public class VehicleAccidentEntity {

    @Id
    @Column(name = "vehicleid")
    private Long vehicleId;

    @Id
    @Column(name = "accidentid")
    private Long accidentId;

    @ManyToOne
    @MapsId("vehicleId")
    @JoinColumn(name = "vehicleid")
    private VehicleEntity vehicle;

    @ManyToOne
    @MapsId("accidentId")
    @JoinColumn(name = "accidentid")
    private AccidentEntity accident;

}
