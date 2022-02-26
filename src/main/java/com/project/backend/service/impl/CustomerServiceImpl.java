package com.project.backend.service.impl;

import com.project.backend.dto.CustomerDto;
import com.project.backend.entity.Customer;
import com.project.backend.exception.IdentityNumberIsAlreadyExistException;
import com.project.backend.exception.PhoneNumberIsAlreadyExistException;
import com.project.backend.mapper.ICustomerMapper;
import com.project.backend.repository.ICustomerRepository;
import com.project.backend.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(ICustomerRepository customerRepository, ICustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper=customerMapper;

    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        if (customerRepository.existsByIdentityNumber(customerDto.getIdentityNumber())) {
            log.error("CustomerServiceImpl : Identity number is already exist");
            throw new IdentityNumberIsAlreadyExistException("Identity number : " + customerDto.getIdentityNumber() + " is already exist");}
        if (customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber())) {
            log.error("CustomerServiceImpl: Phone number is already exist");
            throw new PhoneNumberIsAlreadyExistException("Phone number : "+customerDto.getPhoneNumber()+" is already exist");}

        Customer customer=customerMapper.mapFromCustomerDtoToCustomer(customerDto);
        Customer saveCustomer = customerRepository.save(customer);

            return customerMapper.mapFromCustomerToCustomerDto(saveCustomer);

    }
}
