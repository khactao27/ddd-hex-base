package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * Author: hungnm
 * Date: 29/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmRegisterReq {
    @NotEmpty(message = "mb.users.create.required_username")
    @Length(min = 6, max = 32, message = "mb.users.create.invalid_username")
    private String username;
    @NotEmpty(message = "mb.users.create.required_firstname")
    @Length(max = 32, message = "mb.users.create.invalid_firstname")
    private String firstName;
    @NotEmpty(message = "mb.users.create.required_lastname")
    @Length( max = 32, message = "mb.users.create.invalid_lastname")
    private String lastName;
    private String password;
    private String fullName;
    private String phone;
    private String location;
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
