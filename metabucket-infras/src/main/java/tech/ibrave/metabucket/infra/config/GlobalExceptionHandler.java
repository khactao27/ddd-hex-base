package tech.ibrave.metabucket.infra.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.shared.exception.ErrorCode;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.response.ErrorResp;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(ErrorCodeException.class)
    public ResponseEntity<ErrorResp> handleErrorCodeException(ErrorCodeException ex) {
        return toErrorResp(ex);
    }

    @ExceptionHandler(DecodingException.class)
    public ResponseEntity<ErrorResp> handleDecodingException(DecodingException ex) {
        return toErrorResp(ErrorCodes.JSON_ERROR, ex);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ErrorResp> handleJsonMappingException(JsonMappingException ex) {
        return toErrorResp(ErrorCodes.JSON_ERROR, ex);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorResp> handleJsonProcessingException(JsonProcessingException ex) {
        return toErrorResp(ErrorCodes.JSON_ERROR, ex);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResp> handleNullPointerException(NullPointerException ex) {
        return toErrorResp(ErrorCodes.INTERNAL_ERROR, ex);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResp> handleIOException(IOException ex) {
        return toErrorResp(ErrorCodes.INTERNAL_ERROR, ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResp> handleAccessDeniedException(AccessDeniedException ex) {
        return toErrorResp(ErrorCodes.ACCESS_DENIED, ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResp> handleMethodArgNotValid(MethodArgumentNotValidException ex) {
        var fieldError = ex.getBindingResult().getFieldError();
        var errorCode = ErrorCodes.INVALID_ARG;
        var messageCode = errorCode.messageCode();
        if (fieldError != null && ObjectUtils.isEmpty(fieldError.getRejectedValue())) {
            errorCode = ErrorCodes.MISSING_REQUIRED_FIELD;
            messageCode = String.format(errorCode.messageCode(), fieldError.getField());
        }

        var errorResp = new ErrorResp(RandomStringUtils.randomAlphabetic(5), errorCode.code(), messageCode);
        return toResponseEntity(errorResp, errorCode.status());
    }

    public ResponseEntity<ErrorResp> toErrorResp(String id,
                                                 String error,
                                                 String message,
                                                 HttpStatus status) {
        return toResponseEntity(new ErrorResp(id, error, message), status);
    }

    @SuppressWarnings("all")
    public ResponseEntity<ErrorResp> toErrorResp(ErrorCodeException ex) {
        var errorCode = ex.getErrorCode();
        var errorResp = new ErrorResp(errorCode.code(), errorCode.messageCode());
        return toResponseEntity(errorResp, errorCode.status());
    }

    public ResponseEntity<ErrorResp> toErrorResp(ErrorCode errorCode, Throwable ex) {
        var errorResp = new ErrorResp(errorCode.code(), ex.getMessage());
        return toResponseEntity(errorResp, errorCode.status());
    }

    /**
     * To response entity with optional.
     *
     * @param optional optional.
     * @param <T>      T.
     * @return ResponseEntity.
     */
    @SuppressWarnings("all")
    protected <T> ResponseEntity<T> toResponseEntity(Optional<T> optional) {
        if (!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return toResponseEntity(optional.get());
    }

    /**
     * To response entity with OK status.
     *
     * @param data data.
     * @param <T>  T.
     * @return ResponseEntity.
     */
    protected <T> ResponseEntity<T> toResponseEntity(T data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    /**
     * To response entity with status.
     *
     * @param data   data.
     * @param status http status.
     * @param <T>    T.
     * @return ResponseEntity.
     */
    protected <T> ResponseEntity<T> toResponseEntity(T data, HttpStatus status) {
        return new ResponseEntity<>(data, status);
    }

    /**
     * To response entity with status.
     *
     * @param optional optional.
     * @param status   http status.
     * @param <T>      T.
     * @return ResponseEntity.
     */
    @SuppressWarnings("all")
    protected <T> ResponseEntity<T> toResponseEntity(Optional<T> optional, HttpStatus status) {
        return optional.map(t -> new ResponseEntity<>(t, status))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
