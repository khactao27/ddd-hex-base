package tech.ibrave.metabucket.domain;

import org.springframework.http.HttpStatus;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public enum ErrorCodes implements ErrorCode {
    BUSINESS_ERROR("MB0001", HttpStatus.BAD_REQUEST, "mb.business_error"),
    INTERNAL_ERROR("MB0002", HttpStatus.INTERNAL_SERVER_ERROR, "mb.internal_error"),
    JSON_ERROR("MB0003", HttpStatus.BAD_REQUEST, "mb.bad_request"),
    ACCESS_DENIED("MB0004", HttpStatus.UNAUTHORIZED, "mb.access_denied"),
    INVALID_ARG("MB0005", HttpStatus.BAD_REQUEST, "mb.invalid_arg"),
    MISSING_REQUIRED_FIELD("MB0006", HttpStatus.BAD_REQUEST, "mb.missing_required_field");

    private final String code;
    private final HttpStatus status;
    private final String messageCode;

    ErrorCodes(String code, HttpStatus status, String messageCode) {
        this.code = code;
        this.status = status;
        this.messageCode = messageCode;
    }

    @Override
    public HttpStatus status() {
        return status;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String messageCode() {
        return messageCode;
    }
}
