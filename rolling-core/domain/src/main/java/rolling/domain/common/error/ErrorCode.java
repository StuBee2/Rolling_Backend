package rolling.domain.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    IO_ERROR(500, "IOException occurred"),
    NESTED_RUNTIME_ERROR(500, "NestedRuntimeException occurred"),
    SERVLET_BINDING_ERROR(500, "ServletBindingException occurred"),
    INTERNAL_SERVER_ERROR(500, "InternalServerException occurred"),
    FILE_CONVERT_ERROR(500, "FileConvertException occurred"),
    FILE_UPLOAD_ERROR(500, "FileUploadException occurred"),
    WRONG_CALCULATION(500, "Calculation is wrong"),

    MEDIA_TYPE_NOT_SUPPORTED(415, "Http media type is not supported"),

    DUPLICATED_NICKNAME(409, "Nickname is duplicated"),
    DUPLICATED_EMAIL(409, "Email is duplicated"),
    DUPLICATED_COMPANY_NAME(409, "Company name is duplicated"),

    METHOD_NOT_SUPPORTED(405, "Http method is not supported"),

    MEMBER_NOT_FOUND(404, "Member not found"),
    COMPANY_NOT_FOUND(404, "Company not found"),
    EMPLOYMENT_NOT_FOUND(404, "Employment not found"),
    STORY_NOT_FOUND(404, "Story not found"),

    ACCESS_DENIED(403, "Access denied"),
    NOT_MATCHED_ID(403, "Not matched id"),
    WRONG_LOGIN_TYPE(403, "Check your login type"),
    ALUMNI_CERTIFY_FAILED(403, "Alumni certify failed"),

    EXPIRED_JWT(401, "Jwt is expired"),
    OAUTH_FAIL(401, "OAuth failed"),

    OBJECT_VALID_FAIL(400, "Object validation failed"),
    EMPTY_FILE(400, "File is empty"),
    JWT_SIGNATURE_NOT_MATCHED(400, "JWT signature doesn't matched"),
    WRONG_TOKEN_TYPE(400, "Check your token type"),
    MALFORMED_JWT(400, "Jwt is malformed"),
    UNSUPPORTED_JWT(400, "Jwt is unsupported"),
    NEWS_CLIENT(400, "Page or Size is too big"),
    EMPLOYMENT_EXIST(400, "Employment already exists"),
    ILLEGAL_ARGUMENT(400, "IllegalArgumentException occurred");

    private final int statusValue;
    private final String message;

}