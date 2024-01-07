package rolling.domain.member.exception;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

public class WrongLoginTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongLoginTypeException();

    private WrongLoginTypeException() {
        super(ErrorCode.WRONG_LOGIN_TYPE);
    }

}