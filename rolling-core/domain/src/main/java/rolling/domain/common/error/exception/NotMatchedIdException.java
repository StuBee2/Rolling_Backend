package rolling.domain.common.error.exception;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

public class NotMatchedIdException extends CustomException {

    public static final CustomException EXCEPTION = new NotMatchedIdException();

    private NotMatchedIdException() {
        super(ErrorCode.NOT_MATCHED_ID);
    }

}