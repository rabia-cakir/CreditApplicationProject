package com.project.backend.controller;

import com.project.backend.dto.CustomerDto;
import com.project.backend.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll()
    {
        return new ResponseEntity<>(customerService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable("id") long id)
    {
        return new ResponseEntity<>(customerService.get(id), HttpStatus.OK);
    }
}
