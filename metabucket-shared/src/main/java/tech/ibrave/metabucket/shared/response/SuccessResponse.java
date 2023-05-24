package tech.ibrave.metabucket.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.shared.message.Message;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse {

    private Object id;
    private String message;
    private String messageCode;

    public static SuccessResponse ofMessage(String message) {
        var resp = new SuccessResponse();
        resp.setMessage(message);
        return resp;
    }

    public static SuccessResponse ofMessageCode(String messageCode) {
        var resp = new SuccessResponse();
        resp.setMessageCode(messageCode);
        return resp;
    }

    public SuccessResponse(Object id, Message message) {
        this.id = id;
        this.message = message.content();
        this.messageCode = message.messageCode();
    }
}
