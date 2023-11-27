package com.dbms.usaccidents.usaccidentsanalysis.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarylandViolationResultDto {

    private List<String> monthYear;
    private List<Integer> countFromMaryland;
    private List<Integer> countFromAccident;
}
