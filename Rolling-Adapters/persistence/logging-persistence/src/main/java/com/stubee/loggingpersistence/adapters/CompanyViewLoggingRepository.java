package com.stubee.loggingpersistence.adapters;

import com.stubee.persistencecommons.entity.CompanyViewLoggingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CompanyViewLoggingRepository extends JpaRepository<CompanyViewLoggingEntity, Long> {
}