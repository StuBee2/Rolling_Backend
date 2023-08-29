package com.stubee.companyapplication.outports;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.companyapplication.services.query.response.CompanyQueryResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueryCompanyPort extends CheckCompanyNameDuplicationPort {

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