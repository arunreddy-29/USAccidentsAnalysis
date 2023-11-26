package com.dbms.usaccidents.usaccidentsanalysis.controller;


import com.dbms.usaccidents.usaccidentsanalysis.schema.*;
import com.dbms.usaccidents.usaccidentsanalysis.service.USAccidentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/usaccidents")
public class USAccidentController {

    private final USAccidentService usAccidentService;

    public USAccidentController( USAccidentService usAccidentService) {
        this.usAccidentService = usAccidentService;
    }

    @GetMapping("/totalRows")
    public Long getTotalRows() {
        return usAccidentService.getTotalRows();
    }

    @GetMapping("/ageGroupTrend")
    public List<AccidentResultDto> getAgeGroupTrend(
            @RequestParam("fromAge") int fromAge,
            @RequestParam("toAge") int toAge,
            @RequestParam("timeOfDay") int timeOfDay,
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        AgeGroupTrendDto ageGroupTrendDto = new AgeGroupTrendDto(fromAge, toAge, timeOfDay, fromDate, toDate);
        return usAccidentService.getAgeGroupTrend(ageGroupTrendDto);
    }

    @GetMapping("/carTypeTrend")
    public List<AccidentResultDto> getCarTypeTrend(
            @RequestParam("carMake") String carMake,
            @RequestParam("carModel") String carModel,
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        CarTypeTrendDto carTypeTrendDto = new CarTypeTrendDto(carMake, carModel, fromDate, toDate);
        return usAccidentService.getCarTypeTrend(carTypeTrendDto);
    }

    @GetMapping("/marylandViolationTrend/violations")
    public List<String> getMarylandViolations() {
        return usAccidentService.getMarylandViolations();
    }

    @GetMapping("/marylandViolationTrend")
    public List<MarylandViolationResultDto> getMarylandViolationTrend(
            @RequestParam String violation,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        MarylandViolationTrendDto marylandViolationTrendDto = new MarylandViolationTrendDto(violation, fromDate, toDate);
        return usAccidentService.getMarylandViolationTrend(marylandViolationTrendDto);
    }

    @GetMapping("/covidTrend")
    public List<CovidTrendResultDto> getCovidTrend(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        CovidTrendDto covidTrendDto = new CovidTrendDto(fromDate, toDate);
        return usAccidentService.getCovidTrend(covidTrendDto);
    }
}
