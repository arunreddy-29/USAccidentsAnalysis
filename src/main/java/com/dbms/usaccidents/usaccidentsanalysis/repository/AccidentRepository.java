package com.dbms.usaccidents.usaccidentsanalysis.repository;

import com.dbms.usaccidents.usaccidentsanalysis.entity.AccidentEntity;
import com.dbms.usaccidents.usaccidentsanalysis.schema.AccidentResultDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AccidentRepository extends JpaRepository<AccidentEntity, Long> {

    @Query("SELECT CONCAT(" +
            "CASE WHEN t.month < 10 THEN '0' ELSE '' END, t.month, '/', t.year) AS monthYear, " +
            "COUNT(t) AS countFromMaryland " +
            "FROM TrafficViolationEntity t " +
            "WHERE t.violationtype = :violationType " +
            "AND t.accident = 'Yes' " +
            "AND t.year >= YEAR(:fromDate) " +
            "AND t.month >= MONTH(:fromDate) " +
            "AND t.year <= YEAR(:toDate) " +
            "AND t.month <= MONTH(:toDate) " +
            "GROUP BY CONCAT(" +
            "CASE WHEN t.month < 10 THEN '0' ELSE '' END, t.month, '/', t.year) " +
            "ORDER BY t.year, t.month")
    List<AccidentResultDto> countAccidentsInMDForViolationType(
            @Param("violationType") String violationType,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate);

    @Query("SELECT CONCAT(" +
            "CASE WHEN a.month < 10 THEN '0' ELSE '' END, a.month, '/', a.year) AS monthYear, " +
            "COUNT(a) AS countFromAccident " +
            "FROM AccidentEntity a " +
            "JOIN a.location l " +
            "WHERE l.state = 'MD' " +
            "AND a.year >= YEAR(:fromDate) " +
            "AND a.month >= MONTH(:fromDate) " +
            "AND a.year <= YEAR(:toDate) " +
            "AND a.month <= MONTH(:toDate) " +
            "GROUP BY CONCAT(" +
            "CASE WHEN a.month < 10 THEN '0' ELSE '' END, a.month, '/', a.year) " +
            "ORDER BY a.year, a.month")
    List<AccidentResultDto> countAccidentsInMDByMonthYear(
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate);

    @Query("SELECT CONCAT(" +
            "CASE WHEN a.month < 10 THEN '0' ELSE '' END, a.month, '/', a.year) AS monthYear, " +
            "COUNT(a) AS accidentCount " +
            "FROM AccidentEntity a " +
            "JOIN a.location l " +
            "WHERE a.year >= YEAR(:fromDate) " +
            "AND a.month >= MONTH(:fromDate) " +
            "AND a.year <= YEAR(:toDate) " +
            "AND a.month <= MONTH(:toDate) " +
            "GROUP BY CONCAT(" +
            "CASE WHEN a.month < 10 THEN '0' ELSE '' END, a.month, '/', a.year) " +
            "ORDER BY a.year, a.month")
    List<AccidentResultDto> countAccidentsByMonthYear(
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate);

    @Query("SELECT CONCAT(" +
            "CASE WHEN c.month < 10 THEN '0' ELSE '' END, c.month, '/', c.year) AS monthYear, " +
            "SUM(c.cases) AS covidCaseCount " +
            "FROM CovidEntity c " +
            "WHERE c.year >= YEAR(:fromDate) " +
            "AND c.month >= MONTH(:fromDate) " +
            "AND c.day >= DAY(:fromDate) " +
            "AND c.year <= YEAR(:toDate) " +
            "AND c.month <= MONTH(:toDate) " +
            "AND c.day <= DAY(:toDate) " +
            "GROUP BY CONCAT(" +
            "CASE WHEN c.month < 10 THEN '0' ELSE '' END, c.month, '/', c.year) " +
            "ORDER BY c.year, c.month")
    List<AccidentResultDto> countCovidCasesByMonthYear(
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate);

    @Query("SELECT CONCAT(" +
            "CASE WHEN a.month < 10 THEN '0' ELSE '' END, a.month, '/', a.year) AS monthYear, COUNT(a) as accidentCount " +
            "FROM AccidentEntity a " +
            "JOIN a.location l " +
            "JOIN a.weather w " +
            "WHERE a.year >= YEAR(:fromDate) " +
            "AND a.month >= MONTH(:fromDate) " +
            "AND a.year <= YEAR(:toDate) " +
            "AND a.month <= MONTH(:toDate) " +
            "AND (:weatherCondition IS NULL OR w.weathercondition LIKE CONCAT('%', :weatherCondition, '%')) " +
            "AND (:city IS NULL OR l.city = :city) " +
            "AND (:state IS NULL OR l.state = :state) " +
            "AND (:zipCode IS NULL OR l.zipcode = :zipCode) " +
            "GROUP BY CONCAT(" +
            "CASE WHEN a.month < 10 THEN '0' ELSE '' END, a.month, '/', a.year) " +
            "ORDER BY a.year, a.month")
    List<AccidentResultDto> countAccidentsByLocationAndWeatherConditionAndMonthYearRange(
            @Param("city") String city,
            @Param("state") String state,
            @Param("zipCode") String zipCode,
            @Param("weatherCondition") String weatherCondition,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate);


    @Query("SELECT CONCAT(" +
            "CASE WHEN EXTRACT(MONTH FROM a.timestamp) < 10 THEN '0' ELSE '' END, EXTRACT(MONTH FROM a.timestamp), '/', EXTRACT(YEAR FROM a.timestamp)) AS monthYear, COUNT(a) as accidentCount " +
            "FROM AccidentEntity a " +
            "JOIN VehicleAccidentEntity va ON va.accident = a " +
            "JOIN VehicleEntity v ON va.vehicle = v " +
            "JOIN DriverEntity d ON v.driver = d " +
            "WHERE EXTRACT(YEAR FROM a.timestamp) >= YEAR(:fromDate) " +
            "AND EXTRACT(MONTH FROM a.timestamp) >= MONTH(:fromDate) " +
            "AND EXTRACT(YEAR FROM a.timestamp) <= YEAR(:toDate) " +
            "AND EXTRACT(MONTH FROM a.timestamp) <= MONTH(:toDate) " +
            "AND d.age BETWEEN :startAge AND :endAge " +
            "AND EXTRACT(HOUR FROM a.timestamp) = :hour " +
            "GROUP BY CONCAT(" +
            "CASE WHEN EXTRACT(MONTH FROM a.timestamp) < 10 THEN '0' ELSE '' END, EXTRACT(MONTH FROM a.timestamp), '/', EXTRACT(YEAR FROM a.timestamp)) " +
            "ORDER BY EXTRACT(YEAR FROM a.timestamp), EXTRACT(MONTH FROM a.timestamp)")
    List<AccidentResultDto> countAccidentsByAgeAndTimePeriod(
            @Param("startAge") int startAge,
            @Param("endAge") int endAge,
            @Param("hour") int hour,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate);

    @Query("SELECT v.manufacturer, v.model, FUNCTION('YEAR', a.timestamp) as year, COUNT(a) " +
            "FROM VehicleEntity v " +
            "JOIN VehicleAccidentEntity va ON va.vehicle.id = v.id " +
            "JOIN AccidentEntity a ON va.accident.id = a.id " +
            "WHERE v.manufacturer = :manufacturer " +
            "AND v.model = :model " +
            "AND a.timestamp BETWEEN :fromDate AND :toDate " +
            "GROUP BY v.manufacturer, v.model, FUNCTION('YEAR', a.timestamp) " +
            "ORDER BY a.year")
    List<AccidentResultDto> findAccidentCountsByManufacturerAndModelAndYear(
            @Param("manufacturer") String manufacturer,
            @Param("model") String model,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate);





}
