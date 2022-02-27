package com.project.backend.dto;

import com.project.backend.enums.CreditResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Details about the application")
public class ApplicationDto {

    @ApiModelProperty(notes="The unique id of the application")
    private long id;

    @ApiModelProperty(notes="The credit limit given to the customer")
    private BigDecimal creditLimit;

    @ApiModelProperty(notes="The result of the credit applied for")
    private CreditResult creditResult;

    @ApiModelProperty(notes="The information about customer")
    private CustomerDto customer;
}