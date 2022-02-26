package com.project.backend.service.impl;

import com.project.backend.dto.CustomerDto;
import com.project.backend.entity.Customer;
import com.project.backend.exception.IdentityNumberIsAlreadyExistException;
import com.project.backend.exception.PhoneNumberIsAlreadyExistException;
import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.mapper.ICustomerMapper;
import com.project.backend.repository.ICustomerRepository;
import com.project.backend.service.IApplicationService;
import com.project.backend.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;
    private final IApplicationService applicationService;

    @Autowired
    public CustomerServiceImpl(ICustomerRepository customerRepository, ICustomerMapper customerMapper, IApplicationService applicationService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.applicationService = applicationService;

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
        applicationService.makeApplication(customer);
        log.info("CustomerServiceImpl : credit application has been completed");
        return customerMapper.mapFromCustomerToCustomerDto(saveCustomer);

    }

    @Override
    public CustomerDto get(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with id: %d not found.", id)));
        log.error("customer is not found");
        customerMapper.mapFromCustomerToCustomerDto(customer);
        return customerMapper.mapFromCustomerToCustomerDto(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<CustomerDto> getAll() {
        return customerMapper.mapFromCustomersToCustomerDto(customerRepository.findAll());
    }

}
