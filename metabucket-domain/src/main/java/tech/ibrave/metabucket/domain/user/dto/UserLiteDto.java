package tech.ibrave.metabucket.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.BaseDto;
import tech.ibrave.metabucket.domain.shared.UserSource;

/**
 * author: anct
 * date: 5/28/2023
 * YNWA
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLiteDto extends BaseDto {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private String title;
    private String location;
    private String phone;
    private String email;
    private UserSource source = UserSource.SELF_REGISTER;
    private boolean enable;
}
