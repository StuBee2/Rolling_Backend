package com.stubee.rollingdomains.domain.company.services;

import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface UpdateCompanyListService {

    void updateAll(List<Company> companyList);

}