package com.project.backend.dto;

import com.project.backend.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ApplicationDto {
    private long id;
    private BigDecimal creditLimit;
    private CreditResult creditResult;
    private CustomerDto customer;

}
