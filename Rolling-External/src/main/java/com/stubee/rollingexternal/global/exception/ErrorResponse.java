package com.stubee.rollingexternal.global.exception;

import com.stubee.rollingdomains.common.exception.ErrorCode;

public record ErrorResponse(
        int status,
        String message) {
    public static ErrorResponse create(final ErrorCode error) {
        return new ErrorResponse(error.getStatusValue(), error.getMessage());
    }
}