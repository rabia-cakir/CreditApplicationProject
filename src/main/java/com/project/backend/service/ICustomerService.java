package com.project.backend.service;

import com.project.backend.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    CustomerDto save(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto);
    void delete(long id);
    List<CustomerDto> getAll();
    CustomerDto get(long id);
}
