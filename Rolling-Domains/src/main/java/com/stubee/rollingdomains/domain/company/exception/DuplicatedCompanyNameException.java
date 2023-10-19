package com.stubee.rollingdomains.domain.company.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class DuplicatedCompanyNameException extends CustomException {

    public static final CustomException EXCEPTION = new DuplicatedCompanyNameException();

    private DuplicatedCompanyNameException() {
        super(ErrorCode.DUPLICATED_COMPANY_NAME);
    }

}