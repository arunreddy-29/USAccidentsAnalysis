package com.dbms.usaccidents.usaccidentsanalysis.controller;


import com.dbms.usaccidents.usaccidentsanalysis.schema.*;
import com.dbms.usaccidents.usaccidentsanalysis.service.USAccidentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/usaccidents")
public class USAccidentController {

    private final USAccidentService usAccidentService;

    public USAccidentController(USAccidentService usAccidentService) {
        this.usAccidentService = usAccidentService;
    }

    @GetMapping("/totalRows")
    public Long getTotalRows() {
        return usAccidentService.getTotalRows();
    }

    @GetMapping("/ageGroupTrend")
    public AccidentResultDto getAgeGroupTrend(
            @RequestParam("fromAge") int fromAge,
            @RequestParam("toAge") int toAge,
            @RequestParam("timeOfDay") String timeOfDay,
            @RequestParam("fromDate") LocalDate fromDate,
            @RequestParam("toDate") LocalDate toDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDatetime = LocalDateTime.parse(fromDate + " 00:00:00", formatter);
        LocalDateTime toDatetime = LocalDateTime.parse(toDate + " 23:59:59", formatter);

        int time = Integer.parseInt(timeOfDay.split(":")[0]);

        AgeGroupTrendDto ageGroupTrendDto = new AgeGroupTrendDto(fromAge, toAge, time, fromDatetime, toDatetime);
        return usAccidentService.getAgeGroupTrend(ageGroupTrendDto);
    }

    @GetMapping("/carTypeTrend/carMake")
    public List<String> getCarMake() {
        return usAccidentService.getCarMake();
    }

    @GetMapping("/carTypeTrend/carModel")
    public List<String> getCarModel(@RequestParam("carMake") String carMake) {
        return usAccidentService.getCarModel(carMake);
    }

    @GetMapping("/carTypeTrend")
    public AccidentResultDto getCarTypeTrend(
            @RequestParam("carMake") String carMake,
            @RequestParam("carModel") String carModel,
            @RequestParam("fromDate") LocalDate fromDate,
            @RequestParam("toDate") LocalDate toDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDatetime = LocalDateTime.parse(fromDate + " 00:00:00", formatter);
        LocalDateTime toDatetime = LocalDateTime.parse(toDate + " 23:59:59", formatter);

        CarTypeTrendDto carTypeTrendDto = new CarTypeTrendDto(carMake, carModel, fromDatetime, toDatetime);
        return usAccidentService.getCarTypeTrend(carTypeTrendDto);
    }


    @GetMapping("/marylandViolationTrend/violations")
    public List<String> getMarylandViolations() {
        return usAccidentService.getMarylandViolations();
    }


    @GetMapping("/marylandViolationTrend")
    public MarylandViolationResultDto getMarylandViolationTrend(
            @RequestParam String violation,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDatetime = LocalDateTime.parse(fromDate + " 00:00:00", formatter);
        LocalDateTime toDatetime = LocalDateTime.parse(toDate + " 23:59:59", formatter);

        MarylandViolationTrendDto marylandViolationTrendDto = new MarylandViolationTrendDto(violation, fromDatetime, toDatetime);
        return usAccidentService.getMarylandViolationTrend(marylandViolationTrendDto);
    }

    @GetMapping("/covidTrend")
    public List<CovidTrendResultDto> getCovidTrend(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDatetime = LocalDateTime.parse(fromDate + " 00:00:00", formatter);
        LocalDateTime toDatetime = LocalDateTime.parse(toDate + " 23:59:59", formatter);

        CovidTrendDto covidTrendDto = new CovidTrendDto(fromDatetime, toDatetime);
        return usAccidentService.getCovidTrend(covidTrendDto);
    }
}
