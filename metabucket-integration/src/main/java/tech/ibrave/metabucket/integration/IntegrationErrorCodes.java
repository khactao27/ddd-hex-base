package tech.ibrave.metabucket.integration;


import org.springframework.http.HttpStatus;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author an.cantuong
 */
public enum IntegrationErrorCodes implements ErrorCode {

    INTEGRATION_NOT_FOUND("I0001", NOT_FOUND, ""),
    INTEGRATE_SECURITY_ERROR("I0002", HttpStatus.BAD_REQUEST, "mb.integrate.security.error"),
    GOOGLE_INTEGRATE_ERROR("I0003", HttpStatus.BAD_REQUEST, "mb.integrate.google.error")
    ;

    private final String code;
    private final HttpStatus status;
    private final String messageCode;

    IntegrationErrorCodes(String code,
                          HttpStatus status,
                          String messageCode) {
        this.messageCode = messageCode;
        this.code = code;
        this.status = status;
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
