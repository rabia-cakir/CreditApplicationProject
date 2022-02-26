package com.project.backend.service;

import com.project.backend.dto.CustomerDto;

public interface ICustomerService {

    CustomerDto save(CustomerDto customerDto);
}
