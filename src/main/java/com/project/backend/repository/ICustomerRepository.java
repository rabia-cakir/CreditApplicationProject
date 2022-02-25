package com.project.backend.repository;

import com.project.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Id> {
}
