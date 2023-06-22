package tech.ibrave.metabucket.application.storage.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.storage.StorageType;
import tech.ibrave.metabucket.shared.constant.DataUnit;

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
    @NotNull(message = "mb.storage.validate.required_type")
    private StorageType type;
    @NotNull(message = "{mb.storage.validate.required_totalcapacity}")
    @Max(value = 999999999, message = "{mb.storage.validate.invalid_totalcapacity}")
    private Long totalCapacity;
    @NotNull(message = "mb.storage.validate.required_unit")
    private DataUnit unit;
    private String accessToken;
    private String refreshToken;
}
