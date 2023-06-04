package tech.ibrave.metabucket.application.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 04/06/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResult {
    private boolean success;
    private String message;
}
