package com.stubee.rollingdomains.domain.employment.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class EmploymentNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new EmploymentNotFoundException();

    private EmploymentNotFoundException() {
        super(ErrorCode.EMPLOYMENT_NOT_FOUND);
    }

}