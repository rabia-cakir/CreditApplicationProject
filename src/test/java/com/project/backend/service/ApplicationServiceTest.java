package com.project.backend.service;

import com.project.backend.dto.ApplicationDto;
import com.project.backend.dto.CustomerDto;
import com.project.backend.entity.Application;
import com.project.backend.entity.Customer;
import com.project.backend.enums.CreditResult;
import com.project.backend.mapper.IApplicationMapper;
import com.project.backend.repository.IApplicationRepository;
import com.project.backend.service.impl.ApplicationServiceImpl;
import com.project.backend.utils.services.ScoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    IApplicationRepository applicationRepository;

    @Mock
    IApplicationMapper applicationMapper;

    @Mock
    ScoreService scoreService;

    @InjectMocks
    ApplicationServiceImpl applicationService;


    @Test
    public void makeApplication(){
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

        Application application = Application.builder()
                .creditLimit(new BigDecimal("20000"))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customer)
                .build();

        ApplicationDto applicationDto = ApplicationDto.builder()
                .creditLimit(new BigDecimal("20000"))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto)
                .build();

        when(scoreService.getScore(customer.getIdentityNumber())).thenReturn(600);
        when(applicationRepository.save(application)).thenReturn(application);
        when(applicationMapper.mapFromApplicationToApplicationDto(application)).thenReturn(applicationDto);

        //when
        ApplicationDto actual = this.applicationService.makeApplication(customer);

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getCreditResult(), applicationDto.getCreditResult()),
                () -> assertEquals(actual.getCreditLimit(), applicationDto.getCreditLimit()),
                () -> assertEquals(actual.getCustomer(), applicationDto.getCustomer())
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

        Application application = Application.builder()
                .id(1L)
                .creditLimit(new BigDecimal("20000"))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customer)
                .build();

        ApplicationDto applicationDto = ApplicationDto.builder()
                .id(1L)
                .creditLimit(new BigDecimal("20000"))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto)
                .build();


        when(scoreService.getScore(customer.getIdentityNumber())).thenReturn(600);
        when(applicationRepository.save(application)).thenReturn(application);
        when(applicationMapper.mapFromApplicationToApplicationDto(application)).thenReturn(applicationDto);

        //when
        ApplicationDto actual = this.applicationService.update(customer, 1L);

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getCreditResult(), applicationDto.getCreditResult()),
                () -> assertEquals(actual.getCreditLimit(), applicationDto.getCreditLimit()),
                () -> assertEquals(actual.getCustomer(), applicationDto.getCustomer())
        );

    }

    @Test
    public void getStatus(){

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

        Application application = Application.builder()
                .id(1L)
                .creditLimit(new BigDecimal("20000"))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customer)
                .build();

        ApplicationDto applicationDto = ApplicationDto.builder()
                .id(1L)
                .creditLimit(new BigDecimal("20000"))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto)
                .build();


        when(applicationRepository.findByCustomerIdentityNumber(customer.getIdentityNumber())).thenReturn(application);
        when(applicationMapper.mapFromApplicationToApplicationDto(application)).thenReturn(applicationDto);

        //when
        ApplicationDto actual = this.applicationService.getStatus("11111111116");

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getCustomer(), applicationDto.getCustomer()),
                () -> assertEquals(actual, applicationDto)
        );

    }

    @Test
    public void getAll(){
        //given
        List<Application> applications = Arrays.asList(
                Application.builder()
                        .id(1L)
                        .creditLimit(new BigDecimal("20000"))
                        .creditResult(CreditResult.CONFIRMED)
                        .customer(new Customer()).build(),
                Application.builder()
                        .id(2L)
                        .creditLimit(new BigDecimal("10000"))
                        .creditResult(CreditResult.CONFIRMED)
                        .customer(new Customer()).build());


        List<ApplicationDto> applicationDtos = Arrays.asList(
                ApplicationDto.builder()
                        .id(1L)
                        .creditLimit(new BigDecimal("20000"))
                        .creditResult(CreditResult.CONFIRMED)
                        .customer(new CustomerDto())
                        .build(),
                ApplicationDto
                        .builder()
                        .id(2L)
                        .creditLimit(new BigDecimal("10000"))
                        .creditResult(CreditResult.CONFIRMED)
                        .customer(new CustomerDto())
                        .build());

        when(applicationRepository.findAll()).thenReturn(applications);
        when(applicationMapper.mapFromApplicationsToApplicationDto(applications)).thenReturn(applicationDtos);

        //when
        List<ApplicationDto> actual = this.applicationService.getAll();

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual, applicationDtos),
                () -> assertEquals(actual.size(), applicationDtos.size())
        );

    }


}
