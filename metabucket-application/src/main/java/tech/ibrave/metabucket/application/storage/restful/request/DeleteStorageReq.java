package tech.ibrave.metabucket.application.storage.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: hungnm
 * Date: 22/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteStorageReq {
    @NotBlank(message = "{mb.users.login.required_password}")
    private String password;
    @NotNull(message = "mb.storage.delete.required_Storageid")
    private List<Integer> ids;
}
