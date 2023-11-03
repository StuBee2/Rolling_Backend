package com.stubee.companyapplication.usecases.query;

import java.util.List;

public interface QueryCompanyListByGradesUseCase {

    List<CompanyResponse> get(String gradeType);

}