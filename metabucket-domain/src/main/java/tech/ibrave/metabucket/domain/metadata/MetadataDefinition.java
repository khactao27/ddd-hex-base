package tech.ibrave.metabucket.domain.metadata;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Getter
@Setter
public class MetadataDefinition {
    private String id;
    private String name;
    private ValueType valueType;
    private String description;
    private MetadataCategory category;
}
