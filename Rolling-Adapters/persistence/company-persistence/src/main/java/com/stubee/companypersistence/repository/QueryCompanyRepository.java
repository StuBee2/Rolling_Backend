package com.stubee.companypersistence.repository;

import com.stubee.persistencecommons.entity.CompanyEntity;
import com.stubee.companyapplication.usecases.query.CompanyQueryResponse;
import com.stubee.rollingdomains.common.model.dtos.request.PageRequest;

import java.util.List;

public interface QueryCompanyRepository {

    boolean existsByCompanyId(Long companyId);

    boolean existsByCompanyName(String companyName);

    CompanyEntity findById(Long id);

    CompanyQueryResponse findInfoById(Long companyId);

    List<CompanyEntity> findByNameContaining(String companyName, PageRequest pageRequest);

    List<CompanyEntity> findByRegistrantId(Long registrantId, PageRequest pageRequest);

    List<CompanyEntity> findAll(PageRequest pageRequest);

    List<CompanyEntity> findByTotalGrade();

    List<CompanyEntity> findBySalaryAndBenefits();

    List<CompanyEntity> findByWorkLifeBalance();

    List<CompanyEntity> findByOrganizationalCulture();

    List<CompanyEntity> findByCareerAdvancement();

}