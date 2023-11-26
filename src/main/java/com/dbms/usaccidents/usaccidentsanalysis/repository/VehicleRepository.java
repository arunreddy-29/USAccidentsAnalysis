package com.dbms.usaccidents.usaccidentsanalysis.repository;

import com.dbms.usaccidents.usaccidentsanalysis.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    @Query("SELECT DISTINCT a.manufacturer FROM VehicleEntity a")
    List<String> findDistinctCarMake();

    @Query("SELECT DISTINCT v.model FROM VehicleEntity v WHERE v.manufacturer = :carMake")
    List<String> findDistinctModelsByMake(String carMake);

}
