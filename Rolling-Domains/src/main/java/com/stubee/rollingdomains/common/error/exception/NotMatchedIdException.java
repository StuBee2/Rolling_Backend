package com.stubee.rollingdomains.common.error.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class NotMatchedIdException extends CustomException {

    public static final CustomException EXCEPTION = new NotMatchedIdException();

    private NotMatchedIdException() {
        super(ErrorCode.NOT_MATCHED_ID);
    }

}