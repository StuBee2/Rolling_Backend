package com.stubee.rollinginfrastructure.global.exception.news;

import com.stubee.rollingcore.common.exception.CustomException;
import com.stubee.rollingcore.common.exception.ErrorCode;

public class NewsClientException extends CustomException {

    public static final CustomException EXCEPTION = new NewsClientException();

    private NewsClientException() {
        super(ErrorCode.NEWS_CLIENT);
    }

}