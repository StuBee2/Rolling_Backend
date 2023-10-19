package com.stubee.companypersistence.repository;

import com.stubee.persistencecommons.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandCompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {
}