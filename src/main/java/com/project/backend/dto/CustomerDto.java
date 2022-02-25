package com.project.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class CustomerDto {
    private long id;
    private String identityNumber;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String phoneNumber;

}
