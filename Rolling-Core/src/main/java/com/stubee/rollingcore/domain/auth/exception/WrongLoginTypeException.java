package com.stubee.rollingcore.domain.auth.exception;

import com.stubee.rollingcore.common.exception.CustomException;

public class WrongLoginTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongLoginTypeException();

    private WrongLoginTypeException() {
        super(403, "잘못된 로그인 타입입니다.");
    }

}