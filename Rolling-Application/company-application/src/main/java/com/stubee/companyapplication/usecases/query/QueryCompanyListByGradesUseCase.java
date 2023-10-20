package com.stubee.companyapplication.usecases.query;

import com.stubee.companyapplication.usecases.query.response.CompanyResponse;

import java.util.List;

public interface QueryCompanyListByGradesUseCase {

    List<CompanyResponse> getByTotalGrade();

    List<CompanyResponse> getBySalaryAndBenefits();

    List<CompanyResponse> getByWorkLifeBalance();

    List<CompanyResponse> getByOrganizationalCulture();

    List<CompanyResponse> getByCareerAdvancement();

}