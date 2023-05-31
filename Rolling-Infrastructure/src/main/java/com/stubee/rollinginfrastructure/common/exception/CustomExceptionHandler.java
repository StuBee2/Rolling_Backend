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

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<ErrorResponse> handleIOException(IOException e) {
        return new ResponseEntity<>(
                new ErrorResponse(INTERNAL_SERVER_ERROR.value(), ErrorMessage.IOEXCEPTION),
                INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    protected  ResponseEntity<ErrorResponse> handleDBException(DataAccessException e) {
        return new ResponseEntity<>(
                new ErrorResponse(BAD_REQUEST.value(), ErrorMessage.DBACCESSEXCEPTION),
                BAD_REQUEST);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(ServletRequestBindingException e) {
        return new ResponseEntity<>(
                new ErrorResponse(BAD_REQUEST.value(), ErrorMessage.SERVLETBINDINGEXCEPTION),
                BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(
                new ErrorResponse(INTERNAL_SERVER_ERROR.value(), ErrorMessage.INTERNAL_SERVER),
                INTERNAL_SERVER_ERROR);
    }

    private static class ErrorMessage {

        private static final String IOEXCEPTION = "IOEXCEPTION OCCURRED";
        private static final String DBACCESSEXCEPTION = "DBACCESSEXCEPTION OCCURRED";
        private static final String SERVLETBINDINGEXCEPTION = "SERVLETREQUESTBINDINGEXCEPTION OCCURRED";
        private static final String INTERNAL_SERVER = "INTERNAL SERVER ERROR OCCURRED";

    }

}