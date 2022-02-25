package com.project.backend.controller;

import com.project.backend.service.ICustomerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {


    private final ICustomerService customerService;


    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
