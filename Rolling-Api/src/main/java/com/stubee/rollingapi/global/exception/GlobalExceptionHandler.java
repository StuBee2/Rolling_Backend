package com.stubee.rollingapi.global.exception;

import com.stubee.rollingdomains.common.dto.response.ErrorResponse;
import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("CustomException message : {}", e.getMessage());

        return new ResponseEntity<>(
                ErrorResponse.create(e.getErrorCode()), valueOf(e.getErrorCode().getStatusValue()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.ILLEGAL_ARGUMENT), BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<ErrorResponse> handleIOException(IOException e) {
        log.error("IoException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.IO_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(ServletRequestBindingException e) {
        log.error("ServletRequestBindingException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.SERVLET_BINDING_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception class : {}", e.getClass().getSimpleName());
        log.error("Exception message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }

}