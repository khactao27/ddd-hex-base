package tech.ibrave.metabucket.shared.exception;

import org.springframework.http.HttpStatus;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface ErrorCode {
    HttpStatus status();
    String code();
    String messageCode();
}
