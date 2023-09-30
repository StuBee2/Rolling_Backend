package com.stubee.rollingdomains.common.exception;

public class WrongCalculationException extends CustomException {

    public static final CustomException EXCEPTION = new WrongCalculationException();

    private WrongCalculationException() {
        super(ErrorCode.WRONG_CALCULATION);
    }

}