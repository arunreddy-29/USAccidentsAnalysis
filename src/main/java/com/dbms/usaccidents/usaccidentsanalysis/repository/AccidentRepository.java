package com.dbms.usaccidents.usaccidentsanalysis.repository;

import com.dbms.usaccidents.usaccidentsanalysis.entity.AccidentEntity;
import com.dbms.usaccidents.usaccidentsanalysis.schema.AccidentResultDto;
import com.dbms.usaccidents.usaccidentsanalysis.schema.CovidTrendResultDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccidentRepository extends JpaRepository<AccidentEntity, Long> {

    @Query("SELECT CONCAT(" +
            "LPAD(CAST(t.month AS CHAR), 2, '0'), '/', t.year) AS monthYear, " +
            "COUNT(t) AS countFromMaryland " +
            "FROM TrafficViolationEntity t " +
            "WHERE t.violationType = :violationType " +
            "AND t.accident = 'Yes' " +
            "AND (CAST(CONCAT(t.year, '-', LPAD(CAST(t.month AS CHAR), 2, '0'), '-', '01') AS LocalDate) BETWEEN :fromDate AND :toDate) " +
            "GROUP BY CONCAT(" +
            "LPAD(CAST(t.month AS CHAR), 2, '0'), '/', t.year) " +
            "ORDER BY t.year, t.month")
    List<AccidentResultDto> countAccidentsInMDForViolationType(
            @Param("violationType") String violationType,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate);


    @Query("SELECT CONCAT(" +
            "LPAD(CAST(a.month AS CHAR), 2, '0'), '/', a.year) AS monthYear, " +
            "COUNT(a) AS countFromAccident " +
            "FROM AccidentEntity a " +
            "JOIN a.location l " +
            "WHERE l.state = 'MD' " +
            "AND (CAST(CONCAT(a.year, '-', LPAD(CAST(a.month AS CHAR), 2, '0'), '-', '01') AS LocalDate) BETWEEN :fromDate AND :toDate) " +
            "GROUP BY CONCAT(" +
            "LPAD(CAST(a.month AS CHAR), 2, '0'), '/', a.year) " +
            "ORDER BY a.year, a.month")
    List<AccidentResultDto> countAccidentsInMDByMonthYear(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate);


    @Query("SELECT CONCAT(LPAD(a.month, 2, '0'), '/', a.year) AS monthYear, COUNT(a) as accidentCount " +
            "FROM AccidentEntity a " +
            "JOIN a.location l " +
            "JOIN a.weather w " +
            "WHERE (CAST(CONCAT(a.year, '-', LPAD(CAST(a.month AS CHAR), 2, '0'), '-', '01') AS LocalDate) BETWEEN :fromDate AND :toDate) " +
            "AND (:weatherCondition IS NULL OR w.weatherCondition LIKE CONCAT('%', :weatherCondition, '%')) " +
            "AND (:city IS NULL OR l.city = :city) " +
            "AND (:state IS NULL OR l.state = :state) " +
            "AND (:zipCode IS NULL OR l.zipCode = :zipCode) " +
            "GROUP BY CONCAT(LPAD(a.month, 2, '0'), '/', a.year)")
    List<AccidentResultDto> countAccidentsByLocationAndWeatherConditionAndMonthYearRange(
            @Param("city") String city,
            @Param("state") String state,
            @Param("zipCode") String zipCode,
            @Param("weatherCondition") String weatherCondition,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate);

    @Query("SELECT CONCAT(" +
            "LPAD(CAST(a.month AS CHAR), 2, '0'), '/', a.year) AS monthYear, " +
            "COUNT(a) AS accidentCount " +
            "FROM AccidentEntity a " +
            "JOIN a.location l " +
            "WHERE (CAST(CONCAT(a.year, '-', LPAD(CAST(a.month AS CHAR), 2, '0'), '-', '01') AS LocalDate) BETWEEN :fromDate AND :toDate) " +
            "GROUP BY CONCAT(" +
            "LPAD(CAST(a.month AS CHAR), 2, '0'), '/', a.year) " +
            "ORDER BY a.year, a.month")
    List<AccidentResultDto> countAccidentsByMonthYear(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate);

    @Query("SELECT CONCAT(" +
            "LPAD(CAST(c.month AS CHAR), 2, '0'), '/', c.year) AS monthYear, " +
            "SUM(c.cases) AS covidCaseCount " +
            "FROM CovidEntity c " +
            "WHERE (CAST(CONCAT(c.year, '-', LPAD(CAST(c.month AS CHAR), 2, '0'), '-', LPAD(CAST(c.day AS CHAR), 2, '0')) AS LocalDate) BETWEEN :fromDate AND :toDate) " +
            "GROUP BY CONCAT(" +
            "LPAD(CAST(c.month AS CHAR), 2, '0'), '/', c.year) " +
            "ORDER BY c.year, c.month")
    List<AccidentResultDto> countCovidCasesByMonthYear(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate);


}
