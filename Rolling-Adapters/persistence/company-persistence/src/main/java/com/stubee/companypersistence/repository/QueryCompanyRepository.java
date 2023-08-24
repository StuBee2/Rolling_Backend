package com.stubee.companypersistence.repository;

import com.stubee.persistencecommons.entity.CompanyEntity;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.companyapplication.services.query.response.CompanyQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryCompanyRepository {

    boolean existsByCompanyId(UUID companyId);

    CompanyEntity findById(UUID id);

    CompanyQueryResponse findInfoById(UUID companyId);

    List<CompanyEntity> findByNameContaining(String companyName, PageRequest pageRequest);

    List<CompanyEntity> findByRegistrantId(UUID registrantId, PageRequest pageRequest);

    List<CompanyEntity> findAll(PageRequest pageRequest);

    List<CompanyEntity> findByTotalGrade();

    List<CompanyEntity> findBySalaryAndBenefits();

    List<CompanyEntity> findByWorkLifeBalance();

    List<CompanyEntity> findByOrganizationalCulture();

    List<CompanyEntity> findByCareerAdvancement();

}