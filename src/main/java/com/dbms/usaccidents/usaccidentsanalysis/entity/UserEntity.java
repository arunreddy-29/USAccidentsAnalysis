package com.dbms.usaccidents.usaccidentsanalysis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    private Long id;
    private String loginid;
    private String firstname;
    private String lastname;
    private String password;

}
