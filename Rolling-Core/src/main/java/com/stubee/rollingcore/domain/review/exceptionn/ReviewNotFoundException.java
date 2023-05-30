package com.stubee.rollingcore.domain.review.exceptionn;

import com.stubee.rollingcore.common.exception.CustomException;

public class ReviewNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(404, "Review Not Found");
    }

}