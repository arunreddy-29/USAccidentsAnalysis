package com.dbms.usaccidents.usaccidentsanalysis.repository;

import com.dbms.usaccidents.usaccidentsanalysis.entity.DriverAccidentEntity;
import com.dbms.usaccidents.usaccidentsanalysis.entity.DriverAccidentId;
import com.dbms.usaccidents.usaccidentsanalysis.schema.AccidentResultDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DriverAccidentRepository extends JpaRepository<DriverAccidentEntity, DriverAccidentId> {

    @Query("SELECT CONCAT(LPAD(da.accidentEntity.month, 2, '0'), '/', da.accidentEntity.year) AS monthYear, " +
            "COUNT(da) as accidentCount " +
            "FROM DriverAccidentEntity da " +
            "WHERE da.driverEntity.age BETWEEN :fromAge AND :toAge " +
            "AND FUNCTION('HOUR', da.accidentEntity.timestamp) = :accidentHour " +
            "AND (CAST(CONCAT(da.accidentEntity.year, '-', LPAD(CAST(da.accidentEntity.month AS CHAR), 2, '0'), '-', '01') AS LocalDate) BETWEEN :fromDate AND :toDate) " +
            "GROUP BY CONCAT(LPAD(da.accidentEntity.month, 2, '0'), '/', da.accidentEntity.year)" +
            "ORDER BY da.accidentEntity.year, da.accidentEntity.month")
    List<AccidentResultDto> countAccidentsByAgeAndSpecificHour(
            @Param("fromAge") Integer fromAge,
            @Param("toAge") Integer toAge,
            @Param("accidentHour") Integer accidentHour,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate);

    @Query("SELECT CONCAT(" +
            "LPAD(CAST(acc.month AS CHAR), 2, '0'), '/', acc.year) AS monthYear, " +
            "v.manufacturer AS make, v.model AS model, COUNT(acc) AS accidentCount " +
            "FROM DriverAccidentEntity da " +
            "JOIN da.accidentEntity acc " +
            "JOIN da.driverEntity d " +
            "JOIN d.vehicles v " +
            "WHERE v.manufacturer = :make " +
            "AND v.model = :model " +
            "AND (CAST(CONCAT(acc.year, '-', LPAD(CAST(acc.month AS CHAR), 2, '0'), '-', '01') AS LocalDate) BETWEEN :fromDate AND :toDate) " +
            "GROUP BY CONCAT(LPAD(acc.month, 2, '0'), '/', acc.year)" +
            "ORDER BY acc.year, acc.month")
    List<AccidentResultDto> countAccidentsByMakeModelAndMonthYear(
            @Param("make") String make,
            @Param("model") String model,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate);


//    @Query("SELECT CONCAT(" +
//            "LPAD(CAST(acc.month AS CHAR), 2, '0'), '/', acc.year) AS monthYear, " +
//            "v.manufacturer AS make, v.model AS model, COUNT(acc) AS accidentCount " +
//            "FROM DriverAccidentEntity da " +
//            "JOIN da.accidentEntity acc " +
//            "JOIN da.driverEntity d " +
//            "JOIN d.vehicles v " +
//            "WHERE v.manufacturer = :make " +
//            "AND v.model = :model " +
//            "AND (CAST(CONCAT(acc.year, '-', LPAD(CAST(acc.month AS CHAR), 2, '0'), '-', '01') AS LocalDate) BETWEEN :fromDate AND :toDate) " +
//            "GROUP BY acc.year, acc.month, v.manufacturer, v.model " +
//            "ORDER BY acc.year, acc.month, v.manufacturer, v.model")
//    List<AccidentResultDto> countAccidentsByMakeModelAndMonthYear(
//            @Param("make") String make,
//            @Param("model") String model,
//            @Param("fromDate") LocalDate fromDate,
//            @Param("toDate") LocalDate toDate);
}


