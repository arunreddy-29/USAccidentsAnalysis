package com.dbms.usaccidents.usaccidentsanalysis.repository;

import com.dbms.usaccidents.usaccidentsanalysis.entity.TrafficViolationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrafficViolationRepository extends JpaRepository<TrafficViolationEntity, Long> {

    @Query("SELECT DISTINCT a.violationtype FROM  TrafficViolationEntity a")
    List<String> findDistinctViolationType();
}
