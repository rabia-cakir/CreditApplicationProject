package com.project.backend.service;

import com.project.backend.dto.CustomerDto;
import com.project.backend.entity.Customer;
import com.project.backend.mapper.ICustomerMapper;
import com.project.backend.repository.ICustomerRepository;
import com.project.backend.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    ICustomerRepository customerRepository;

    @Mock
    ICustomerMapper customerMapper;

    @Mock
    IApplicationService applicationService;

    @InjectMocks
    CustomerServiceImpl customerService;


    @Test
    public void save(){
        //given
        Customer customer = Customer.builder()
                .id(1L)
                .identityNumber("11111111116")
                .firstName("First Name")
                .lastName("Last Name")
                .salary(new BigDecimal("6000"))
                .phoneNumber("5555555555")
                .build();

        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .identityNumber("11111111116")
                .firstName("First Name")
                .lastName("Last Name")
                .salary(new BigDecimal("6000"))
                .phoneNumber("5555555555")
                .build();

        when(customerMapper.mapFromCustomerDtoToCustomer(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.mapFromCustomerToCustomerDto(customer)).thenReturn(customerDto);

        //when
        CustomerDto actual = this.customerService.save(customerDto);

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getFirstName(), customerDto.getFirstName()),
                () -> assertEquals(actual, customerDto),
                () -> assertEquals(actual.getId(), customerDto.getId())
        );

    }

    @Test
    public void getAll(){
        //given
        List<Customer> customers = Arrays.asList(
                Customer.builder()
                        .id(1L)
                        .identityNumber("11111111116")
                        .firstName("First Name")
                        .lastName("Last Name")
                        .salary(new BigDecimal("6000"))
                        .phoneNumber("5555555555")
                        .build(),
                Customer.builder()
                        .id(2L)
                        .identityNumber("11111111112")
                        .firstName("First Name")
                        .lastName("Last Name")
                        .salary(new BigDecimal("5000"))
                        .phoneNumber("5555555550")
                        .build()
                );
        List<CustomerDto> customerDtos = Arrays.asList(
                CustomerDto.builder()
                        .id(1L)
                        .identityNumber("11111111116")
                        .firstName("First Name")
                        .lastName("Last Name")
                        .salary(new BigDecimal("6000"))
                        .phoneNumber("5555555555")
                        .build(),
                CustomerDto.builder()
                        .id(2L)
                        .identityNumber("11111111112")
                        .firstName("First Name")
                        .lastName("Last Name")
                        .salary(new BigDecimal("5000"))
                        .phoneNumber("5555555550")
                        .build()
        );

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.mapFromCustomersToCustomerDto(customers)).thenReturn(customerDtos);

        List<CustomerDto> actual = this.customerService.getAll();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual, customerDtos),
                () -> assertEquals(actual.size(), customerDtos.size())
        );

    }

    @Test
    public void get(){
        //given
        Customer customer = Customer.builder()
                .id(1L)
                .identityNumber("11111111116")
                .firstName("First Name")
                .lastName("Last Name")
                .salary(new BigDecimal("6000"))
                .phoneNumber("5555555555")
                .build();

        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .identityNumber("11111111116")
                .firstName("First Name")
                .lastName("Last Name")
                .salary(new BigDecimal("6000"))
                .phoneNumber("5555555555")
                .build();

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(customerMapper.mapFromCustomerToCustomerDto(customer)).thenReturn(customerDto);

        //when
        CustomerDto actual = this.customerService.get(1L);

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual, customerDto),
                () -> assertEquals(actual.getId(), customerDto.getId())
        );

    }

    @Test
    public void update(){
        //given
        Customer customer = Customer.builder()
                .id(1L)
                .identityNumber("11111111116")
                .firstName("First Name")
                .lastName("Last Name")
                .salary(new BigDecimal("6000"))
                .phoneNumber("5555555555")
                .build();

        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .identityNumber("11111111116")
                .firstName("First Name")
                .lastName("Last Name")
                .salary(new BigDecimal("6000"))
                .phoneNumber("5555555555")
                .build();


        when(customerMapper.mapFromCustomerDtoToCustomer(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.mapFromCustomerToCustomerDto(customer)).thenReturn(customerDto);

        //when
        CustomerDto actual = this.customerService.save(customerDto);

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getFirstName(), customerDto.getFirstName()),
                () -> assertEquals(actual, customerDto),
                () -> assertEquals(actual.getId(), customerDto.getId())
        );

    }
}
