package com.stubee.rollingdomains.domain.review.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class ReviewNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ErrorCode.REVIEW_NOT_FOUND);
    }

}