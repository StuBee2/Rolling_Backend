package com.stubee.companyapplication.outports.query;

import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface QueryCompanyByGradesPort {

    List<Company> getOrderBy(String gradeType);

}