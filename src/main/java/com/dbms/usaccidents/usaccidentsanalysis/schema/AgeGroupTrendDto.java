package com.dbms.usaccidents.usaccidentsanalysis.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgeGroupTrendDto {

    private int fromAge;
    private int toAge;
    private int timeOfDay;
    private String fromDate;
    private String toDate;
}
