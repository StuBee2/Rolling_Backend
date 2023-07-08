package com.stubee.rollingadapter.persistence.company.repository;

import com.stubee.rollingadapter.persistence.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommandCompanyJpaRepository extends JpaRepository<CompanyEntity, UUID> {
}