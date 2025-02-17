package tech.ibrave.metabucket.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.UserSource;
import tech.ibrave.metabucket.shared.model.BaseAuditingObject;

import java.util.List;

/**
 * Author: hungnm
 * Date: 28/05/2023
 */
@Getter
@Setter
public class UserDto extends BaseAuditingObject {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private String title;
    private String location;
    private String phone;
    private String email;
    private UserSource source = UserSource.SELF_REGISTER;
    private List<RoleLiteDto> roles;
    private List<UserGroupLiteDto> groups;
    private Boolean enable;
    private boolean enable2FA;
}
