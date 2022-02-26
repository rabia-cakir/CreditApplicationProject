package com.project.backend.repository;

import com.project.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApplicationRepository extends JpaRepository<Application,Long> {
    //customer.identityNumber
    Application findByCustomerIdentityNumber(String identityNumber);
}
