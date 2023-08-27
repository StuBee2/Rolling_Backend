package com.stubee.applicationcommons.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.company.CheckCompanyExistencePort;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class CheckCompanyExistenceService {

    private final CheckCompanyExistencePort checkCompanyExistencePort;

    public void check(final UUID companyId) {
        if(checkCompanyExistencePort.check(companyId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }

}