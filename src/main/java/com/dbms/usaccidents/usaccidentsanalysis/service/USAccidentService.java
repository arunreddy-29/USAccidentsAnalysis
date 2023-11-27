package com.dbms.usaccidents.usaccidentsanalysis.service;

import com.dbms.usaccidents.usaccidentsanalysis.repository.*;
import com.dbms.usaccidents.usaccidentsanalysis.schema.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class USAccidentService {

    private final TrafficViolationRepository trafficViolationRepository;

    private final AccidentRepository accidentRepository;

    private final LocationRepository locationRepository;

    private final VehicleRepository vehicleRepository;

    private final WeatherRepository weatherRepository;

    private final DriverRepository driverRepository;
    private final VehicleAccidentRepository vehicleAccidentRepository;


    public USAccidentService(TrafficViolationRepository trafficViolationRepository, AccidentRepository accidentRepository, LocationRepository locationRepository, VehicleRepository vehicleRepository, WeatherRepository weatherRepository, DriverRepository driverRepository, VehicleAccidentRepository vehicleAccidentRepository) {
        this.trafficViolationRepository = trafficViolationRepository;
        this.accidentRepository = accidentRepository;
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository;
        this.weatherRepository = weatherRepository;
        this.driverRepository = driverRepository;
        this.vehicleAccidentRepository = vehicleAccidentRepository;
    }

    public Long getTotalRows() {
        return trafficViolationRepository.count() + accidentRepository.count() + locationRepository.count() + vehicleRepository.count() + weatherRepository.count() + driverRepository.count() + vehicleAccidentRepository.count();
    }

    public AccidentResultDto getAgeGroupTrend(AgeGroupTrendDto ageGroupTrendDto) {

        List<Object[]> dto1 = accidentRepository.countAccidentsByAgeAndTimePeriod(
                ageGroupTrendDto.getFromAge(),
                ageGroupTrendDto.getToAge(),
                ageGroupTrendDto.getTimeOfDay(),
                ageGroupTrendDto.getFromDate(),
                ageGroupTrendDto.getToDate());

        return createAccidentResult(dto1);
    }

    private AccidentResultDto createAccidentResult(List<Object[]> dto1) {
        AccidentResultDto accidentResultDto = new AccidentResultDto();
        List<String> monthYear = new ArrayList<>();
        List<Integer> accidentCount = new ArrayList<>();

        for (int i = 0; i < dto1.size(); i++) {
            Object[] resultDto1 = dto1.get(i);

            monthYear.add(resultDto1[0].toString());
            accidentCount.add(Integer.valueOf(resultDto1[1].toString()));

        }
        accidentResultDto.setMonthYear(monthYear);
        accidentResultDto.setAccidentCount(accidentCount);

        return accidentResultDto;
    }

    public List<String> getCarMake() {
        return vehicleRepository.findDistinctCarMake();
    }

    public List<String> getCarModel(String carMake) {
        return vehicleRepository.findDistinctModelsByMake(carMake);
    }

    public AccidentResultDto getCarTypeTrend(CarTypeTrendDto carTypeTrendDto) {

        List<Object[]> dto1 = accidentRepository.findAccidentCountsByManufacturerAndModel(
                carTypeTrendDto.getCarMake(),
                carTypeTrendDto.getCarModel(),
                carTypeTrendDto.getFromDate(),
                carTypeTrendDto.getToDate());

        return createAccidentResult(dto1);
    }

    public List<String> getMarylandViolations() {
        return trafficViolationRepository.findDistinctViolationType();
    }

    public MarylandViolationResultDto getMarylandViolationTrend(MarylandViolationTrendDto marylandViolationTrendDto) {

        List<Object[]> dto1 = accidentRepository.countAccidentsInMDForViolationType(
                marylandViolationTrendDto.getViolation(),
                marylandViolationTrendDto.getFromDate(),
                marylandViolationTrendDto.getToDate());

        List<Object[]> dto2 = accidentRepository.countAccidentsInMDByMonthYear(
                marylandViolationTrendDto.getFromDate(),
                marylandViolationTrendDto.getToDate());

        MarylandViolationResultDto marylandViolationResultDto = new MarylandViolationResultDto();

        List<String> monthYear = new ArrayList<>();
        List<Integer> countFromMaryland = new ArrayList<>();
        List<Integer> countFromAccident = new ArrayList<>();

        for (int i = 0; i < dto1.size(); i++) {
            Object[] resultDto1 = dto1.get(i);
            Object[] resultDto2 = dto2.get(i);

            monthYear.add(resultDto1[0].toString());
            countFromMaryland.add(Integer.valueOf(resultDto1[1].toString()));
            countFromAccident.add(Integer.valueOf(resultDto2[1].toString()));
        }

        marylandViolationResultDto.setMonthYear(monthYear);
        marylandViolationResultDto.setCountFromMaryland(countFromMaryland);
        marylandViolationResultDto.setCountFromAccident(countFromAccident);

        return marylandViolationResultDto;
    }

    public List<CovidTrendResultDto> getCovidTrend(CovidTrendDto covidTrendDto) {

        List<Object[]> dto1 = accidentRepository.countAccidentsByMonthYear(
                covidTrendDto.getFromDate(),
                covidTrendDto.getToDate());

        List<Object[]> dto2 = accidentRepository.countCovidCasesByMonthYear(
                covidTrendDto.getFromDate(),
                covidTrendDto.getToDate());

//        List<CovidTrendResultDto> combinedResults = new ArrayList<>();
//
//        for (int i = 0; i < dto1.size(); i++) {
//            Object[] resultDto1 = dto1.get(i);
//            Object[] resultDto2 = dto2.get(i);
//
//            CovidTrendResultDto combinedDto = new CovidTrendResultDto(
//                    resultDto1.getMonthYear(),
//                    resultDto1.getAccidentCount(),
//                    resultDto2.getAccidentCount()
//            );
//
//            combinedResults.add(combinedDto);
//        }

        return null;
    }

}
