package com.stubee.rollinginfrastructure.global.exception.security;

import com.stubee.rollingcore.common.exception.CustomException;
import com.stubee.rollingcore.common.exception.ErrorCode;

public class OAuthFailException extends CustomException {

    public static final CustomException EXCEPTION = new OAuthFailException();

    private OAuthFailException() {
        super(ErrorCode.OAUTH_FAIL);
    }

}