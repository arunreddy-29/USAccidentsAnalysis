package com.dbms.usaccidents.usaccidentsanalysis.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherTrendDto {

    private LocationType location;
    private String locationValue;
    private String weatherCondition;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

}
