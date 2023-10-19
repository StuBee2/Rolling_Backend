package com.stubee.rollingdomains.common.error.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class WrongCalculationException extends CustomException {

    public static final CustomException EXCEPTION = new WrongCalculationException();

    private WrongCalculationException() {
        super(ErrorCode.WRONG_CALCULATION);
    }

}