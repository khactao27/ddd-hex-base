package tech.ibrave.metabucket.domain.metadata.dto;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.metadata.ValueType;
import tech.ibrave.metabucket.shared.model.BaseAuditingObject;

import java.util.Set;

/**
 * Author: hungnm
 * Date: 14/06/2023
 */
@Getter
@Setter
public class MetadataDefinitionDto extends BaseAuditingObject {
    private Long id;
    private String name;
    private ValueType valueType;
    private String description;
    private MetadataCategoryLiteDto category;
    private Set<MetadataOptionDto> MetadataOptions;
}
