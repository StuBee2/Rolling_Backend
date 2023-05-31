package com.stubee.rollingapplication.domain.company.port.spi;

import com.stubee.rollingcore.common.dto.PageDto;
import com.stubee.rollingcore.domain.company.dto.response.CompanyQueryResponse;
import com.stubee.rollingcore.domain.company.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueryCompanyPort {

    boolean existsByCompanyId(UUID companyId);

    Optional<CompanyQueryResponse> findById(UUID id);

    List<Company> findByNameContaining(String name, PageDto pageDto);

    List<Company> findByRegistrantId(UUID registrantId);

    List<Company> findAll();

    List<Company> findByTotalGrade();

    List<Company> findBySalaryGrade();

    List<Company> findByWelfareGrade();

    List<Company> findByBalanceGrade();

}