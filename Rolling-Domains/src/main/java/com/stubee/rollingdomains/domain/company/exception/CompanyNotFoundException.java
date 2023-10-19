package com.stubee.rollingdomains.domain.company.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class CompanyNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new CompanyNotFoundException();

    private CompanyNotFoundException() {
        super(ErrorCode.COMPANY_NOT_FOUND);
    }

}