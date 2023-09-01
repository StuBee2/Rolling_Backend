package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.CheckCompanyExistencePort;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.services.CheckCompanyExistenceService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class CheckCompanyExistenceServiceImpl implements CheckCompanyExistenceService {

    private final CheckCompanyExistencePort checkCompanyExistencePort;

    @Override
    public void check(final UUID companyId) {
        if(!checkCompanyExistencePort.check(companyId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }

}