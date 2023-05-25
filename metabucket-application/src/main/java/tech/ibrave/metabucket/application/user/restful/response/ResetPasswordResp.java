package tech.ibrave.metabucket.application.user.restful.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.shared.message.Message;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordResp extends SuccessResponse {
    private String newPassword;

    public ResetPasswordResp(Object id,
                             Message message,
                             String newPassword) {
        super(id, message);
        this.newPassword = newPassword;
    }
}