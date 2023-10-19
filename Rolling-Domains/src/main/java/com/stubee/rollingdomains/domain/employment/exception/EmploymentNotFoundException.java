package com.stubee.rollingdomains.domain.employment.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class EmploymentNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new EmploymentNotFoundException();

    private EmploymentNotFoundException() {
        super(ErrorCode.EMPLOYMENT_NOT_FOUND);
    }

}