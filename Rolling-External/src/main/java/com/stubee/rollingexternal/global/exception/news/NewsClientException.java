package com.stubee.rollingexternal.global.exception.news;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class NewsClientException extends CustomException {

    public static final CustomException EXCEPTION = new NewsClientException();

    private NewsClientException() {
        super(ErrorCode.NEWS_CLIENT);
    }

}