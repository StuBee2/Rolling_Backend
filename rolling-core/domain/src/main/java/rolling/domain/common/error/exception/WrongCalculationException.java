package rolling.domain.common.error.exception;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

public class WrongCalculationException extends CustomException {

    public static final CustomException EXCEPTION = new WrongCalculationException();

    private WrongCalculationException() {
        super(ErrorCode.WRONG_CALCULATION);
    }

}