package com.stubee.rollingapi.global.exception;

import com.stubee.rollingdomains.common.error.ErrorResponse;
import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("CustomException message : {}", e.getMessage());

        return new ResponseEntity<>(
                ErrorResponse.of(e.getErrorCode()), valueOf(e.getErrorCode().getStatusValue()));
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    protected ResponseEntity<ErrorResponse> handle(HttpMediaTypeException e) {
        log.error("HttpMediaTypeException message : {}", e.getMessage());

        return new ResponseEntity<>(
                ErrorResponse.of(400, e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.of(400, e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    protected ResponseEntity<ErrorResponse> handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        log.error("MissingServletRequestPartException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.EMPTY_FILE), BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.METHOD_NOT_SUPPORTED), METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        log.error("MethodArgumentNotValidException message : {}", message);

        return new ResponseEntity<>(ErrorResponse.of(400, message), BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("HttpMediaTypeNotSupportedException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.MEDIA_TYPE_NOT_SUPPORTED), UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<ErrorResponse> handleIOException(IOException e) {
        log.error("IoException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.IO_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(ServletRequestBindingException e) {
        log.error("ServletRequestBindingException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.SERVLET_BINDING_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NestedRuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleNestedException(NestedRuntimeException e) {
        log.error("NestedRuntimeException message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NESTED_RUNTIME_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception class : {}", e.getClass().getSimpleName());
        log.error("Exception message : {}", e.getMessage());

        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }

}