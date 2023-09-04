package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.CheckCompanyNameDuplicationPort;
import com.stubee.rollingdomains.domain.company.exception.DuplicatedCompanyNameException;
import com.stubee.rollingdomains.domain.company.services.CheckCompanyNameDuplicationService;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class CheckCompanyNameDuplicationServiceImpl implements CheckCompanyNameDuplicationService {

    private final CheckCompanyNameDuplicationPort checkCompanyNameDuplicationPort;

    public void check(final String companyName) {
        if(checkCompanyNameDuplicationPort.check(companyName)) {
            throw DuplicatedCompanyNameException.EXCEPTION;
        }
    }

}