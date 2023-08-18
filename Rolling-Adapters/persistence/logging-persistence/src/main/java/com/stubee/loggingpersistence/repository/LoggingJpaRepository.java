package com.stubee.loggingpersistence.repository;

import com.stubee.persistencecommons.commons.entity.LoggingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingJpaRepository extends CrudRepository<LoggingEntity, Long> {
}