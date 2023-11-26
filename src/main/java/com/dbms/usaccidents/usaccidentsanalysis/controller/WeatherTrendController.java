package com.dbms.usaccidents.usaccidentsanalysis.controller;

import com.dbms.usaccidents.usaccidentsanalysis.schema.AccidentResultDto;
import com.dbms.usaccidents.usaccidentsanalysis.schema.LocationType;
import com.dbms.usaccidents.usaccidentsanalysis.schema.WeatherTrendDto;
import com.dbms.usaccidents.usaccidentsanalysis.service.WeatherTrendService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/usaccidents/weatherTrend")
public class WeatherTrendController {

    private final WeatherTrendService weatherTrendService;

    public WeatherTrendController(WeatherTrendService weatherTrendService) {
        this.weatherTrendService = weatherTrendService;
    }

    @GetMapping("/types")
    public List<String> getLocationTypes() {
        return Arrays.stream(LocationType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/locationValues")
    public List<String> getLocationValues(@RequestParam String type) {
        return weatherTrendService.getDistinctLocationValues(type);
    }

    @GetMapping("/weatherConditions")
    public List<String> getWeatherConditions() {
        return weatherTrendService.getWeatherConditions();
    }

    @GetMapping
    public List<AccidentResultDto> getWeatherTrend(
            @RequestParam("location") LocationType location,
            @RequestParam("locationValue") String locationValue,
            @RequestParam("weatherCondition") String weatherCondition,
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        WeatherTrendDto weatherTrendDto = new WeatherTrendDto(location, locationValue, weatherCondition, fromDate, toDate);
        return weatherTrendService.getWeatherTrend(weatherTrendDto);
    }
}
