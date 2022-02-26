package com.project.backend.service.impl;

import com.project.backend.dto.CustomerDto;
import com.project.backend.exception.IdentityNumberIsAlreadyExistException;
import com.project.backend.exception.PhoneNumberIsAlreadyExistException;
import com.project.backend.repository.ICustomerRepository;
import com.project.backend.service.ICustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;


    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        if (customerRepository.existsByIdentityNumber(customerDto.getIdentityNumber())) {
            throw new IdentityNumberIsAlreadyExistException("Identity number : " + customerDto.getIdentityNumber() + " is already exist");}
        if (customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber())) {
            throw new PhoneNumberIsAlreadyExistException("Phone number : "+customerDto.getPhoneNumber()+" is already exist");}




    }
}
