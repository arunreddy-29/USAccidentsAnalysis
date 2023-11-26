package com.dbms.usaccidents.usaccidentsanalysis.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarylandViolationResultDto {

    private String monthYear;
    private Long countFromMaryland;
    private Long countFromAccident;
}
