package com.stubee.loggingpersistence.repository;

import com.stubee.persistencecommons.entity.CompanyViewLoggingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyViewLoggingRepository extends JpaRepository<CompanyViewLoggingEntity, Long> {
}