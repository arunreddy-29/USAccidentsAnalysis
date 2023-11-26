package com.dbms.usaccidents.usaccidentsanalysis.service;

import com.dbms.usaccidents.usaccidentsanalysis.repository.*;
import com.dbms.usaccidents.usaccidentsanalysis.schema.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class USAccidentService {

    private final DriverAccidentRepository driverAccidentRepository;
    private final TrafficViolationRepository trafficViolationRepository;

    private final AccidentRepository accidentRepository;

    private final LocationRepository locationRepository;

    private final VehicleRepository vehicleRepository;

    private final WeatherRepository weatherRepository;

    private final DriverRepository driverRepository;


    public USAccidentService(TrafficViolationRepository trafficViolationRepository, AccidentRepository accidentRepository, LocationRepository locationRepository, VehicleRepository vehicleRepository, WeatherRepository weatherRepository, DriverRepository driverRepository, DriverAccidentRepository driverAccidentRepository) {
        this.driverAccidentRepository = driverAccidentRepository;
        this.trafficViolationRepository = trafficViolationRepository;
        this.accidentRepository = accidentRepository;
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository;
        this.weatherRepository = weatherRepository;
        this.driverRepository = driverRepository;
    }

    public Long getTotalRows() {
        return driverAccidentRepository.count() + trafficViolationRepository.count() + accidentRepository.count() + locationRepository.count() + vehicleRepository.count() + weatherRepository.count() + driverRepository.count();
    }

    public List<AccidentResultDto> getAgeGroupTrend(AgeGroupTrendDto ageGroupTrendDto) {

        return driverAccidentRepository.countAccidentsByAgeAndSpecificHour(
                ageGroupTrendDto.getFromAge(),
                ageGroupTrendDto.getToAge(),
                ageGroupTrendDto.getTimeOfDay(),
                ageGroupTrendDto.getFromDate(),
                ageGroupTrendDto.getToDate());
    }

    public List<String> getCarMake() {
        return vehicleRepository.findDistinctCarMake();
    }

    public List<String> getCarModel(String carMake) {
        return vehicleRepository.findDistinctModelsByMake(carMake);
    }

    public List<AccidentResultDto> getCarTypeTrend(CarTypeTrendDto carTypeTrendDto) {
        return driverAccidentRepository.countAccidentsByMakeModelAndMonthYear(
                carTypeTrendDto.getCarMake(),
                carTypeTrendDto.getCarModel(),
                carTypeTrendDto.getFromDate(),
                carTypeTrendDto.getToDate());
    }

    public List<String> getMarylandViolations() {
        return trafficViolationRepository.findDistinctViolationType();
    }

    public List<MarylandViolationResultDto> getMarylandViolationTrend(MarylandViolationTrendDto marylandViolationTrendDto) {

        List<AccidentResultDto> dto1 = accidentRepository.countAccidentsInMDForViolationType(
                marylandViolationTrendDto.getViolation(),
                marylandViolationTrendDto.getFromDate(),
                marylandViolationTrendDto.getToDate());

        List<AccidentResultDto> dto2 = accidentRepository.countAccidentsInMDByMonthYear(
                marylandViolationTrendDto.getFromDate(),
                marylandViolationTrendDto.getToDate());

        List<MarylandViolationResultDto> combinedResults = new ArrayList<>();

        for (int i = 0; i < dto1.size(); i++) {
            AccidentResultDto resultDto1 = dto1.get(i);
            AccidentResultDto resultDto2 = dto2.get(i);

            MarylandViolationResultDto combinedDto = new MarylandViolationResultDto(
                    resultDto1.getMonthYear(),
                    resultDto1.getAccidentCount(),
                    resultDto2.getAccidentCount()
            );

            combinedResults.add(combinedDto);
        }

        return combinedResults;
    }

    public List<CovidTrendResultDto> getCovidTrend(CovidTrendDto covidTrendDto) {

        List<AccidentResultDto> dto1 = accidentRepository.countAccidentsByMonthYear(
                covidTrendDto.getFromDate(),
                covidTrendDto.getToDate());

        List<AccidentResultDto> dto2 = accidentRepository.countCovidCasesByMonthYear(
                covidTrendDto.getFromDate(),
                covidTrendDto.getToDate());

        List<CovidTrendResultDto> combinedResults = new ArrayList<>();

        for (int i = 0; i < dto1.size(); i++) {
            AccidentResultDto resultDto1 = dto1.get(i);
            AccidentResultDto resultDto2 = dto2.get(i);

            CovidTrendResultDto combinedDto = new CovidTrendResultDto(
                    resultDto1.getMonthYear(),
                    resultDto1.getAccidentCount(),
                    resultDto2.getAccidentCount()
            );

            combinedResults.add(combinedDto);
        }

        return combinedResults;
    }

}
