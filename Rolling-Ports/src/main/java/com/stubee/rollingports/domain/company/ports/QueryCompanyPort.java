package com.stubee.rollingports.domain.company.ports;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.response.CompanyQueryResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueryCompanyPort {

    boolean existsByCompanyId(UUID companyId);

    Optional<Company> findById(UUID id);

    Optional<CompanyQueryResponse> findInfoById(UUID id);

    List<Company> findByNameContaining(String name, PageRequest pageRequest);

    List<Company> findByRegistrantId(UUID registrantId, PageRequest pageRequest);

    List<Company> findAll(PageRequest pageRequest);

    List<Company> findByTotalGrade();

    List<Company> findBySalaryAndBenefits();

    List<Company> findByWorkLifeBalance();

    List<Company> findByOrganizationalCulture();

    List<Company> findByCareerAdvancement();

}