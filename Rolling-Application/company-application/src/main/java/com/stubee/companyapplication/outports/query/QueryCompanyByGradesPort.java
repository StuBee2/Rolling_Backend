package com.stubee.companyapplication.outports.query;

import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface QueryCompanyByGradesPort {

    List<Company> getByTotalGrade();

    List<Company> getBySalaryAndBenefits();

    List<Company> getByWorkLifeBalance();

    List<Company> getByOrganizationalCulture();

    List<Company> getByCareerAdvancement();

}