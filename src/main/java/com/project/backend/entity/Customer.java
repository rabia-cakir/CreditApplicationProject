package com.project.backend.entity;

import com.project.backend.entity.base.BaseEntityAudit;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "customers")
public class Customer extends BaseEntityAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "identity_number", nullable = false, length = 11)
    private String identityNumber;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "salary", nullable = false, precision=6, scale=0)
    private BigDecimal salary;

    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Application application;

}
