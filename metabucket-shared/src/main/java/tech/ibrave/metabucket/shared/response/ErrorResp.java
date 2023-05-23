package tech.ibrave.metabucket.shared.response;

import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
public class ErrorResp extends MessageCodeResp {
    private final String id;
    private final String errorCode;

    public ErrorResp(String errorCode, String messageCode) {
        super(messageCode);
        this.id = RandomStringUtils.randomAlphabetic(6);
        this.errorCode = errorCode;
    }

    public ErrorResp(String id, String errorCode, String messageCode) {
        super(messageCode);
        this.id = id;
        this.errorCode = errorCode;
    }
}
