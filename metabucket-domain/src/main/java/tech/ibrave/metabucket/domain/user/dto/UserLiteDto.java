package tech.ibrave.metabucket.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * author: anct
 * date: 5/28/2023
 * YNWA
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLiteDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
}
