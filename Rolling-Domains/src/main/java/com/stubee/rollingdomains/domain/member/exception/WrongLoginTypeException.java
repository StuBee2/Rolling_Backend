package com.stubee.rollingdomains.domain.member.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class WrongLoginTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongLoginTypeException();

    private WrongLoginTypeException() {
        super(ErrorCode.WRONG_LOGIN_TYPE);
    }

}