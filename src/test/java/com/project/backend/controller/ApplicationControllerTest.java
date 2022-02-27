package com.project.backend.controller;

import com.project.backend.dto.ApplicationDto;
import com.project.backend.dto.CustomerDto;
import com.project.backend.enums.CreditResult;
import com.project.backend.service.IApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

//MockitoExtension initializes mocks and handles strict stubbings.
@ExtendWith(MockitoExtension.class)
class ApplicationControllerTest {

    //uses @Mock annotation to created mock object for ApplicationService dependency.
    @Mock
    IApplicationService applicationService;

    //uses @InjectMocks to create applicationController class and also inject the mocked applicationService instance
    @InjectMocks
    ApplicationController applicationController;

    @Test
    public void getAll(){
        //given
        List<ApplicationDto> applicationDtos = Arrays.asList(
                ApplicationDto.builder()
                        .id(1l)
                        .creditLimit(new BigDecimal("20000"))
                        .creditResult(CreditResult.CONFIRMED)
                        .customer(new CustomerDto())
                        .build(),
                ApplicationDto.builder()
                        .id(2l)
                        .creditLimit(new BigDecimal("0"))
                        .creditResult(CreditResult.UNCONFIRMED)
                        .customer(new CustomerDto())
                        .build());

        when(applicationService.getAll()).thenReturn(applicationDtos);

        //when
        ResponseEntity<List<ApplicationDto>> actual = applicationController.getAll();

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK),
                () -> assertEquals(actual.getBody().size(), applicationDtos.size())
        );

    }

    @Test
    public void getStatus(){
        //given
        ApplicationDto applicationDto = ApplicationDto.builder()
                .id(1l)
                .creditLimit(new BigDecimal("10000"))
                .creditResult(CreditResult.CONFIRMED)
                .customer(new CustomerDto())
                .build();

        when(applicationService.getStatus(anyString())).thenReturn(applicationDto);

        //when
        ResponseEntity<ApplicationDto> actual = applicationController.getStatus(anyString());

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK),
                () -> assertEquals(actual.getBody().getCustomer(), applicationDto.getCustomer())
        );

    }
}