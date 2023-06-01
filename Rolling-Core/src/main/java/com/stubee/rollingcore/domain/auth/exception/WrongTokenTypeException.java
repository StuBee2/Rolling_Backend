package com.stubee.rollingcore.domain.auth.exception;

import com.stubee.rollingcore.common.exception.CustomException;

public class WrongTokenTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongTokenTypeException();

    private WrongTokenTypeException() {
        super(400, "Token 타입을 확인하세요");
    }

}