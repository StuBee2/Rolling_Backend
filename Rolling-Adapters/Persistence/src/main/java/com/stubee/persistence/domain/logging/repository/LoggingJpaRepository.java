package com.stubee.persistence.domain.logging.repository;

import com.stubee.persistence.domain.logging.entity.LoggingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingJpaRepository extends CrudRepository<LoggingEntity, Long> {
}