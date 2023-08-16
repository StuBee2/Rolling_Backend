package com.stubee.persistence.domain.company.repository;

import com.stubee.persistence.domain.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommandCompanyJpaRepository extends JpaRepository<CompanyEntity, UUID> {
}