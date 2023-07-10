package com.stubee.rollinginfrastructure.common.exception;

public record ErrorResponse(
        int status,
        String message) {
    public static ErrorResponse create(ErrorCode error) {
        return new ErrorResponse(error.getStatusValue(), error.getMessage());
    }
}