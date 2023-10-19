package com.stubee.rollingdomains.common.error;

public record ErrorResponse(
        int status,
        String message) {
    public static ErrorResponse create(final ErrorCode error) {
        return new ErrorResponse(error.getStatusValue(), error.getMessage());
    }
}