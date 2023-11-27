package com.dbms.usaccidents.usaccidentsanalysis.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgeGroupTrendDto {

    private int fromAge;
    private int toAge;
    private int timeOfDay;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
