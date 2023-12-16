package com.stubee.employmentpersistence.adapters;

import com.stubee.persistencecommons.entity.EmploymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CommandEmploymentJpaRepository extends CrudRepository<EmploymentEntity, Long> {
}