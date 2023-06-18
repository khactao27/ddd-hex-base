package tech.ibrave.metabucket.domain.metadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 16/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDataDefinitionLiteDto {
    private Long id;
    private String name;
    private String description;
}
