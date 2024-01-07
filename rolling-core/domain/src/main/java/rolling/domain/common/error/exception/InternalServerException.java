package rolling.domain.common.error.exception;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

public class InternalServerException extends CustomException {

    public static final CustomException EXCEPTION = new InternalServerException();

    private InternalServerException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}