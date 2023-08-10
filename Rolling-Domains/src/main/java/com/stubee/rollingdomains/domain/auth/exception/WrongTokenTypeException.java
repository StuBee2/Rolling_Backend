package com.stubee.rollingdomains.domain.auth.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class WrongTokenTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongTokenTypeException();

    private WrongTokenTypeException() {
        super(ErrorCode.WRONG_TOKEN_TYPE);
    }

}