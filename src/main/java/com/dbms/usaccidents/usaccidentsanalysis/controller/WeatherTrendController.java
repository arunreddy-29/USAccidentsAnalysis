package com.dbms.usaccidents.usaccidentsanalysis.controller;

import com.dbms.usaccidents.usaccidentsanalysis.schema.AccidentResultDto;
import com.dbms.usaccidents.usaccidentsanalysis.schema.LocationType;
import com.dbms.usaccidents.usaccidentsanalysis.schema.WeatherTrendDto;
import com.dbms.usaccidents.usaccidentsanalysis.service.WeatherTrendService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public AccidentResultDto getWeatherTrend(
            @RequestParam("location") LocationType location,
            @RequestParam("locationValue") String locationValue,
            @RequestParam("weatherCondition") String weatherCondition,
            @RequestParam("fromDate") LocalDate fromDate,
            @RequestParam("toDate") LocalDate toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDatetime = LocalDateTime.parse(fromDate + " 00:00:00", formatter);
        LocalDateTime toDatetime = LocalDateTime.parse(toDate + " 23:59:59", formatter);
        WeatherTrendDto weatherTrendDto = new WeatherTrendDto(location, locationValue, weatherCondition, fromDatetime, toDatetime);
        return weatherTrendService.getWeatherTrend(weatherTrendDto);
    }
}
