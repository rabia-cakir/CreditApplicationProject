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
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;
    private final IApplicationService applicationService;

    public CustomerServiceImpl(ICustomerRepository customerRepository, ICustomerMapper customerMapper, IApplicationService applicationService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.applicationService = applicationService;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        //daha önce bu identity no ile kayıt yapılmış mı
        if(customerRepository.existsByIdentityNumber(customerDto.getIdentityNumber())){
            throw new IdentityNumberIsAlreadyExistException("Identity Number with " + customerDto.getIdentityNumber() + " is already exist.");
        }

        //daha önce bu numarayla kayıt yapılmış mı
        if(customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber())){
            throw new PhoneNumberIsAlreadyExistException("Phone Number with " + customerDto.getPhoneNumber() + " is already exist.");
        }

        //customer dto entity'e dönüştü
        Customer customer = customerMapper.mapFromCustomerDtoToCustomer(customerDto);

        //customer veritabanına kaydedildi.
        Customer saveCustomer = customerRepository.save(customer);
        log.info("Service: Application has been created.");

        //kredi başvurusunda bulunma
        applicationService.makeApplication(customer);

        log.info("Service: Sms sending has been completed.");
        //entity'i dto'ya dönüştürme
        return customerMapper.mapFromCustomerToCustomerDto(saveCustomer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        Customer controlCustomer = customerRepository.findById(customerDto.getId()).get();
        if(!controlCustomer.getIdentityNumber().equals(customerDto.getIdentityNumber())){
            if(customerRepository.existsByIdentityNumber(customerDto.getIdentityNumber())){
                throw new IdentityNumberIsAlreadyExistException("Identity Number with " + customerDto.getIdentityNumber() + " is already exist.");
            }
        }
        if(!controlCustomer.getPhoneNumber().equals(customerDto.getPhoneNumber())) {
            if (customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber())) {
                throw new PhoneNumberIsAlreadyExistException("Phone Number with " + customerDto.getPhoneNumber() + " is already exist.");
            }
        }
        Customer customer = customerMapper.mapFromCustomerDtoToCustomer(customerDto);
        Customer updateCustomer = customerRepository.save(customer);
        applicationService.update(customer, customerRepository.findByCustomerApplicationId(customer.getId()));
        log.info("Service: Application update handle has been completed");
        return customerMapper.mapFromCustomerToCustomerDto(updateCustomer);
    }

    @Override
    public void delete(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerMapper.mapFromCustomersToCustomerDto(customerRepository.findAll());
    }

    @Override
    public CustomerDto get(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with id: %d not found.", id)));
        return customerMapper.mapFromCustomerToCustomerDto(customer);
    }
}
