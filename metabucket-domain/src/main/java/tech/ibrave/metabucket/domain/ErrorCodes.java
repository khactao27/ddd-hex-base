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
    MISSING_REQUIRED_FIELD("MB0006", HttpStatus.BAD_REQUEST, "mb.missing_required_field"),
    NOT_FOUND("MB0007", HttpStatus.NOT_FOUND, "mb.not_found"),
    EXISTED_EMAIL("MB0008", HttpStatus.BAD_REQUEST, "mb.users.create.duplicated_email"),
    EXISTED_USERNAME("MB0009", HttpStatus.BAD_REQUEST, "mb.users.create.duplicated_username"),
    ROLE_NAME_EXISTED("MB0008", HttpStatus.BAD_REQUEST, "mb.roles.name.existed"),
    READ_FILE_ERROR("MB0009", HttpStatus.BAD_REQUEST, "mb.read_file_error"),
    INVALID_FIELD("MB0010", HttpStatus.BAD_REQUEST, "mb.users.import.invalid_field"),
    TOO_LARGE_FILE("MB.0010", HttpStatus.BAD_REQUEST, "mb.user.import.too_large_file"),
    EXPORT_ERROR("MB0011", HttpStatus.BAD_REQUEST, "mb.users.export.error"),
    GROUP_NAME_EXISTED("MB0013", HttpStatus.BAD_REQUEST, "mb.groups.name.existed"),

    ;

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
