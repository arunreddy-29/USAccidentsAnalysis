package com.dbms.usaccidents.usaccidentsanalysis.service;

import com.dbms.usaccidents.usaccidentsanalysis.repository.AccidentRepository;
import com.dbms.usaccidents.usaccidentsanalysis.repository.LocationRepository;
import com.dbms.usaccidents.usaccidentsanalysis.repository.WeatherRepository;
import com.dbms.usaccidents.usaccidentsanalysis.schema.AccidentResultDto;
import com.dbms.usaccidents.usaccidentsanalysis.schema.LocationType;
import com.dbms.usaccidents.usaccidentsanalysis.schema.WeatherTrendDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dbms.usaccidents.usaccidentsanalysis.schema.LocationType.*;

@Service
public class WeatherTrendService {

    private final LocationRepository locationRepository;
    private final AccidentRepository accidentRepository;

    private final WeatherRepository weatherRepository;

    public WeatherTrendService(LocationRepository locationRepository, AccidentRepository accidentRepository, WeatherRepository weatherRepository) {
        this.locationRepository = locationRepository;
        this.accidentRepository = accidentRepository;
        this.weatherRepository = weatherRepository;
    }

    public List<String> getDistinctLocationValues(String type) {
        return switch (type) {
            case "STATE" -> locationRepository.findDistinctStates();
            case "CITY" -> locationRepository.findDistinctCities();
            case "ZIPCODE" -> locationRepository.findDistinctZipCodes();
            default -> throw new IllegalArgumentException("Invalid Location Type");
        };
    }

    public List<String> getWeatherConditions() {
        return weatherRepository.findDistinctWeatherCondition();
    }

    public List<AccidentResultDto> getWeatherTrend(WeatherTrendDto weatherTrendDto) {
        String state = null;
        String city = null;
        String zipcode = null;

        if (weatherTrendDto.getLocation().name().equals("STATE")) {
            state = weatherTrendDto.getLocationValue();
        } else if (weatherTrendDto.getLocation().name().equals("CITY")) {
            city = weatherTrendDto.getLocationValue();
        } else if (weatherTrendDto.getLocation().name().equals("ZIPCODE")) {
            zipcode = weatherTrendDto.getLocationValue();
        }

        return accidentRepository.countAccidentsByLocationAndWeatherConditionAndMonthYearRange(
                city,
                state,
                zipcode,
                weatherTrendDto.getWeatherCondition(),
                weatherTrendDto.getFromDate(),
                weatherTrendDto.getToDate()
        );
    }
}
