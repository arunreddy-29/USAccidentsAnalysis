package com.dbms.usaccidents.usaccidentsanalysis.repository;

import com.dbms.usaccidents.usaccidentsanalysis.entity.VehicleAccidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleAccidentRepository extends JpaRepository<VehicleAccidentEntity, Long> {
}
