package com.stubee.rollingdomains.common.exception;

public class NotMatchedIdException extends CustomException {

    public static final CustomException EXCEPTION = new NotMatchedIdException();

    private NotMatchedIdException() {
        super(ErrorCode.NOT_MATCHED_ID);
    }

}