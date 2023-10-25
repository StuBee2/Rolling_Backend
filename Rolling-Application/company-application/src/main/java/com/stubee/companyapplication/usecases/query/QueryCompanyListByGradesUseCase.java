package com.stubee.companyapplication.usecases.query;

import com.stubee.companyapplication.usecases.query.response.CompanyResponse;

import java.util.List;

public interface QueryCompanyListByGradesUseCase {

    List<CompanyResponse> get(String gradeType);

}