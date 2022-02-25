package com.project.backend.entity;

import com.project.backend.entity.base.BaseEntityAudit;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "app_error")
public class AppError extends BaseEntityAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private int status;

    @Column(name = "message")
    private String message;

    @Column(name = "timestamp")
    private long timestamp;

}
