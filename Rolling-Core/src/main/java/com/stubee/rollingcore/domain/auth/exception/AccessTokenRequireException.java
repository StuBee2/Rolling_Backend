package com.stubee.rollingcore.domain.auth.exception;

import com.stubee.rollingcore.common.exception.CustomException;

public class AccessTokenRequireException extends CustomException {

    public static final CustomException EXCEPTION = new AccessTokenRequireException();

    private AccessTokenRequireException() {
        super(400, "");
    }

}