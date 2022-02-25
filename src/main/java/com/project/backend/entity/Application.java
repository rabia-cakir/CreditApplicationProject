package com.project.backend.entity;
import com.project.backend.entity.base.BaseEntityAudit;
import com.project.backend.enums.CreditResult;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name="applications")
public class Application extends BaseEntityAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_result")
    private CreditResult creditResult;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(
            name="customer_id",
            referencedColumnName = "id"
    )
    private Customer customer;

}
