package com.stubee.navernews.adapters;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

class NewsClientException extends CustomException {

    static final CustomException EXCEPTION = new NewsClientException();

    private NewsClientException() {
        super(ErrorCode.NEWS_CLIENT);
    }

}