package com.stubee.companyapplication.outports.query;

import com.stubee.rollingdomains.domain.company.exception.DuplicatedCompanyNameException;

public interface CheckCompanyNameDuplicationPort {

    boolean check(String companyName);

    default void checkByName(final String companyName) {
        if(check(companyName)) {
            throw DuplicatedCompanyNameException.EXCEPTION;
        }
    }

}