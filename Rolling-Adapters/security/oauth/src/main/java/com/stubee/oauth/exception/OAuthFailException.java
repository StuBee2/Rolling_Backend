package com.stubee.oauth.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class OAuthFailException extends CustomException {

    public static final CustomException EXCEPTION = new OAuthFailException();

    private OAuthFailException() {
        super(ErrorCode.OAUTH_FAIL);
    }

}