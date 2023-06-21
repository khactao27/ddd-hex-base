package tech.ibrave.metabucket.application.storage.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.storage.StorageType;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoragePersistenceReq {
    @NotBlank(message = "{mb.storage.validate.required_storagename}")
    @Pattern(regexp = "[a-zA-Z0-9\\\\]{1,100}",
            message = "{mb.storage.validate.invalid_storagename}")
    private String name;
    @Size(max = 1000, message = "{mb.properties.validate.invalid_storagedes}")
    private String description;
    @NotBlank(message = "mb.storage.validate.required_type")
    private StorageType type;
    @NotBlank(message = "{mb.storage.validate.required_totalcapacity}")
    @Pattern(regexp = "[0-9\\\\]{1,10}",
            message = "{mb.storage.validate.invalid_totalcapacity}")
    private int totalCapacity;
    private String accessToken;
    private String refreshToken;
}
