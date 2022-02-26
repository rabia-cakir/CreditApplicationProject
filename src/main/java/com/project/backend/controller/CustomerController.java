package com.project.backend.controller;

import com.project.backend.dto.CustomerDto;
import com.project.backend.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> save(@RequestBody @Valid CustomerDto customerDto)
    {
        log.info("Controller: Request to the CustomerService to save customer");
        return new ResponseEntity<>(customerService.save(customerDto),HttpStatus.OK);
    }
}
