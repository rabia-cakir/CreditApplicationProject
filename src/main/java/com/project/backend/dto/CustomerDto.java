package com.project.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;
import java.math.BigDecimal;


@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Details about the customer")

public class CustomerDto {
    private long id;

    @ApiModelProperty(notes = "The unique identity number of the customer")
    @NotBlank(message = "Identity Number is mandatory")
    @Pattern(regexp="(^[0-9]{11}$)", message = "Identity Number must be 11 characters")
    @Pattern(regexp="(^\\d*[02468]$)", message = "Identity Number must be of digits and the last character must be an even number")
    private String identityNumber;

    @ApiModelProperty(notes = "The customer's first name")
    @NotBlank(message = "First Name is mandatory")
    @Size(min = 2, max = 50, message = "First Name must be between 2 and 50 characters")
    @Pattern(regexp="(^[a-zA-Z]{2,50}$)", message = "First Name must be of characters")
    private String firstName;

    @ApiModelProperty(notes = "The customer's last name")
    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 2, max = 50, message = "Last Name must be between 2 and 50 characters")
    @Pattern(regexp="(^[a-zA-Z]{2,50}$)", message = "Last Name must be of characters")
    private String lastName;

    @ApiModelProperty(notes = "The customer's salary")
    @DecimalMax(value = "999999", message = "salary must be 999999 at most")
    @DecimalMin(value = "3000", message = "salary must be at least 3000")
    @Digits(integer=6, fraction=0)
    private BigDecimal salary;

    @ApiModelProperty(notes = "The customer's phone number")
    @NotBlank(message = "Phone Number is mandatory")
    @Pattern(regexp="(^[0-9]{10}$)", message = "Phone Number must be of digits and 10 characters")
    private String phoneNumber;

}
