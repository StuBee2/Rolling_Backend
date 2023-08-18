package com.stubee.companypersistence.repository;

import com.stubee.persistencecommons.commons.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommandCompanyJpaRepository extends JpaRepository<CompanyEntity, UUID> {
}