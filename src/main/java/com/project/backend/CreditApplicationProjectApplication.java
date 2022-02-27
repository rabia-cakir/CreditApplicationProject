package com.project.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableWebSecurity
@SpringBootApplication
@EnableSwagger2
public class CreditApplicationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditApplicationProjectApplication.class, args);
    }

}
