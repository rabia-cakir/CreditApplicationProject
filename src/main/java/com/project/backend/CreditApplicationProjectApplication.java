package com.project.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class CreditApplicationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditApplicationProjectApplication.class, args);
    }

}
