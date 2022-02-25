package com.project.backend.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_date,update_date"}, allowGetters = true)
public class BaseEntityAudit {

    @Column(name = "created_date",nullable = false,updatable = false)
    @CreatedDate
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    @Column(name = "update_date",nullable = false)
    //Temporal types are the set of time-based types that can be used in persistent state mappings.
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateDate;
}