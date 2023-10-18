package com.stubee.rollingdomains.domain.member.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class WrongLoginTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongLoginTypeException();

    private WrongLoginTypeException() {
        super(ErrorCode.WRONG_LOGIN_TYPE);
    }

}