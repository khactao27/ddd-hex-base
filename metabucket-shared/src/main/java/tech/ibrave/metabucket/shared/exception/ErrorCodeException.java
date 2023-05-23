package tech.ibrave.metabucket.shared.exception;

import lombok.Getter;

@Getter
public class ErrorCodeException extends RuntimeException {

    private final transient ErrorCode errorCode;

    public ErrorCodeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
