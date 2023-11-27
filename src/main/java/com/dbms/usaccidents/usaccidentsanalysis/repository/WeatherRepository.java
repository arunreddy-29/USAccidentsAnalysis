package com.dbms.usaccidents.usaccidentsanalysis.repository;

import com.dbms.usaccidents.usaccidentsanalysis.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    @Query("SELECT DISTINCT a.weathercondition FROM WeatherEntity a")
    List<String> findDistinctWeatherCondition();

}
