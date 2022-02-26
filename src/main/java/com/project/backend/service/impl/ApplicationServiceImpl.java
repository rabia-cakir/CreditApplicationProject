package com.project.backend.service.impl;

import com.project.backend.dto.ApplicationDto;
import com.project.backend.entity.Application;
import com.project.backend.entity.Customer;
import com.project.backend.enums.CreditResult;
import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.mapper.IApplicationMapper;
import com.project.backend.repository.IApplicationRepository;
import com.project.backend.service.IApplicationService;
import com.project.backend.utils.rules.AppRule;
import com.project.backend.utils.services.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ApplicationServiceImpl implements IApplicationService {

    private final IApplicationRepository applicationRepository;
    private final IApplicationMapper applicationMapper;
    private final ScoreService scoreService;

    @Autowired
    public ApplicationServiceImpl(IApplicationRepository applicationRepository, IApplicationMapper applicationMapper, ScoreService scoreService) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.scoreService = scoreService;
    }

    @Override
    public ApplicationDto makeApplication(Customer customer) {
        Pair<BigDecimal,CreditResult> result=applicationResult(scoreService.getScore(customer.getIdentityNumber()),customer.getSalary());
        log.info("ApplicationServiceImpl : credit application has been made");
        Application application=Application.builder()
                .creditLimit(result.getValue0())
                .creditResult(result.getValue1())
                .build();

        return applicationMapper.mapFromApplicationToApplicationDto(applicationRepository.save(application));

    }

    @Override
    public Pair<BigDecimal, CreditResult> applicationResult(int score, BigDecimal salary) {
        if(score < 500){
            return new Pair<>(BigDecimal.valueOf(0), CreditResult.UNCONFIRMED);
        }
        else if(score >= 500 && score < 1000 && salary.intValue() <= 5000){
            return new Pair<>(BigDecimal.valueOf(10000), CreditResult.CONFIRMED);

        }
        else if(score >= 500 && score < 1000 && salary.intValue() > 5000){
            return new Pair<>(BigDecimal.valueOf(20000), CreditResult.CONFIRMED);
        }
        else if(score >= 1000){
            return new Pair<>((salary.multiply(BigDecimal.valueOf(AppRule.CREDIT_LIMIT_MULTIPLIER))), CreditResult.CONFIRMED);
        }
            return new Pair<>(BigDecimal.valueOf(0), CreditResult.UNCONFIRMED);
    }

    @Override
    public ApplicationDto getStatus(String identityNumber) {
        Application application=applicationRepository.findByCustomerIdentityNumber(identityNumber);
        if(Objects.isNull(application)){
            log.error("Application is not found");
            throw new ResourceNotFoundException("Application with identity number: " + identityNumber + " not found.");
        }
        return applicationMapper.mapFromApplicationToApplicationDto(application);
    }

    @Override
    public List<ApplicationDto> getAll() {
        return applicationMapper.mapFromApplicationsToApplicationDto(applicationRepository.findAll());
    }


}
