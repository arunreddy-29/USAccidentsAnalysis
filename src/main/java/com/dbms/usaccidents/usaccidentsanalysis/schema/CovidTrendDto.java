package com.dbms.usaccidents.usaccidentsanalysis.schema;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidTrendDto {
    private LocalDate fromDate;
    private LocalDate toDate;
}
