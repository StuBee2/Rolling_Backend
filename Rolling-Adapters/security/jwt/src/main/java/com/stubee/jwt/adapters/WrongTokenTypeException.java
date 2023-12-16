package com.stubee.jwt.adapters;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

class WrongTokenTypeException extends CustomException {

    static final CustomException EXCEPTION = new WrongTokenTypeException();

    private WrongTokenTypeException() {
        super(ErrorCode.WRONG_TOKEN_TYPE);
    }

}