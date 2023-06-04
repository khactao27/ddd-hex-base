package tech.ibrave.metabucket.application.user.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 04/06/2023
 */
@Getter
@Setter
public class ImportedUserResult {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private String location;
    private String phone;
    private boolean enable;
    private String message;
}
