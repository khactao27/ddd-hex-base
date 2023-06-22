package tech.ibrave.metabucket.application.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.integration.IntegrationErrorCodes;
import tech.ibrave.metabucket.shared.exception.ErrorCode;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.model.response.ErrorResp;
import tech.ibrave.metabucket.shared.model.response.ValidationErrorResp;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

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
    public ResponseEntity<ValidationErrorResp> handleMethodArgNotValid(MethodArgumentNotValidException ex) {
        var errorCode = ErrorCodes.INVALID_ARG;

        var errorResp = new ValidationErrorResp(errorCode.code(), messageSource.getMessage(errorCode.messageCode()));
        var fieldErrors = ex.getBindingResult().getFieldErrors();
        if (CollectionUtils.isNotEmpty(fieldErrors)) {
            for (var fieldError: fieldErrors) {
                errorResp.addFieldError(ValidationErrorResp.FieldDetailError.of(fieldError.getField(),
                        fieldError.getDefaultMessage()));
            }
        }

        return toResponseEntity(errorResp, errorCode.status());
    }

    @ExceptionHandler(GeneralSecurityException.class)
    public ResponseEntity<ErrorResp> handleAccessDeniedException(GeneralSecurityException ex) {
        return toErrorResp(IntegrationErrorCodes.INTEGRATE_SECURITY_ERROR, ex);
    }

    @SuppressWarnings("all")
    public ResponseEntity<ErrorResp> toErrorResp(ErrorCodeException ex) {
        var errorCode = ex.getErrorCode();
        var errorResp = new ErrorResp(errorCode.code(), messageSource.getMessage(errorCode.messageCode()));
        return toResponseEntity(errorResp, errorCode.status());
    }

    public ResponseEntity<ErrorResp> toErrorResp(ErrorCode errorCode, Throwable ex) {
        log.error(ex.getMessage(), ex);

        var errorResp = new ErrorResp(errorCode.code(), messageSource.getMessage(errorCode.messageCode()));
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
