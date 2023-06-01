package tech.ibrave.metabucket.application.auth.base;

import org.springframework.http.HttpStatus;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

/**
 * Author: anct
 * Date: 29/05/2023
 * #YWNA
 */
public enum AuthErrorCodes implements ErrorCode {
    INVALID_USERNAME_OR_PW("MB1001",  HttpStatus.BAD_REQUEST, "mb.auth.invalid_credential"),
    TOKEN_INVALID("MB1002", HttpStatus.BAD_REQUEST, "mb.auth.invalid_token"),
    INVALID_PASSWORD("MB1003", HttpStatus.BAD_REQUEST, "mb.auth.invalid_password"),
    USERNAME_NOT_FOUND("MB1004", HttpStatus.NOT_FOUND, "mb.auth.user_not_found"),
    EMAIL_NOT_FOUND("MB1005", HttpStatus.NOT_FOUND, "mb.auth.email_not_found"),

    ;

    private final String code;
    private final HttpStatus status;
    private final String messageCode;

    AuthErrorCodes(String code, HttpStatus status, String messageCode) {
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