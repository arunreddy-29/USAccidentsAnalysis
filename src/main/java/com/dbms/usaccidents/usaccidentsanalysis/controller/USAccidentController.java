package com.dbms.usaccidents.usaccidentsanalysis.controller;


import com.dbms.usaccidents.usaccidentsanalysis.schema.*;
import com.dbms.usaccidents.usaccidentsanalysis.service.USAccidentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<AccidentResultDto> getAgeGroupTrend(
            @RequestParam("fromAge") int fromAge,
            @RequestParam("toAge") int toAge,
            @RequestParam("timeOfDay") String timeOfDay,
            @RequestParam("fromDate") LocalDate fromDate,
            @RequestParam("toDate") LocalDate toDate) {

        String fDate = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String tDate = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        int time = Integer.parseInt(timeOfDay.split(":")[0]);

        AgeGroupTrendDto ageGroupTrendDto = new AgeGroupTrendDto(fromAge, toAge, time, fDate, tDate);
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
    public List<AccidentResultDto> getCarTypeTrend(
            @RequestParam("carMake") String carMake,
            @RequestParam("carModel") String carModel,
            @RequestParam("fromDate") LocalDate fromDate,
            @RequestParam("toDate") LocalDate toDate) {

        String fDate = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String tDate = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        CarTypeTrendDto carTypeTrendDto = new CarTypeTrendDto(carMake, carModel, fDate, tDate);
        return usAccidentService.getCarTypeTrend(carTypeTrendDto);
    }


    @GetMapping("/marylandViolationTrend/violations")
    public List<String> getMarylandViolations() {
        return usAccidentService.getMarylandViolations();
    }


    @GetMapping("/marylandViolationTrend")
    public List<MarylandViolationResultDto> getMarylandViolationTrend(
            @RequestParam String violation,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate) {

        String fDate = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String tDate = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        MarylandViolationTrendDto marylandViolationTrendDto = new MarylandViolationTrendDto(violation, fDate, tDate);
        return usAccidentService.getMarylandViolationTrend(marylandViolationTrendDto);
    }

    @GetMapping("/covidTrend")
    public List<CovidTrendResultDto> getCovidTrend(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate) {

        String fDate = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String tDate = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        CovidTrendDto covidTrendDto = new CovidTrendDto(fDate, tDate);
        return usAccidentService.getCovidTrend(covidTrendDto);
    }
}
