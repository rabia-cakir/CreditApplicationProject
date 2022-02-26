package com.project.backend.repository;

import com.project.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    //JPQL
    @Query("SELECT a.id FROM Application a WHERE a.customer.id = :id")
    long findByCustomerApplicationId(long id);

    boolean existsByIdentityNumber(String identityNumber);
    boolean existsByPhoneNumber(String phoneNumber);


}
