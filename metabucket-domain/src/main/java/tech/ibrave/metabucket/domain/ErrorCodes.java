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
    INCORRECT_PASSWORD("MB0014", HttpStatus.BAD_REQUEST, "mb.user.update.incorrect_password"),
    DUPLICATE_PASSWORD("MB0015", HttpStatus.BAD_REQUEST, "mb.user.update.duplicated_oldpassword"),
    DUPLICATE_METADATA_NAME("MB0016", HttpStatus.BAD_REQUEST, " mb.metadata.create.duplicated_metadataname"),
    DUPLICATE_MULTI_VALUE("MB0017", HttpStatus.BAD_REQUEST, "mb.metadata.create.duplicate_multi_value"),
    DUPLICATE_CATEGORY_NAME("MB0018", HttpStatus.BAD_REQUEST, "mb.categories.create.duplicated_categoryname"),
    INVALID_STATUS("MB0019", HttpStatus.BAD_REQUEST, "mb.users.create.invalid_status"),
    STORAGE_NOT_FOUND("MB0020", HttpStatus.NOT_FOUND, "mb.storage.notfound"),
    DUPLICATE_STORAGE_NAME("MB0021", HttpStatus.BAD_REQUEST, "mb.storage.validate.duplicated_metadataname"),
    INVALID_TOTAL_CAPACITY("MB0022", HttpStatus.BAD_REQUEST, "mb.storage.validate.invalid_totalcapacity"),
    INVALID_PASSWORD("MB0023", HttpStatus.BAD_REQUEST, "mb.users.login.invalid_password"),
    PERMISSION_NOT_EXIST("MB0024", HttpStatus.BAD_REQUEST, "mb.roles.create.notexist_permissioncode"),
    USER_NOT_FOUND("MB0025", HttpStatus.NOT_FOUND, "mb.users.not_found"),
    ROLE_NOT_FOUND("MB0026", HttpStatus.NOT_FOUND, "mb.roles.not_found"),
    USER_GROUP_NOT_FOUND("MB0027", HttpStatus.NOT_FOUND, "mb.groups.not_found"),
    METADATA_OPTION_NOT_FOUND("MB0028", HttpStatus.NOT_FOUND, "mb.option.not_found"),
    LOG_NOT_FOUND("MB0029", HttpStatus.NOT_FOUND, "mb.logs.not_found"),
    CATEGORY_NOT_FOUND("MB0030", HttpStatus.NOT_FOUND, "mb.category.not_found"),
    SETTING_NOT_FOUND("MB0031", HttpStatus.NOT_FOUND, "mb.settings.not_found"),
    METADATA_DEFINITION_NOT_FOUND("MB0032", HttpStatus.NOT_FOUND, "mb.definition.not_found"),
    UPDATE_USER_NOT_FOUND("MB0033", HttpStatus.NOT_FOUND, "mb.users.update.notexist_userid"),
    DETAIL_USER_NOT_FOUND("MB0033", HttpStatus.NOT_FOUND, "mb.users.getdetail.notexist_userid"),

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
