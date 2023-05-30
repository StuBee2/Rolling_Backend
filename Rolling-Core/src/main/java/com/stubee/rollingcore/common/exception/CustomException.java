package com.stubee.rollingcore.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

    private final int status;
    private final String msg;

}