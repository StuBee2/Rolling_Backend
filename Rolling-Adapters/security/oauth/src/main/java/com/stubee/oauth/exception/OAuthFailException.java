package com.stubee.oauth.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class OAuthFailException extends CustomException {

    public static final CustomException EXCEPTION = new OAuthFailException();

    private OAuthFailException() {
        super(ErrorCode.OAUTH_FAIL);
    }

}