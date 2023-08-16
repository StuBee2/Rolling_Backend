package com.stubee.persistence.domain.employment.repository;

import com.stubee.persistence.domain.employment.entity.EmploymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandEmploymentJpaRepository extends CrudRepository<EmploymentEntity, Long> {
}