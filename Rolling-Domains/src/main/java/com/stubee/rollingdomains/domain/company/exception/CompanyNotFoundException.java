package com.stubee.rollingdomains.domain.company.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class CompanyNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new CompanyNotFoundException();

    private CompanyNotFoundException() {
        super(ErrorCode.COMPANY_NOT_FOUND);
    }

}