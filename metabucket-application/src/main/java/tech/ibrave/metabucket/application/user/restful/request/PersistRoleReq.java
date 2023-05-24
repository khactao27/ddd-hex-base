package tech.ibrave.metabucket.application.user.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistRoleReq {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
    private List<String> userIds;
}
