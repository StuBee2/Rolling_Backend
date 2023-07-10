package com.stubee.rollinginfrastructure.common.exception;

import com.stubee.rollingcore.common.exception.CustomException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return new ResponseEntity<>(
                new ErrorResponse(e.getStatus(), e.getMsg()), HttpStatusCode.valueOf(e.getStatus()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException() {
        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION), BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<ErrorResponse> handleIOException() {
        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.IO_EXCEPTION), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    protected  ResponseEntity<ErrorResponse> handleDBException() {
        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.DBACCESS_EXCEPTION), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    protected ResponseEntity<ErrorResponse> handleBindException() {
        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.SERVLET_BINDING_EXCEPTION), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException() {
        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.INTERNAL_SERVER_EXCEPTION), INTERNAL_SERVER_ERROR);
    }

}