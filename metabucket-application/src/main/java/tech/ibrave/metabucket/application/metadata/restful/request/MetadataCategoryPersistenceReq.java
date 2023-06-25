package tech.ibrave.metabucket.application.metadata.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.metadata.dto.MetaDataDefinitionLiteDto;

import java.util.List;

/**
 * Author: hungnm
 * Date: 15/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataCategoryPersistenceReq {
    @NotBlank(message = "{mb.categories.create.required_categoryname}")
    @Pattern(regexp = "[a-zA-Z0-9\\\\]{1,200}",
            message = "{mb.categories.create.invalid_categoryname}")
    private String name;
    @Pattern(regexp = "[a-zA-Z0-9 \\\\]{1,2000}",
            message = "{mb.category.validate.invalid_cateogrydes}")
    private String description;
    private Long parentId; // category parent
    private List<MetaDataDefinitionLiteDto> metadataDefinitions;
}
