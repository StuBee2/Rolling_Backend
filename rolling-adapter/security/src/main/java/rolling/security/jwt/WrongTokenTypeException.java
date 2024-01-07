package rolling.security.jwt;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

class WrongTokenTypeException extends CustomException {

    static final CustomException EXCEPTION = new WrongTokenTypeException();

    private WrongTokenTypeException() {
        super(ErrorCode.WRONG_TOKEN_TYPE);
    }

}