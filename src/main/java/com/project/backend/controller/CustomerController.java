package com.project.backend.controller;

import com.project.backend.dto.CustomerDto;
import com.project.backend.service.ICustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    @ApiOperation(value = "Create customer.")
    @PostMapping
    public ResponseEntity<CustomerDto> save(@RequestBody @Valid CustomerDto customerDto) {
        log.info("CustomerController: Request to the CustomerService to save customer");
        return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.OK);
    }


    @ApiOperation(value = "List all customers.")
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        log.info("CustomerController: Request to the CustomerService to list all customers");
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Get customer by id.")
    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable("id") long id) {
        log.info("CustomerController: Request to the CustomerService to fetch customer with the specific id");
        return new ResponseEntity<>(customerService.get(id), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete customer.")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        log.info("CustomerController: Request to the CustomerService to delete customer with the specific id");
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "Update customer.")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@RequestBody @Valid CustomerDto customerDto, @PathVariable("id") long id) {
        customerDto.setId(id);
        log.info("CustomerController: Request to the CustomerService to update customer with the specific id");
        return new ResponseEntity<>(customerService.update(customerDto), HttpStatus.OK);
    }
}
