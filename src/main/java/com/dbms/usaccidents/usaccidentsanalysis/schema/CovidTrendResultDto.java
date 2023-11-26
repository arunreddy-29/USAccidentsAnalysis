package com.dbms.usaccidents.usaccidentsanalysis.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidTrendResultDto {

    private String monthYear;
    private Long accidentCount;
    private Long covidCaseCount;
}
