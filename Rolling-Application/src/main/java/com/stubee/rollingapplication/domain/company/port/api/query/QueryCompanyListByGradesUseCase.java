package com.stubee.rollingapplication.domain.company.port.api.query;

import com.stubee.rollingcore.domain.company.model.Company;

import java.util.List;

public interface QueryCompanyListByGradesUseCase {

    List<Company> getByTotalGrade();

    List<Company> getBySalaryAndBenefits();

    List<Company> getByWorkLifeBalance();

    List<Company> getByOrganizationalCulture();

    List<Company> getByCareerAdvancement();

}