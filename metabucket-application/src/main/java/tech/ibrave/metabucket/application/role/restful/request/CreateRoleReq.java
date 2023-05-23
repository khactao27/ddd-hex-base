package tech.ibrave.metabucket.application.role.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.role.Role;
import tech.ibrave.metabucket.shared.request.UseCaseReq;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRoleReq implements UseCaseReq<Role> {

    private String name;
    private String description;
    private List<String> userIds;

    @Override
    public Role toDomainModel() {
        return new Role(name, description);
    }
}
