package com.project.backend.controller;

import com.project.backend.dto.CustomerDto;
import com.project.backend.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {


    private final ICustomerService customerService;


    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto)
    {
        return new ResponseEntity<>(customerService.save(customerDto),HttpStatus.OK);
    }
}
