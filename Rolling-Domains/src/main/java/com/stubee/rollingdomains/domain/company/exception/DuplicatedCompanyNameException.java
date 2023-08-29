package com.stubee.rollingdomains.domain.company.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class DuplicatedCompanyNameException extends CustomException {

    public static final CustomException EXCEPTION = new DuplicatedCompanyNameException();

    private DuplicatedCompanyNameException() {
        super(ErrorCode.DUPLICATED_COMPANY_NAME);
    }

}