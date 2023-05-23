package tech.ibrave.metabucket.shared.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
public class SuccessResponse<ID> extends MessageCodeResp {

    private ID id;
    private String message;

    public SuccessResponse(ID id, String messageCode) {
        super(messageCode);
        this.id = id;
    }

    public SuccessResponse(ID id, String messageCode, String message) {
        super(messageCode);
        this.id = id;
        this.message = message;
    }
}
