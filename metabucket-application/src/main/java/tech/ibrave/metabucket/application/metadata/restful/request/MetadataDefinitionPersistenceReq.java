package tech.ibrave.metabucket.application.metadata.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.metadata.ValueType;
import tech.ibrave.metabucket.domain.metadata.dto.MetadataCategoryLiteDto;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataDefinitionPersistenceReq {
    @NotBlank(message = "{mb.metadata.create.required_metadataname}")
    @Pattern(regexp = "[a-zA-Z0-9\\\\]{1,100}", message = "{mb.metadata.create.invalid_metadataname}")
    private String name;
    @Size(max = 100, message = "{mb.metadata.create.invalid_metadatades}")
    private String description;
    private ValueType valueType;
    private MetadataCategoryLiteDto category;
}
