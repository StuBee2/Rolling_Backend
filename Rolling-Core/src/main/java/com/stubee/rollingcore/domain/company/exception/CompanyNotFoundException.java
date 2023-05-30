package com.stubee.rollingcore.domain.company.exception;

import com.stubee.rollingcore.common.exception.CustomException;

public class CompanyNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new CompanyNotFoundException();

    private CompanyNotFoundException() {
        super(404, "Company Not Found");
    }

}