package com.stubee.loggingpersistence.repository;

import com.stubee.persistencecommons.entity.HistoryLoggingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryLoggingJpaRepository extends CrudRepository<HistoryLoggingEntity, Long> {
}