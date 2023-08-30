package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.CheckCompanyNameDuplicationPort;
import com.stubee.companyshared.services.CheckCompanyNameDuplicationService;
import com.stubee.rollingdomains.domain.company.exception.DuplicatedCompanyNameException;
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