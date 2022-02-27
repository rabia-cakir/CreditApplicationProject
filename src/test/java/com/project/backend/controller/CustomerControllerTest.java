package com.project.backend.controller;

import com.project.backend.dto.CustomerDto;
import com.project.backend.service.ICustomerService;
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
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    ICustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Test
    public void save()
    {
        //given
        CustomerDto customerDto= CustomerDto.builder()
                .id(1L)
                .identityNumber("11111111110")
                .firstName("first name")
                .lastName("last name")
                .salary(new BigDecimal(5000))
                .phoneNumber("5555555555")
                .build();

        when(customerService.save(customerDto)).thenReturn(customerDto);

        //when
        ResponseEntity<CustomerDto> actual=customerController.save(customerDto);

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK),
                () -> assertEquals(actual.getBody().getFirstName(),customerDto.getFirstName())
        );
    }

    @Test
    public void getAll()
    {
        //given
        List<CustomerDto> customerDtos= Arrays.asList(
                CustomerDto.builder()
                        .id(1L)
                        .identityNumber("11111111110")
                        .firstName("first name")
                        .lastName("last name")
                        .salary(new BigDecimal(6000))
                        .phoneNumber("5555555555")
                        .build(),
                CustomerDto.builder()
                        .id(1L)
                        .identityNumber("11111111112")
                        .firstName("first name")
                        .lastName("last name")
                        .salary(new BigDecimal(7000))
                        .phoneNumber("5555555552")
                        .build());
        when(customerService.getAll()).thenReturn(customerDtos);

        //when
        ResponseEntity<List<CustomerDto>> actual=customerController.getAll();

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK),
                () -> assertEquals(actual.getBody().size(),customerDtos.size())
        );
    }

}
