package tech.ibrave.metabucket.domain.metadata.dto;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.metadata.ValueType;
import tech.ibrave.metabucket.domain.shared.BaseDto;

/**
 * Author: hungnm
 * Date: 14/06/2023
 */
@Getter
@Setter
public class MetadataDefinitionDto extends BaseDto {
    private String id;
    private String name;
    private ValueType valueType;
    private String description;
    private MetadataCategoryLiteDto category;
}
