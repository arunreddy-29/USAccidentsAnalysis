package com.dbms.usaccidents.usaccidentsanalysis.repository;

import com.dbms.usaccidents.usaccidentsanalysis.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    @Query("SELECT DISTINCT a.state FROM LocationEntity a")
    List<String> findDistinctStates();

    @Query("SELECT DISTINCT a.city FROM LocationEntity a")
    List<String> findDistinctCities();

    @Query("SELECT DISTINCT a.zipCode FROM LocationEntity a")
    List<String> findDistinctZipCodes();
}
