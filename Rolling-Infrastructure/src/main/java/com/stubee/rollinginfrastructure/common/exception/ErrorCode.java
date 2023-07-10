package com.stubee.rollinginfrastructure.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    IO_EXCEPTION(INTERNAL_SERVER_ERROR.value(), "IOException Occurred"),
    DBACCESS_EXCEPTION(INTERNAL_SERVER_ERROR.value(), "DBAccessException Occurred"),
    SERVLET_BINDING_EXCEPTION(INTERNAL_SERVER_ERROR.value(), "ServletBindingException Occurred"),
    INTERNAL_SERVER_EXCEPTION(INTERNAL_SERVER_ERROR.value(), "InternalServerException Occurred"),

    EXPIRED_JWT_EXCEPTION(BAD_REQUEST.value(), "Expired Jwt Exception Occurred"),
    MALFORMED_JWT_EXCEPTION(BAD_REQUEST.value(), "Malformed Jwt Exception Occurred"),
    UNSUPPORTED_JWT_EXCEPTION(BAD_REQUEST.value(), "Unsupported Jwt Exception Occurred"),
    ILLEGAL_ARGUMENT_EXCEPTION(BAD_REQUEST.value(), "Illegal Argument Exception Occurred");

    private final int statusValue;
    private final String message;

}