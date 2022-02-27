package com.project.backend.repository;

import com.project.backend.entity.AppError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppErrorRepository extends JpaRepository<AppError,Long> {

}
