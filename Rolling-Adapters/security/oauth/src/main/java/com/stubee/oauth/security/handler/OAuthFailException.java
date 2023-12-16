package com.stubee.oauth.security.handler;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

class OAuthFailException extends CustomException {

    static final CustomException EXCEPTION = new OAuthFailException();

    private OAuthFailException() {
        super(ErrorCode.OAUTH_FAIL);
    }

}