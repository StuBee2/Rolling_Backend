package com.stubee.companypersistence.adapters;

import com.stubee.persistencecommons.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CommandCompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {
}