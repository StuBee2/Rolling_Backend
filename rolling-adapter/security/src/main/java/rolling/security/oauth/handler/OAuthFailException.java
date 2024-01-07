package rolling.security.oauth.handler;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

class OAuthFailException extends CustomException {

    static final CustomException EXCEPTION = new OAuthFailException();

    private OAuthFailException() {
        super(ErrorCode.OAUTH_FAIL);
    }

}