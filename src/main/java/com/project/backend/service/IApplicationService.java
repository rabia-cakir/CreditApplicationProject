package com.project.backend.service;

import com.project.backend.dto.ApplicationDto;
import com.project.backend.entity.Customer;
import com.project.backend.enums.CreditResult;
import org.javatuples.Pair;

import java.math.BigDecimal;
import java.util.List;

public interface IApplicationService {
    ApplicationDto makeApplication(Customer customer);
    public Pair<BigDecimal, CreditResult> applicationResult(int score, BigDecimal salary);
    ApplicationDto getStatus(String identityNumber);
    List<ApplicationDto> getAll();
    ApplicationDto update(Customer customer, long applicationId);
}
