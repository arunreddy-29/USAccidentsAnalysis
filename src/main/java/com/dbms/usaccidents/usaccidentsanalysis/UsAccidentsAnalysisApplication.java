package com.dbms.usaccidents.usaccidentsanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dbms.usaccidents.usaccidentsanalysis.repository")
@EntityScan
public class UsAccidentsAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsAccidentsAnalysisApplication.class, args);
    }

}
