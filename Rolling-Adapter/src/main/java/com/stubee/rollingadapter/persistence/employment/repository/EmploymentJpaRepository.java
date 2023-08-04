package com.stubee.rollingadapter.persistence.employment.repository;

import com.stubee.rollingadapter.persistence.employment.entity.EmploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentJpaRepository extends JpaRepository<EmploymentEntity, Long> {
}