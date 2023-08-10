package com.stubee.rollingdomains.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    IO_ERROR(500, "IOException occurred"),
    DBACCESS_ERROR(500, "DBAccessException occurred"),
    SERVLET_BINDING_ERROR(500, "ServletBindingException occurred"),
    INTERNAL_SERVER_ERROR(500, "InternalServerException occurred"),
    FILE_CONVERT_ERROR(500, "FileConvertException occurred"),
    FILE_UPLOAD_ERROR(500, "FileUploadException occurred"),

    MEMBER_NOT_FOUND(404, "Member not found"),
    COMPANY_NOT_FOUND(404, "Company not found"),
    REVIEW_NOT_FOUND(404, "Review not found"),

    NOT_MATCHED_MEMBER(403, "You are not the author/registrant"),
    WRONG_LOGIN_TYPE(403, "Check your login type"),

    EXPIRED_JWT(401, "Jwt is expired"),
    OAUTH_FAIL(401, "OAuth failed"),

    WRONG_TOKEN_TYPE(400, "Check your token type"),
    MALFORMED_JWT(400, "Jwt is malformed"),
    UNSUPPORTED_JWT(400, "Jwt is unsupported"),
    NEWS_CLIENT(400, "Page or Size is too big"),
    ILLEGAL_ARGUMENT(400, "IllegalArgumentException occurred");

    private final int statusValue;
    private final String message;

}