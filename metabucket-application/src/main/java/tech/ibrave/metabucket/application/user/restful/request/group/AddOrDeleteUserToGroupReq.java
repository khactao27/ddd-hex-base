package tech.ibrave.metabucket.application.user.restful.request.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.user.dto.UserDto;

import java.util.List;

/**
 * Author: hungnm
 * Date: 06/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddOrDeleteUserToGroupReq {
    private List<UserDto> users;
}
