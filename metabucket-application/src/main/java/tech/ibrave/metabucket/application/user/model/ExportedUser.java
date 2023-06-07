package tech.ibrave.metabucket.application.user.model;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.UserSource;

/**
 * Author: hungnm
 * Date: 04/06/2023
 */
@Getter
@Setter
public class ExportedUser {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private String title;
    private String location;
    private String phone;
    private String email;
    private UserSource source;
    private boolean enable;
}
