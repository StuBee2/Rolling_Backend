package com.stubee.companyapplication.outports.query;

import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;

import java.util.UUID;

public interface CheckCompanyExistencePort {

    boolean check(UUID companyId);

    default void checkById(final UUID companyId) {
        if(check(companyId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }

}