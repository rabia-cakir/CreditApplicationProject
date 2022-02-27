package com.project.backend.config.swagger;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        //return a prepared docket instance
        //this docket instance manipulated by following a builder pattern
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        //we create this object instance with all the API information
        return new ApiInfo(
                "Credit Application System",
                "This is a rest api for a credit application system.",
                "1.0.0",
                "Free to use",
                new Contact("Rabia Çakır", "https://github.com/rabia-cakir", "rabiacakir0681@gmail.com"),
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0.html",
                Collections.emptyList());
    }
}
