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
    //private final NotificationService notificationService;

    public ApplicationServiceImpl(IApplicationRepository applicationRepository, IApplicationMapper applicationMapper,
                                  ScoreService scoreService) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.scoreService = scoreService;

    }


    @Override
    public ApplicationDto makeApplication(Customer customer) {
        Pair<BigDecimal, CreditResult> result = applicationResult(scoreService.getScore(customer.getIdentityNumber()), customer.getSalary());
        Application application = Application.builder().customer(customer).creditLimit(result.getValue0()).creditResult(result.getValue1()).build();
        return applicationMapper.mapFromApplicationToApplicationDto(applicationRepository.save(application));
    }

    @Override
    public ApplicationDto update(Customer customer, long applicationId) {
        Pair<BigDecimal, CreditResult> result = applicationResult(scoreService.getScore(customer.getIdentityNumber()), customer.getSalary());
        Application application = Application.builder().customer(customer).creditLimit(result.getValue0()).creditResult(result.getValue1()).build();Boolean status = (application.getCreditResult() == CreditResult.CONFIRMED);
        application.setId(applicationId);
        log.info("Service: Sms sending has been completed.");
        return applicationMapper.mapFromApplicationToApplicationDto(applicationRepository.save(application));
    }

    @Override
    public List<ApplicationDto> getAll() {
        return applicationMapper.mapFromApplicationsToApplicationDto(applicationRepository.findAll());
    }

    @Override
    public ApplicationDto getStatus(String identityNumber) {

        //application: o identy number'a sahip müşterinin application'ı
        Application application = applicationRepository.findByCustomerIdentityNumber(identityNumber);
        //o müşteri krediye başvurmuş mu gerçekten
        if(Objects.isNull(application)){
            throw new ResourceNotFoundException("Application with identity number: " + identityNumber + " not found.");
        }
        //entity'i dto'ya çeviriyorum
        return applicationMapper.mapFromApplicationToApplicationDto(application);
    }

    @Override
    public Pair<BigDecimal, CreditResult> applicationResult(int score, BigDecimal salary) {
        //Kredi skoru 500’ün altında ise kullanıcı reddedilir
        if(score < 500){
            return new Pair<>(BigDecimal.valueOf(0), CreditResult.UNCONFIRMED);
            //Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL’nin altında ise

        }else if(score >= 500 && score < 1000 && salary.intValue() <= 5000){
            return new Pair<>(BigDecimal.valueOf(10000), CreditResult.CONFIRMED);

            //Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL’nin üstünde ise
        }else if(score >= 500 && score < 1000 && salary.intValue() > 5000){
            return new Pair<>(BigDecimal.valueOf(20000), CreditResult.CONFIRMED);

            //Kredi skoru 1000 puana eşit veya üzerinde ise
        }else if(score >= 1000){
            return new Pair<>((salary.multiply(BigDecimal.valueOf(AppRule.CREDIT_LIMIT_MULTIPLIER))), CreditResult.CONFIRMED);
        }
        return new Pair<>(BigDecimal.valueOf(0), CreditResult.UNCONFIRMED);
    }

}
