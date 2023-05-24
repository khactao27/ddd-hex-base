package tech.ibrave.metabucket.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import tech.ibrave.metabucket.shared.message.Message;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResp {
    private String id;
    private String errorCode;
    private String message;
    private String messageCode;

    public ErrorResp(String errorCode, String messageCode) {
        this.id = RandomStringUtils.randomAlphabetic(6);
        this.errorCode = errorCode;
        this.messageCode = messageCode;
    }

    public ErrorResp(String id, String errorCode, Message message) {
        this.id = id;
        this.errorCode = errorCode;
        this.messageCode = message.messageCode();
        this.message = message.content();
    }
}
