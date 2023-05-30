package com.stubee.rollinginfrastructure.common.exception;

public record ErrorResponse(
        int status,
        String message) {}