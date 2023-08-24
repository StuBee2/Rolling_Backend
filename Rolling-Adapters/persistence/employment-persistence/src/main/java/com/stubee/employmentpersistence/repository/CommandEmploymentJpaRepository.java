package com.stubee.employmentpersistence.repository;

import com.stubee.persistencecommons.entity.EmploymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandEmploymentJpaRepository extends CrudRepository<EmploymentEntity, Long> {
}