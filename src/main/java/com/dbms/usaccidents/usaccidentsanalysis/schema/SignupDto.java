package com.dbms.usaccidents.usaccidentsanalysis.schema;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    private String loginid;
    private String firstname;
    private String lastname;
    private String password;
}
