package com.stubee.rollingdomains.domain.employment.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class EmploymentExistException extends CustomException {

    public static final CustomException EXCEPTION = new EmploymentExistException();

    private EmploymentExistException() {
        super(ErrorCode.EMPLOYMENT_EXIST);
    }

}