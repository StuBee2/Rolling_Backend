package com.stubee.rollingcore.domain.review.exception;

import com.stubee.rollingcore.common.exception.CustomException;
import com.stubee.rollingcore.common.exception.ErrorCode;

public class ReviewNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ErrorCode.REVIEW_NOT_FOUND);
    }

}