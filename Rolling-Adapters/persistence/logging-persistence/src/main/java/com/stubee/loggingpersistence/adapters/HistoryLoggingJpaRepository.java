package com.stubee.loggingpersistence.adapters;

import com.stubee.persistencecommons.entity.HistoryLoggingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface HistoryLoggingJpaRepository extends CrudRepository<HistoryLoggingEntity, Long> {
}