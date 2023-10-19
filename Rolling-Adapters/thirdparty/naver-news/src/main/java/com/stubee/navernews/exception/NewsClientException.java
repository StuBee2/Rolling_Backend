package com.stubee.navernews.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class NewsClientException extends CustomException {

    public static final CustomException EXCEPTION = new NewsClientException();

    private NewsClientException() {
        super(ErrorCode.NEWS_CLIENT);
    }

}